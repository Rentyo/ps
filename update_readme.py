import os
import re
import subprocess
from datetime import datetime

README_FILE = "README.md"

PLATFORMS = {
    "백준": "<!-- BOJ_START -->",
    "SWEA": "<!-- SWEA_START -->",
    "프로그래머스": "<!-- PRGM_START -->",  # 나중에 추가 가능
}

def get_last_commit_date(file_path):
    """git log를 이용해 마지막 커밋 날짜 가져오기"""
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

            # 마지막 커밋 날짜 가져오기 (폴더 내 README.md 우선)
            readme_path = os.path.join(prob_path, "README.md")
            solved_on = get_last_commit_date(readme_path if os.path.exists(readme_path) else prob_path)

            problems.append({
                "id": prob_id,
                "title": title,
                "tier": difficulty,
                "solved_on": solved_on
            })
    return problems

def update_section(start_tag, end_tag, lines):
    with open(README_FILE, "r", encoding="utf-8") as f:
        content = f.read()
    new_section = start_tag + "\n" + "\n".join(lines) + "\n" + end_tag
    content = re.sub(f"{start_tag}.*?{end_tag}", new_section, content, flags=re.DOTALL)
    with open(README_FILE, "w", encoding="utf-8") as f:
        f.write(content)

def generate_table(problems, platform):
    """문제 리스트를 마크다운 테이블 형태로 변환"""
    lines = []
    for p in problems:
        if platform == "백준":
            link = f"https://www.acmicpc.net/problem/{p['id']}"
        elif platform == "SWEA":
            link = f"https://swexpertacademy.com/main/code/problem/{p['id']}"  # 필요시 수정
        else:
            link = "#"  # 프로그래머스는 나중에 링크 연결 가능
        line = f"| [{p['title']}]({link}) | {p['tier']} | {p['solved_on']} | [Link]({link}) |"
        lines.append(line)
    return lines

def main():
    for platform, tag in PLATFORMS.items():
        dir_path = platform
        problems = parse_problems(dir_path)
        lines = generate_table(problems, platform)
        end_tag = tag.replace("START", "END")
        update_section(tag, end_tag, lines)
        print(f"✅ {platform} 업데이트 완료! 총 {len(problems)}문제 적용됨.")

if __name__ == "__main__":
    main()
