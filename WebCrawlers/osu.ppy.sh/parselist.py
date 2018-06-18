# extract all list from the database
import re

from getlists import PAGE_PATTERN
from property_db import PropertyDb

propertyDb = PropertyDb()

for record in propertyDb.get_all():
    current_page = record[1]
    page_matcher = re.search(PAGE_PATTERN, current_page, re.DOTALL | re.MULTILINE)

    if page_matcher:
        file_name = page_matcher.group(1)
        download_url = page_matcher.group(2)
        if 'mediafire' not in download_url:
            print('-- ERROR not found mediafire on page on key {}: {}'.format(record[0], current_page))

        # output for batch
        print('{} - {}'.format(file_name, download_url))

    else:
        # if not found
        print('-- ERROR not found things on page on key {}: {}'.format(record[0], current_page))


propertyDb.close()

