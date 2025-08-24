import os

def generate_baekjoon_table(base_path="baekjoon"):
    """
    백준 문제 목록 테이블을 생성합니다.
    """
    table = "| 난이도 | 문제 번호 | 문제 이름 | 코드 |\n"
    table += "|--------|-----------|-----------|------|\n"

    for root, dirs, files in os.walk(base_path):
        for dir_name in dirs:
            # 예: 'silver/1000_A+B'
            if "_" in dir_name and " " not in dir_name:
                parts = dir_name.split('_', 1)
                problem_number = parts[0]
                problem_name = parts[1].replace('_', ' ')
                difficulty = os.path.basename(root).capitalize()
                link_path = os.path.join(root, dir_name)
                
                table += f"| {difficulty} | [{problem_number}](https://www.acmicpc.net/problem/{problem_number}) | {problem_name} | [풀이]({link_path}/Main.java) |\n"
    return table

def generate_programmers_table(base_path="programmers"):
    """
    프로그래머스 문제 목록 테이블을 생성합니다.
    """
    table = "| 레벨 | 문제 이름 | 링크 | 코드 |\n"
    table += "|------|-----------|------|------|\n"
    
    for root, dirs, files in os.walk(base_path):
        for dir_name in dirs:
            # 예: 'level1/12903_가운데글자가져오기'
            if "_" in dir_name and dir_name.isdigit() == False:
                parts = dir_name.split('_', 1)
                problem_number = parts[0]
                problem_name = parts[1]
                level = os.path.basename(root).capitalize()
                link_path = os.path.join(root, dir_name)

                table += f"| {level} | {problem_name} | [문제](https://school.programmers.co.kr/learn/courses/30/lessons/{problem_number}) | [풀이]({link_path}/Solution.java) |\n"
    return table

def generate_swea_table(base_path="swea"):
    """
    SWEA 문제 목록 테이블을 생성합니다.
    """
    table = "| 난이도 | 문제 번호 | 문제 이름 | 코드 |\n"
    table += "|--------|-----------|-----------|------|\n"
    
    for root, dirs, files in os.walk(base_path):
        for dir_name in dirs:
            if dir_name.isdigit(): # 예: 'D3/1206'
                problem_number = dir_name
                difficulty = os.path.basename(root)
                link_path = os.path.join(root, dir_name)
                
                # SWEA는 문제 이름 추출이 어려워 링크만 제공
                table += f"| {difficulty} | {problem_number} | - | [풀이]({link_path}/Solution.java) |\n"
    return table

def update_readme():
    with open("README.md", "r", encoding="utf-8") as f:
        readme_content = f.read()

    # 각 테이블 시작/끝 주석
    baekjoon_start = ""
    baekjoon_end = ""
    programmers_start = ""
    programmers_end = ""
    swea_start = ""
    swea_end = ""

    # 테이블 생성
    baekjoon_table = generate_baekjoon_table()
    programmers_table = generate_programmers_table()
    swea_table = generate_swea_table()

    # README 내용 업데이트
    new_readme_content = readme_content
    new_readme_content = new_readme_content.replace(readme_content[readme_content.find(baekjoon_start):readme_content.find(baekjoon_end) + len(baekjoon_end)], f"{baekjoon_start}\n{baekjoon_table}{baekjoon_end}")
    new_readme_content = new_readme_content.replace(readme_content[readme_content.find(programmers_start):readme_content.find(programmers_end) + len(programmers_end)], f"{programmers_start}\n{programmers_table}{programmers_end}")
    new_readme_content = new_readme_content.replace(readme_content[readme_content.find(swea_start):readme_content.find(swea_end) + len(swea_end)], f"{swea_start}\n{swea_table}{swea_end}")

    with open("README.md", "w", encoding="utf-8") as f:
        f.write(new_readme_content)

if __name__ == "__main__":
    update_readme()
