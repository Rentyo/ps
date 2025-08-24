import os
import re
import subprocess

README_FILE = "README.md"

PLATFORMS = {
    "백준": "<!-- BOJ_START -->",
    "SWEA": "<!-- SWEA_START -->",
    "프로그래머스": "<!-- PRGM_START -->"
}

# 난이도 순서
TIER_ORDER = {"Platinum": 1, "Gold": 2, "Silver": 3, "Bronze": 4}

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

def parse_problems(platform_dir):
    """폴더 구조를 탐색해서 문제 정보 수집"""
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

            # 폴더명에서 번호와 제목 추출
            match = re.match(r"(\d+)\.\s*(.+)", prob_folder)
            if match:
                prob_id, title = match.groups()
            else:
                prob_id, title = prob_folder, prob_folder

            # 문제 폴더 내 .java 파일 기준 마지막 커밋 날짜
            file_for_date = None
            for fname in os.listdir(prob_path):
                if fname.endswith(".java"):
                    file_for_date = os.path.join(prob_path, fname)
                    break

            solved_on = get_last_commit_date(file_for_date if file_for_date else prob_path)

            problems.append({
                "id": prob_id,
                "title": title,
                "tier": difficulty,
                "solved_on": solved_on
            })
    return problems

def sort_problems(problems, by="tier"):
    """문제 리스트 정렬"""
    if by == "tier":
        return sorted(problems, key=lambda x: TIER_ORDER.get(x["tier"], 99))
    elif by == "date":
        return sorted(problems, key=lambda x: x["solved_on"], reverse=True)
    else:
        return problems

def generate_table(problems, platform):
    """문제 리스트를 마크다운 테이블 형태로 변환"""
    lines = []
    for p in problems:
        if platform == "백준":
            link = f"https://www.acmicpc.net/problem/{p['id']}"
        elif platform == "SWEA":
            link = f"https://swexpertacademy.com/main/code/problem/{p['id']}"  # 필요시 수정
        elif platform == "프로그래머스":
            link = f"https://school.programmers.co.kr/learn/courses/30/lessons/{p['id']}"
        else:
            link = "#"

        line = f"| {p['title']} | {p['tier']} | {p['solved_on']} | [Link]({link}) |"
        lines.append(line)

    # 테이블 헤더
    header = "| Problem | Tier | Solved On | Link |"
    separator = "|---------|------|-----------|------|"
    return [header, separator] + lines

def update_section(start_tag, end_tag, lines):
    with open(README_FILE, "r", encoding="utf-8") as f:
        content = f.read()
    new_section = start_tag + "\n" + "\n".join(lines) + "\n" + end_tag
    content = re.sub(f"{start_tag}.*?{end_tag}", new_section, content, flags=re.DOTALL)
    with open(README_FILE, "w", encoding="utf-8") as f:
        f.write(content)

def main():
    for platform, tag in PLATFORMS.items():
        problems = parse_problems(platform)
        problems = sort_problems(problems, by="tier")  # 난이도 순으로 정렬
        table_lines = generate_table(problems, platform)
        end_tag = tag.replace("START", "END")
        update_section(tag, end_tag, table_lines)
        print(f"✅ {platform} 업데이트 완료! 총 {len(problems)}문제 적용됨.")

if __name__ == "__main__":
    main()
