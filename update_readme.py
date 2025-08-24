import os
import subprocess
from collections import defaultdict
from datetime import datetime

README_PATH = "README.md"

# SWEA 난이도 → 공통 티어 매핑
def normalize_tier(tier: str) -> str:
    """SWEA의 D 난이도를 BOJ/Programmers 티어 체계에 맞게 그룹화"""
    if tier.startswith("D") and tier[1].isdigit():
        d_level = int(tier[1])
        if d_level <= 2:
            return "Bronze"
        elif d_level <= 4:
            return "Silver"
        elif d_level <= 6:
            return "Gold"
    return tier if tier else "Unrated"

def get_git_commit_date(file_path: str) -> str:
    """파일별 마지막 커밋 날짜 가져오기"""
    try:
        result = subprocess.check_output(
            ["git", "log", "-1", "--format=%cd", "--date=short", "--", file_path],
            text=True
        )
        return result.strip()
    except subprocess.CalledProcessError:
        return "-"

def scan_problems(base_dir: str, platform: str):
    problems = defaultdict(list)

    for root, _, files in os.walk(base_dir):
        for file in files:
            if file.endswith(".java"):
                rel_path = os.path.relpath(os.path.join(root, file), base_dir)
                parts = rel_path.split(os.sep)
                if len(parts) < 3:
                    continue

                tier = parts[0]
                problem_info = parts[1]
                problem_num, problem_name = problem_info.split(". ", 1)
                commit_date = get_git_commit_date(os.path.join(base_dir, rel_path))

                if platform == "BOJ":
                    link = f"https://www.acmicpc.net/problem/{problem_num}"
                elif platform == "SWEA":
                    link = f"https://swexpertacademy.com/main/code/problem/{problem_num}"
                else:
                    link = "#"

                problems[normalize_tier(tier)].append({
                    "name": problem_name,
                    "tier": normalize_tier(tier),
                    "date": commit_date,
                    "link": link
                })

    return problems

def format_table(platform: str, problems: dict) -> str:
    output = []
    for tier, plist in sorted(problems.items()):
        output.append(f"<details>\n<summary>{tier} 문제 보기 ({len(plist)}개)</summary>\n")
        output.append("\n| Problem | Tier | Solved On | Link |\n|---------|------|-----------|------|")
        for p in sorted(plist, key=lambda x: x["name"]):
            output.append(f'| {p["name"]} | {p["tier"]} | {p["date"]} | [Link]({p["link"]}) |')
        output.append("\n</details>\n")
    return "\n".join(output)

def generate_stats(all_problems: dict) -> str:
    """README 통계 표 생성"""
    platforms = ["BOJ", "Programmers", "SWEA"]
    tiers = ["Bronze", "Silver", "Gold", "Platinum", "Diamond"]

    lines = ["| Platform | Solved | Bronze | Silver | Gold | Platinum | Diamond |",
             "|----------|-------|--------|--------|------|---------|--------|"]

    for platform in platforms:
        problems = all_problems.get(platform, {})
        tier_counts = defaultdict(int)
        for tier, plist in problems.items():
            tier_counts[tier] += len(plist)

        total = sum(tier_counts.values())
        row = [platform, str(total)]
        for t in tiers:
            row.append(str(tier_counts.get(t, 0)) if t in tier_counts else "-")
        lines.append("| " + " | ".join(row) + " |")

    return "\n".join(lines)

def update_readme(boj_problems, prgm_problems, swea_problems):
    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    # BOJ
    new_boj = format_table("BOJ", boj_problems)
    content = replace_between(content, "BOJ_START", "BOJ_END", new_boj)

    # Programmers
    new_prgm = format_table("Programmers", prgm_problems)
    content = replace_between(content, "PRGM_START", "PRGM_END", new_prgm)

    # SWEA
    new_swea = format_table("SWEA", swea_problems)
    content = replace_between(content, "SWEA_START", "SWEA_END", new_swea)

    # Stats
    all_problems = {"BOJ": boj_problems, "Programmers": prgm_problems, "SWEA": swea_problems}
    new_stats = generate_stats(all_problems)
    content = replace_between(content, "STATS_START", "STATS_END", new_stats)

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

def replace_between(content: str, start: str, end: str, new: str) -> str:
    start_tag, end_tag = f"<!-- {start} -->", f"<!-- {end} -->"
    start_idx, end_idx = content.find(start_tag), content.find(end_tag)
    if start_idx == -1 or end_idx == -1:
        return content
    return content[:start_idx + len(start_tag)] + "\n" + new + "\n" + content[end_idx:]

if __name__ == "__main__":
    boj_problems = scan_problems("백준", "BOJ")
    swea_problems = scan_problems("SWEA", "SWEA")
    prgm_problems = scan_problems("프로그래머스", "Programmers")

    update_readme(boj_problems, prgm_problems, swea_problems)
