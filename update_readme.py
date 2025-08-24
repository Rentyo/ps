import os
import re

def generate_baekjoon_table(base_path="백준"):
    """
    백준 문제 목록 테이블을 생성합니다.
    디렉토리 구조: 백준/난이도/문제번호. 문제 이름/문제 이름.java
    """
    table = "| 난이도 | 문제 번호 | 문제 이름 | 코드 |\n"
    table += "|--------|-----------|-----------|------|\n"

    # 난이도별로 정렬하기 위한 순서 (선택 사항)
    difficulty_order = ['Diamond', 'Platinum', 'Gold', 'Silver', 'Bronze', 'Unranked']
    
    # 난이도 폴더를 찾기 위해 base_path를 탐색
    difficulties = []
    if os.path.exists(base_path):
        difficulties = [d for d in os.listdir(base_path) if os.path.isdir(os.path.join(base_path, d))]
        # 미리 정의된 순서에 따라 정렬
        difficulties.sort(key=lambda d: difficulty_order.index(d) if d in difficulty_order else len(difficulty_order))

    for difficulty in difficulties:
        difficulty_path = os.path.join(base_path, difficulty)
        
        # 문제 폴더를 찾기 위해 난이도 폴더를 탐색
        problems = []
        if os.path.exists(difficulty_path):
            problems = [p for p in os.listdir(difficulty_path) if os.path.isdir(os.path.join(difficulty_path, p))]
            problems.sort() # 문제 번호 순으로 정렬

        for problem_dir in problems:
            # "문제번호. 문제 이름"에서 문제 번호와 이름 추출
            match = re.match(r'(\d+)\.\s*(.*)', problem_dir)
            if match:
                problem_number = match.group(1)
                problem_name = match.group(2)
                
                # 풀이 파일 경로 생성
                # 예: 백준/Gold/1000. A+B/A+B.java
                code_path = os.path.join(difficulty_path, problem_dir, f"{problem_name.replace(' ', '')}.java")
                
                # 파일이 실제로 존재하는지 확인
                if os.path.exists(code_path):
                    table += f"| {difficulty} | [{problem_number}](https://www.acmicpc.net/problem/{problem_number}) | {problem_name} | [풀이](./{code_path.replace(os.sep, '/')}) |\n"

    return table

def update_readme():
    with open("README.md", "r", encoding="utf-8") as f:
        readme_content = f.read()

    # 백준 테이블 시작/끝 주석
    baekjoon_start = ""
    baekjoon_end = ""

    # 테이블 생성
    baekjoon_table = generate_baekjoon_table()

    # README 내용 업데이트
    new_readme_content = readme_content
    # 기존 백준 테이블을 새로운 내용으로 교체
    if baekjoon_start in new_readme_content and baekjoon_end in new_readme_content:
        start_index = new_readme_content.find(baekjoon_start) + len(baekjoon_start)
        end_index = new_readme_content.find(baekjoon_end)
        new_readme_content = new_readme_content[:start_index] + "\n" + baekjoon_table + new_readme_content[end_index:]

    with open("README.md", "w", encoding="utf-8") as f:
        f.write(new_readme_content)

if __name__ == "__main__":
    update_readme()
