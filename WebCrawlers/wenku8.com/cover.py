# -*- coding:utf-8 -*-
# Usage:
# python dimg.py
# This tool will skip the existing '*-img.zip' file automatically.
import zipfile
import os
import sys
import socket
import threading
from queue import Queue
import urllib.request as ur

# init
zipFolder = 'dl'
saveFolder = 'covers'
maxThread = 8
if not os.path.exists(zipFolder + os.sep + saveFolder):
    os.mkdir(zipFolder + os.sep + saveFolder)
socket.setdefaulttimeout(15)

def worker():
    while True:
        fileName = q.get()
        
        # do fetching data, open .zip file
        tempName = zipFolder + os.sep + fileName
        tempImageName = zipFolder + os.sep + saveFolder + os.sep + fileName.replace('.', '-img.')
        if os.path.exists(tempImageName):
            print('# Passed ' + tempImageName)
            continue
        zf = zipfile.ZipFile(tempName, 'r')  # in file
        zout = zipfile.ZipFile(tempImageName, 'w')
        print('# Processing ' + fileName + ' (' + threading.current_thread().name + ')')
        
        # go through all file in
        for name in zf.namelist():
            if name.find('.htm') == -1:
                continue  # skip non-htm file, like: *.css
            content = str(zf.read(name))
            #print('    # Processing ' + name + ':')
        
            # find all links
            beg = 0
            lastImage = ''
            while content.find(matcher, beg) != -1:
                inBeg = content.find(matcher, beg)
                inEnd = content.find('"', inBeg)
                url = content[inBeg:inEnd]
                beg = inEnd  # for next loop
                if url == lastImage:
                    continue  # skip downloaded file like this: <a><img/></a>
                #print('        Fetching ' + url + '...')
        
                # begin request
                while True:
                    try:
                        f = ur.urlopen(url)
                        b = f.read()
                    except Exception as e:
                        print(e)
                        #print('      !!Redo')
                        continue
        
                    # add to zip archive
                    zout.writestr(url[url.find('pictures'):], b)
                    break
        
                #print('        ->  Done')
                lastImage = url
            #print('    Done the file: ' + name)
        
        zout.close()
        zf.close()
        print('- Done the file: ' + tempImageName + ' (' + threading.current_thread().name + ')')
        q.task_done()

# create threads
q = Queue()
for i in range(maxThread):
    print('creating: ' + str(i))
    t = threading.Thread(target=worker)
    t.daemon = True  # thread dies when main thread (only non-daemon thread) exits.
    t.start()

# fill queue
lstFile = range(1, 2011)
for item in lstFile:
    q.put(item)

q.join()  # wait for ending

'''
# go through all the file in target folder
for fileName in lstFile:
    # open .zip file
    tempName = zipFolder + os.sep + fileName
    tempImageName = zipFolder + os.sep + saveFolder + os.sep + fileName.replace('.', '-img.')
    if os.path.exists(tempImageName):
        print('# Passed ' + tempImageName)
        continue
    zf = zipfile.ZipFile(tempName, 'r')  # in file
    zout = zipfile.ZipFile(tempImageName, 'w')
    print('# Processing ' + fileName + ':')

    # go through all file in
    for name in zf.namelist():
        if name.find('.htm') == -1:
            continue  # skip non-htm file, like: *.css
        content = str(zf.read(name))
        print('    # Processing ' + name + ':')

        # find all links
        beg = 0
        lastImage = ''
        while content.find(matcher, beg) != -1:
            inBeg = content.find(matcher, beg)
            inEnd = content.find('"', inBeg)
            url = content[inBeg:inEnd]
            beg = inEnd  # for next loop
            if url == lastImage:
                continue  # skip downloaded file like this: <a><img/></a>
            print('        Fetching ' + url + '...')

            # begin request
            while True:
                try:
                    f = ur.urlopen(url)
                    b = f.read()
                except Exception as e:
                    print(e)
                    print('      !!Redo')
                    continue

                # add to zip archive
                zout.writestr(url[url.find('pictures'):], b)
                break

            print('        ->  Done')
            lastImage = url
        print('    Done the file: ' + name)

    zout.close()
    zf.close()
    print('- Done the file: ' + tempImageName)
'''