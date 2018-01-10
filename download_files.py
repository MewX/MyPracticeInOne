import urllib.request as urllib2

import cate_table
import utils

base_url = "https://ptestudy.com/pratice/detail/?cate={}&pindex={}"
base_dir = "data/"
utils.create_dir_when_not_exist(base_dir)
for problem_type in cate_table.type_table:
    utils.eprint("Now: proceeding cate " + str(problem_type.cate))

    # create directory
    current_dir = base_dir + problem_type.name + "/"
    utils.create_dir_when_not_exist(current_dir)
    for i in range(1, problem_type.max_pindex + 1):
        current_url = base_dir.format(problem_type.cate, i)
        file_name = current_dir + str(problem_type.cate) + "." + str(i) + ".html"

        # get html content
        response = urllib2.urlopen(current_url)
        webContent = response.read()

        # save to file
        f = open(file_name, 'w')
        f.write(webContent)
        f.close()
