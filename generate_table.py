import re
import urllib.request as urllib2

import utils

# Usage: python generate_table.py > cate_table.py
cate_list = [
    22, 23, 24, 25, 26,
    28, 29,
    31, 32,
    34, 35, 36, 37
]

print("""class Type:
    name = ""
    cate = 0
    max_pindex = 0

    def __init__(self, name, cate, max_pindex):
        self.name = name.lower().replace(" ", "_")
        self.cate = cate
        self.max_pindex = max_pindex

""")

print("type_table = [")
base_url = "https://ptestudy.com/pratice/detail/?cate="
for cate in cate_list:
    current_url = base_url + str(cate)

    # do request
    utils.eprint("Doing cate: " + str(cate))
    response = urllib2.urlopen(current_url)
    html = response.read().decode('utf-8')

    # regex match
    reobj = re.compile(r'<div class="pe-yw">(.+?)<.+?>(\d+)</option>\s*?</select>', re.DOTALL | re.MULTILINE)
    match = reobj.search(html)
    if match:
        print('    Type("{}", {}, {}),'.format(match.group(1), cate, match.group(2)))
    else:
        print("Not found for cate " + str(cate))

print("]")
