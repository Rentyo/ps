import os
import re
import subprocess
from collections import defaultdict
from datetime import datetime


README_PATH = "README.md"


# 난이도 매핑 (백준 기준)
boj_tiers = ["Bronze", "Silver", "Gold", "Platinum", "Diamond"]


# SWEA 난이도 매핑 (D1~D6, Unrated)
def map_swea_tier(tier: str) -> str:
if tier.startswith("D") and tier[1].isdigit():
num = int(tier[1])
if num <= 2:
return "Bronze"
elif num <= 4:
return "Silver"
else:
return "Gold"
return "Unrated"




def get_commit_date(file_path):
try:
result = subprocess.run(
["git", "log", "-1", "--format=%ci", "--", file_path],
capture_output=True, text=True, check=True
)
return result.stdout.strip().split(" ")[0]
except Exception:
return "-"




def parse_problems(base_dir, platform):
problems = []
if not os.path.exists(base_dir):
return problems


for tier in os.listdir(base_dir):
tier_path = os.path.join(base_dir, tier)
if not os.path.isdir(tier_path):
continue


for problem in os.listdir(tier_path):
problem_path = os.path.join(tier_path, problem)
if not os.path.isdir(problem_path):
continue


match = re.match(r"(\d+)\. (.+)", problem)
if not match:
continue


num, name = match.groups()
if platform == "BOJ":
url = f"https://www.acmicpc.net/problem/{num}"
elif platform == "SWEA":
url = f"https://swexpertacademy.com/main/code/problem/{num}"
else:
url = f"https://programmers.co.kr/learn/courses/{num}"


solved_date = get_commit_date(problem_path)
tier_mapped = tier if platform != "SWEA" else map_swea_tier(tier)


problems.append({
"num": num,
"name": name,
"tier": tier,
"tier_mapped": tier_mapped,
"platform": platform,
"url": url,
"date": solved_date,
})
return problems




def group_by_tier(problems):
grouped = defaultdict(list)
for p in problems:
grouped[p["tier"]].append(p)
return grouped




def compute_statistics(all_problems):
stats = defaultdict(lambda: defaultdict(int))
for platform, problems in all_problems.items():
main(
