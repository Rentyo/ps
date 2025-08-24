import os
import re
import subprocess
from datetime import datetime


# 티어 정규화 함수
def normalize_tier(tier):
if tier.startswith("D") and len(tier) > 1 and tier[1].isdigit():
return tier.upper() # SWEA 티어 (D1~D6, Unrated)
return tier.capitalize() # BOJ (Bronze, Silver, Gold 등)


# 파일별 커밋 날짜 가져오기
def get_commit_date(filepath):
try:
result = subprocess.check_output(
["git", "log", "-1", "--format=%ci", "--", filepath],
text=True
).strip()
return result.split(" ")[0]
except subprocess.CalledProcessError:
return "-"


# 플랫폼별 문제 스캔
def scan_problems(base_dir, platform):
problems = []
for root, _, files in os.walk(base_dir):
for file in files:
if file.endswith(".py") or file.endswith(".cpp") or file.endswith(".java"):
filepath = os.path.join(root, file)
parts = filepath.split(os.sep)


try:
if platform == "BOJ":
_, _, tier, folder = parts[:4]
problem_info = folder
problem_num, problem_name = problem_info.split(". ", 1)
url = f"https://www.acmicpc.net/problem/{problem_num}"
elif platform == "SWEA":
_, _, tier, folder = parts[:4]
problem_name = folder
url = f"https://swexpertacademy.com/main/code/problem/{re.sub('[^0-9]', '', problem_name)}"
elif platform == "Programmers":
_, _, tier, folder = parts[:4]
problem_name = folder
url = f"https://school.programmers.co.kr/learn/courses/30/lessons/{re.sub('[^0-9]', '', problem_name)}"
else:
continue
except Exception:
continue


commit_date = get_commit_date(filepath)
problems.append({
"name": problem_name,
"tier": normalize_tier(tier),
"date": commit_date,
"url": url
})
return problems


# README 갱신
def update_readme():
with open("README.md", "r", encoding="utf-8") as f:
readme_content = f.read()


boj_problems = scan_problems("백준", "BOJ")
swea_problems = scan_problems("SWEA", "SWEA")
prgm_problems = scan_problems("Programmers", "Programmers")


def generate_table(problems):
if not problems:
return ""
grouped = {}
for p in problems:
grouped.setdefault(p["tier"], []).append(p)


section = []
update_readme()
