import os

BASE_DIR = "."  # 저장소 루트 디렉토리
README_FILE = "README.md"


def parse_problem_name(dirname):
    """디렉토리 이름에서 문제 번호/이름 분리"""
    if "_" in dirname:
        num, name = dirname.split("_", 1)
        return num, name
    return None, dirname


def generate_table(root_dir, site):
    """문제 목록 표 생성"""
    rows = []
    counts = {}  # 난이도/레벨 카운트

    for level in sorted(os.listdir(root_dir)):
        level_path = os.path.join(root_dir, level)
        if not os.path.isdir(level_path):
            continue
        for problem in sorted(os.listdir(level_path)):
            num, name = parse_problem_name(problem)
            code_path = os.path.join(root_dir, level, problem)
            if site == "baekjoon":
                rows.append(
                    f"| {level} | [{num}](https://www.acmicpc.net/problem/{num}) | {name} | [풀이]({code_path}) |"
                )
            elif site == "programmers":
                rows.append(
                    f"| {level} | {name} | [문제](https://school.programmers.co.kr/learn/courses/30/lessons/{num}) | [풀이]({code_path}) |"
                )
            elif site == "swea":
                rows.append(
                    f"| {level} | {num} | {name} | [풀이]({code_path}) |"
                )

            counts[level] = counts.get(level, 0) + 1

    return rows, counts


def main():
    readme_content = [
        "# 📝 Algorithm Study Log\n\n",
        "백준허브를 통해 자동 저장되는 문제 풀이 기록입니다.\n\n",
        "## 📊 풀이 현황\n"
    ]

    sites = {
        "baekjoon": "🔵 Baekjoon",
        "programmers": "🟢 Programmers",
        "swea": "🟠 SWEA"
    }

    total_counts = {}

    # 현황 먼저 생성
    for site, title in sites.items():
        if not os.path.exists(site):
            continue
        _, counts = generate_table(site, site)
        total = sum(counts.values())
        detail = " / ".join([f"{k}: {v}" for k, v in counts.items()])
        readme_content.append(f"- **{title}** : {total}문제 ({detail})\n")
        total_counts[site] = (total, counts)

    readme_content.append("\n---\n\n## 📂 사이트별 문제 모음\n\n")

    # 상세 표 생성
    for site, title in sites.items():
        if not os.path.exists(site):
            continue
        rows, _ = generate_table(site, site)
        readme_content.append(f"### {title}\n")
        if site == "baekjoon":
            readme_content.append("| 난이도 | 문제 번호 | 문제 이름 | 코드 |\n")
            readme_content.append("|--------|-----------|-----------|------|\n")
        elif site == "programmers":
            readme_content.append("| 레벨 | 문제 이름 | 링크 | 코드 |\n")
            readme_content.append("|------|-----------|------|------|\n")
        elif site == "swea":
            readme_content.append("| 난이도 | 문제 번호 | 문제 이름 | 코드 |\n")
            readme_content.append("|--------|-----------|-----------|------|\n")
        readme_content.extend([row + "\n" for row in rows])
        readme_content.append("\n")

    with open(README_FILE, "w", encoding="utf-8") as f:
        f.writelines(readme_content)


if __name__ == "__main__":
    main()
