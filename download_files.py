import urllib.request as urllib2

import cate_table
import utils

# TODO: update mode for updating questions
PAUSE_MODE = False  # if True, the following two variable will be used for the new starting position
PAUSE_CATE = 0
PAUSE_PINDEX = 0

base_url = "https://ptestudy.com/pratice/detail/?cate={}&pindex={}"
base_dir = "data/"
utils.create_dir_when_not_exist(base_dir)
for problem_type in cate_table.type_table:
    utils.eprint("Now proceeding cate " + str(problem_type.cate))

    # create directory
    current_dir = base_dir + problem_type.name + "/"
    utils.create_dir_when_not_exist(current_dir)
    for i in range(1, problem_type.max_pindex + 1):
        current_url = base_url.format(problem_type.cate, i)
        file_name = current_dir + str(problem_type.cate) + "." + str(i) + ".html"

        # check pause or not
        if PAUSE_MODE:
            if PAUSE_CATE == problem_type.cate and PAUSE_PINDEX <= i:
                # exit pause mode
                PAUSE_MODE = False
            # otherwise
            continue

        # get html content
        utils.eprint("Now downloading " + file_name)
        response = urllib2.urlopen(current_url)
        webContent = response.read()

        # save to file
        f = open(file_name, 'wb')
        f.write(webContent)
        f.close()
