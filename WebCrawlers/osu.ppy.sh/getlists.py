# this file is used for getting pack lists and store them in the database
import re
import socket
import urllib.request as urllib2

# the list can be get from: https://osu.ppy.sh/p/packlist?t=s
import settings
from property_db import PropertyDb

FULL_LIST = 'https://osu.ppy.sh/p/packlist?t=s'
LIST_PATTERN = r'"expandPack\(\'(.+?)\'\)"'
MAP_URL_PATTERN = 'https://osu.ppy.sh/pages/include/packlist-info.php?n={}'
PAGE_PATTERN = r'<h2>(.+?)</h2>.+?<div>.+?href="(.+?)">'  # group 1 - file name; group 2 - link
socket.setdefaulttimeout(15)

"""
download a page by passed url
"""
def download(url):
    while True:
        try:
            opener = urllib2.build_opener()
            opener.addheaders.append(('Cookie', settings.COOKIES))
            opener.addheaders.append(('User-Agent', settings.USER_AGENT))
            f = opener.open(url)
            b = f.read()
        except Exception as e:
            print(e)
            print('-- trying again: {}'.format(url))
            continue
        return b.decode("utf-8")


"""
the main function
"""
list_page = download(FULL_LIST)
print(list_page)
propertyDb = PropertyDb()
for match in re.finditer(LIST_PATTERN, list_page, re.DOTALL | re.MULTILINE):
    key = match.group(1)
    print('working on pack: {}'.format(key))

    # if this pack is not downloaded
    if propertyDb.does_record_exist(key):
        continue

    current_page = download(MAP_URL_PATTERN.format(key))
    page_matcher = re.search(PAGE_PATTERN, current_page, re.DOTALL | re.MULTILINE)

    if page_matcher:
        propertyDb.save_or_overwrite_data(key, current_page)
    else:
        # if not found
        print('-- ERROR not found things on page on key {}: {}'.format(key, current_page))

propertyDb.close()
print('done')
