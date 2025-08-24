import os
import re
import subprocess

README_FILE = "README.md"

PLATFORMS = {
    "백준": "<!-- BOJ_START -->",
    "프로그래머스": "<!-- PRGM_START -->",
    "SWEA": "<!-- SWEA_START -->"
}

# 공통 티어 순서
COMMON_TIER_ORDER = ["Bronze", "Silver", "Gold", "Platinum", "Diamond", "Unrated"]

# SWEA 전용 티어 순서 (D1~D6 난이도: D1 < D2 < ... < D6)
SWEA_TIER_ORDER = ["D1", "D2", "D3", "D4", "D5", "D6", "Unrated"]

def get_last_commit_date(file_path):
    """파일 기준 마지막 커밋 날짜 가져오기"""
    if not os.path.exists(file_path):
        return "Unknown"
    try:
        result = subprocess.run(
            ["git", "log", "-1", "--format=%cd", "--date=short", file_path],
            capture_output=True,
            text=True,
            check=True
        )
        return result.stdout.strip()
    except subprocess.CalledProcessError:
        return "Unknown"

def parse_problems(platform_dir, platform_name):
    """폴더 구조 탐색 후 문제 정보 수집"""
    if not os.path.exists(platform_dir):
        return []

    problems = []
    for difficulty in sorted(os.listdir(platform_dir)):
        diff_path = os.path.join(platform_dir, difficulty)
        if not os.path.isdir(diff_path):
            continue
        for prob_folder in sorted(os.listdir(diff_path)):
            prob_path = os.path.join(diff_path, prob_folder)
            if not os.path.isdir(prob_path):
                continue

            # 문제 ID와 제목 추출
            match = re.match(r"(\d+)\.\s*(.+)", prob_folder)
            if match:
                prob_id, title = match.groups()
            else:
                prob_id = prob_folder
                title = prob_folder

            # 파일 기준 마지막 커밋 날짜
            file_for_date = None
            for fname in os.listdir(prob_path):
                if fname.endswith(".java"):
                    file_for_date = os.path.join(prob_path, fname)
                    break
            solved_on = get_last_commit_date(file_for_date if file_for_date else prob_path)

            problems.append({
                "id": prob_id,
                "title": title,
                "tier": difficulty if platform_name=="SWEA" else difficulty,
                "solved_on": solved_on
            })
    return problems

def sort_problems(problems, platform=None):
    """난이도 순 + 제목순 정렬"""
    if platform == "SWEA":
        tier_order = {tier:i for i, tier in enumerate(SWEA_TIER_ORDER)}
    else:
        tier_order = {tier:i for i, tier in enumerate(COMMON_TIER_ORDER)}
    return sorted(problems, key=lambda x: (tier_order.get(x["tier"], 99), x["title"]))

def generate_table_by_tier(problems, platform):
    """티어별 <details> 토글로 마크다운 테이블 생성"""
    if platform == "SWEA":
        tier_order = SWEA_TIER_ORDER
    else:
        tier_order = COMMON_TIER_ORDER

    tier_groups = {tier: [] for tier in tier_order}
    for p in problems:
        tier_groups.setdefault(p["tier"], []).append(p)

    tables = []
    for tier in tier_order:
        if not tier_groups.get(tier):
            continue
        tables.append(f"<details>\n<summary>{tier} 문제 보기 ({len(tier_groups[tier])}개)</summary>\n")
        tables.append("| Problem | Tier | Solved On | Link |")
        tables.append("|---------|------|-----------|------|")
        for p in tier_groups[tier]:
            if platform == "백준":
                link = f"https://www.acmicpc.net/problem/{p['id']}"
            elif platform == "SWEA":
                link = f"https://swexpertacademy.com/main/code/problem/{p['id']}"
            elif platform == "프로그래머스":
                link = f"https://school.programmers.co.kr/learn/courses/30/lessons/{p['id']}"
            else:
                link = "#"
            tables.append(f"| {p['title']} | {p['tier']} | {p['solved_on']} | [Link]({link}) |")
        tables.append("</details>\n")
    return tables

def update_section(start_tag, end_tag, lines):
    with open(README_FILE, "r", encoding="utf-8") as f:
        content = f.read()
    new_section = start_tag + "\n" + "\n".join(lines) + "\n" + end_tag
    content = re.sub(f"{start_tag}.*?{end_tag}", new_section, content, flags=re.DOTALL)
    with open(README_FILE, "w", encoding="utf-8") as f:
        f.write(content)

def compute_statistics(problems, platform=None):
    """플랫폼별 통계 계산"""
    if platform == "SWEA":
        tiers = SWEA_TIER_ORDER
    else:
        tiers = COMMON_TIER_ORDER

    stats = {tier:0 for tier in tiers}
    for p in problems:
        tier = p["tier"]
        if platform == "SWEA":
            tier = tier  # 그대로 사용
        stats[tier] = stats.get(tier,0)+1
    total = len(problems)
    return total, stats

def update_stats_section(boj, prgm, swea):
    lines = ["| Platform | Solved | " + " | ".join(COMMON_TIER_ORDER) + " |",
             "|----------|-------|" + "--------|"*len(COMMON_TIER_ORDER)]
    for platform_name, probs in [("BOJ", boj), ("Programmers", prgm), ("SWEA", swea)]:
        total, stats = compute_statistics(probs, platform_name if platform_name=="SWEA" else None)
        tier_counts = " | ".join(str(stats.get(t, 0)) for t in COMMON_TIER_ORDER)
        lines.append(f"| {platform_name} | {total} | {tier_counts} |")
    start_tag = "<!-- STATS_START -->"
    end_tag = "<!-- STATS_END -->"
    with open(README_FILE, "r", encoding="utf-8") as f:
        content = f.read()
    new_section = start_tag + "\n" + "\n".join(lines) + "\n" + end_tag
    content = re.sub(f"{start_tag}.*?{end_tag}", new_section, content, flags=re.DOTALL)
    with open(README_FILE, "w", encoding="utf-8") as f:
        f.write(content)

def main():
    boj_problems = sort_problems(parse_problems("백준", "백준"))
    prgm_problems = sort_problems(parse_problems("프로그래머스", "프로그래머스"))
    swea_problems = sort_problems(parse_problems("SWEA", "SWEA"), platform="SWEA")

    for platform, tag, probs in [("백준","<!-- BOJ_START -->",boj_problems),
                                 ("프로그래머스","<!-- PRGM_START -->",prgm_problems),
                                 ("SWEA","<!-- SWEA_START -->",swea_problems)]:
        table_lines = generate_table_by_tier(probs, platform)
        end_tag = tag.replace("START","END")
        update_section(tag, end_tag, table_lines)
        print(f"✅ {platform} 문제 업데이트 완료! 총 {len(probs)}문제 적용됨.")

    update_stats_section(boj_problems, prgm_problems, swea_problems)
    print("📊 통계 섹션 업데이트 완료!")

if __name__ == "__main__":
    main()
