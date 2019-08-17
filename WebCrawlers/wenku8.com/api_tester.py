# -*- coding:utf-8 -*-
"""
WSGI config for treeol project.

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/1.6/howto/deployment/wsgi/
"""

import os, urllib2, urllib, sys, base64, time
'''
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "treeol.settings")

from django.core.wsgi import get_wsgi_application
application = get_wsgi_application()

HELLO_WORLD = b"Hello world!\n"


# callable function
def application(environ, start_response):
    return [HELLO_WORLD]
'''

HELLO_WORLD = b"Hello world!\nMewX"
BaseURL = "http://app.wenku8.cn/android.php"

def getTimeStamp( ):
    return str(int(time.time()));

def getResult( postData,isReal):
    ''' 'isReal' get False -> return args; else return results; '''
    if len(postData) == 0: return 'postData is NULL';
    postData += '&timetoken='+getTimeStamp()
    req = urllib2.Request(BaseURL, 'request='+base64.b64encode(postData));
    if not isReal: return postData + "\nrequest=" + base64.b64encode(postData); # return  args
    
    ret = "";
    fd = urllib2.urlopen(req);
    while 1:
        data = fd.read(1024)
        if not len(data):
            break
        ret += data;
    return ret;


'''
所有请求以UTF-8编码发送过来的 使用base64加密 post方式过来，格式如 request=xxxxxx
post过来的request操作里面最后面都包含一个时间戳(timetoken=xxxxxxx) 防止缓存

发送来的request有以下这些请求信息
下面列表里面 凡是后面带有 &t=1 这个参数的，就是可以将查询的信息以 “台湾繁体” 返回给你，简体t=0（或不带此参数）

action=book&do=cover&aid=1400 //小说封面
action=book&do=info&aid=3&t=1 //小说信息
    小说基本信息(书名、作者、更新日期、状态(连载=0 完结＝1)、简介(这里是缩略的简介，wp8app是配合小说总列表使用的短简介)，
    这里提供是小说基本信息是不涉及数据库查询的，不会加重数据库负担)
action=book&do=intro&aid=3&t=1 //小说介绍 这个是完整简介
action=book&do=meta&aid=3&t=1 //小说数据 (具体的小说数据，比如点击量、收藏量、最新章节等等，这里的数据是查询数据库的)
action=book&do=list&aid=1417&t=1 //小说目录
action=book&do=text&aid=1239&cid=47062&t=1 //小说正文

action=book&do=vote&aid=1239 //推荐小说  (就是网站上面那个喜欢小说 就推一下那个，app日限制5次/需要登录账号)

action=search&searchtype=articlename&searchkey=d&t=1 //小说搜索
    （searchtype条件分为articlename和author t=1的时候可以使用繁体字作为关键词进行搜索）

action=articlelist&sort=allvote&page=1 //小说列表 page不得为空 (wp app在使用 纯返回书本id)
action=novellist&sort=allvote&page=1&t=1 //小说列表2 page不得为空（ios app在使用 返回书本和点击量、收藏量信息）
    (sort分成数字和字母两类，字母为最新更新、点击量分类之类的，
    使用的字母就是文库网页上的字母(http://www.wenku8.cn/modules/article/toplist.php?sort=lastupdate)，
    使用数字的话，则是按照小说所属文库进行分类，字数与文库对应列表请看下面的xml那个)

action=xml&item=2012&t=1 //xml item=对应xml文件名(专题为special、文库分类为sort) 


除此外，还有这些纯数字返回信息
0 请求发生错误
1 成功(登陆、添加、删除、发帖)
2 用户名错误
3 密码错误
4 请先登陆
5 已经在书架
6 书架已满
7 小说不在书架
8 回复帖子主题不存在
9 签到失败
10 推荐失败
11 帖子发送失败
22 refer page 0
'''
def application(environ, start_response):
    status = '200 OK'
    response_headers = [('Content-type', 'text/plain')]
    start_response(status, response_headers)
    
    ##########
    # Here test: action=book&do=cover&aid=1400 //小说封面
    ##########
    ReqTest01 = 'action=book&do=cover&aid=1305'
    #return getResult( ReqTest01, False ); # This get JPG binary file
    
    
    ##########
    # Here test: action=book&do=info&aid=3&t=1 //小说信息(不会加重数据库负担)
    ##########
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <metadata>
    <data name="Title" aid="1305"><![CDATA[絕對雙刃absolute duo]]></data>
    <data name="Author" value="柊★巧"/>
    <data name="BookStatus" value="0"/>
    <data name="LastUpdate" value="2014-10-01"/>
    <data name="IntroPreview"><![CDATA[　　「焰牙」——那是藉由超化之後的精神力將自身靈...]]></data>
    </metadata>'''; # action=book&do=info&aid=1306&t=1
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <metadata>
    <data name="Title" aid="1305"><![CDATA[绝对双刃absolute duo]]></data>
    <data name="Author" value="柊★巧"/>
    <data name="BookStatus" value="0"/>
    <data name="LastUpdate" value="2014-10-01"/>
    <data name="IntroPreview"><![CDATA[　　「焰牙」——那是藉由超化之后的精神力将自身灵...]]></data>
    </metadata>'''; # action=book&do=info&aid=1305&t=0
    ReqTest02 = 'action=book&do=info&aid=1305&t=0'
    #return getResult( ReqTest02, True );
    
    
    ##########
    # Here test: action=book&do=intro&aid=3&t=1 //小说介绍 这个是完整简介
    ##########
    '''
    　　在劍與魔法作為一股強大力量的世界裡，克雷歐過著只有繪畫是唯一生存意義的孤獨生活。
    　　不過生於名門的他，為了取得繼承人資格必須踏上試煉之旅。
    　　踏入禁忌森林的他，遇見一名半人半植物的魔物。
    　　輕易被抓的克雷歐設法勾起少女的興趣得到幫助，卻又被她當成寵物一般囚禁起來。
    　　兩人從此展開不可思議的同居時光，這樣的生活令他感到很安心。
    　　但平靜的日子沒有持續太久……
    　　描繪人與魔物的戀情，溫暖人心的奇幻故事。'''; # leading-spaces
    ReqTest03 = 'action=book&do=intro&aid=1306&t=1'
    #return getResult( ReqTest03, True );
    
    
    ##########
    # Here test: action=book&do=meta&aid=3&t=1 //小说数据
    # (具体的小说数据，比如点击量、收藏量、最新章节等等，这里的数据是查询数据库的)
    ##########
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <metadata>
    <data name="Title" aid="1306"><![CDATA[向森之魔物献上花束(向森林的魔兽少女献花)]]></data>
    <data name="Author" value="小木君人"/>
    <data name="DayHitsCount" value="26"/>
    <data name="TotalHitsCount" value="43984"/>
    <data name="PushCount" value="1735"/>
    <data name="FavCount" value="848"/>
    <data name="PressId" value="小学馆" sid="10"/>
    <data name="BookStatus" value="已完成"/>
    <data name="BookLength" value="105985"/>
    <data name="LastUpdate" value="2012-11-02"/>
    <data name="LatestSection" cid="41897"><![CDATA[第一卷 插图]]></data>
    </metadata>'''; # action=book&do=meta&aid=1306&t=0
    ReqTest04 = 'action=book&do=meta&aid=1306&t=0'
    #return getResult( ReqTest04, True );
    
    
    ##########
    # Here test: action=book&do=list&aid=1417&t=1 //小说目录
    ##########
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <package>
    <volume vid="41748"><![CDATA[第一卷 告白于苍刻之夜]]>
    <chapter cid="41749"><![CDATA[序章]]></chapter>
    <chapter cid="41750"><![CDATA[第一章「去对我的『楯』说吧——」]]></chapter>
    <chapter cid="41751"><![CDATA[第二章「我真的对你非常感兴趣」]]></chapter>
    <chapter cid="41752"><![CDATA[第三章「揍我吧！」]]></chapter>
    <chapter cid="41753"><![CDATA[第四章「下次，再来喝苹果茶」]]></chapter>
    <chapter cid="41754"><![CDATA[第五章「这是约定」]]></chapter>
    <chapter cid="41755"><![CDATA[第六章「你的背后——由我来守护！」]]></chapter>
    <chapter cid="41756"><![CDATA[第七章「茱莉——爱交给你！」]]></chapter>
    <chapter cid="41757"><![CDATA[尾声]]></chapter>
    <chapter cid="41758"><![CDATA[后记]]></chapter>
    <chapter cid="41759"><![CDATA[插图]]></chapter>
    </volume>
    <volume vid="45090"><![CDATA[第二卷 谎言、真相与赤红]]>
    <chapter cid="45091"><![CDATA[序章]]></chapter>
    <chapter cid="45092"><![CDATA[第一章「莉莉丝·布里斯托」]]></chapter>
    <chapter cid="45093"><![CDATA[第二章「借你的话来说就是……」]]></chapter>
    <chapter cid="45094"><![CDATA[第三章「这真是个好提议」]]></chapter>
    <chapter cid="45095"><![CDATA[第四章「如守护骑士一般」]]></chapter>
    <chapter cid="45096"><![CDATA[第五章「『咬龙战』，开始！」]]></chapter>
    <chapter cid="45097"><![CDATA[第六章「超越人类的存在」]]></chapter>
    <chapter cid="45098"><![CDATA[第七章「『灵魂』」]]></chapter>
    <chapter cid="45099"><![CDATA[尾声]]></chapter>
    <chapter cid="45100"><![CDATA[后记]]></chapter>
    <chapter cid="45105"><![CDATA[插图]]></chapter>
    </volume>
    ...... ......
    </package>''';
    ReqTest05 = 'action=book&do=list&aid=1305&t=0'
    #return getResult( ReqTest05, True );
    
    
    ##########
    # Here test: action=book&do=text&aid=1239&cid=47062&t=0 //小说正文
    ##########
    '''
    第一卷 告白于苍刻之夜 插图 
    ...... ......
    <!--image-->http://pic.wenku8.cn/pictures/1/1305/41759/50471.jpg<!--image-->
    <!--image-->http://pic.wenku8.cn/pictures/1/1305/41759/50472.jpg<!--image-->
    <!--image-->http://pic.wenku8.cn/pictures/1/1305/41759/50473.jpg<!--image-->
    ...... ......
    '''; # action=book&do=text&aid=1305&cid=41759&t=0
    # The others will return the whole content back
    ReqTest06 = 'action=book&do=text&aid=1305&cid=45095&t=0'
    #return getResult( ReqTest06, True );
    
    
    ##########
    # Here test: action=book&do=vote&aid=1239 //推荐小说
    # (就是网站上面那个喜欢小说 就推一下那个，app日限制5次/需要登录账号)
    ##########
    ReqTest07 = ''
    #return getResult( ReqTest07, True );
    
    
    ##########
    # Here test: action=search&searchtype=articlename&searchkey=d&t=0 //小说搜索
    #（searchtype条件分为articlename和author t=1的时候可以使用繁体字作为关键词进行搜索）
    ##########
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <result>
    <item aid='1699'/>
    <item aid='1638'/>
    <item aid='1293'/>
    <item aid='977'/>
    <item aid='693'/>
    <item aid='993'/>
    <item aid='333'/>
    <item aid='499'/>
    <item aid='826'/>
    </result>'''; # action=search&searchtype=articlename&searchkey=Art&t=0 有额外空格和换行
    ReqTest08 = 'action=search&searchtype=articlename&searchkey='+urllib.quote('妹')+'&t=0' # UTF-8汉字这样处理
    ReqTest08 = 'action=search&searchtype=author&searchkey='+urllib.quote('田中罗密欧')+'&t=0' # author 搜索
    #return getResult( ReqTest08, True );
    
    
    ##########
    # Here test: action=articlelist&sort=allvote&page=1 //小说列表
    # (wp app在使用 纯返回书本id  page不得为空)
    ##########
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <result>
    <page num='166'/>
    <item aid='1143'/>
    <item aid='1034'/>
    <item aid='1213'/>
    <item aid='1'/>
    <item aid='1011'/>
    <item aid='1192'/>
    <item aid='433'/>
    <item aid='47'/>
    <item aid='7'/>
    <item aid='374'/>
    </result>'''; # there returns a "Page Num" first
    ReqTest09 = 'action=articlelist&sort=allvote&page=1'
    # sort arguments:
    #   allvisit 总排行榜; allvote 总推荐榜; monthvisit 月排行榜; monthvote 月推荐榜;
    #   weekvisit 周排行榜; weekvote 周推荐榜; dayvisit 日排行榜; dayvote 日推荐榜;
    #   postdate 最新入库; lastupdate 最近更新; goodnum 总收藏榜; size 字数排行;
    #   fullflag 完结列表
    #return getResult( ReqTest09, True );
    
    
    ##########
    # Here test: action=novellist&sort=allvote&page=1&t=1 //小说列表2
    # （ios app在使用 返回书本和点击量、收藏量信息） page不得为空
    ##########
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <result>
    <page num='166'/>
    
    <item aid='1143'>
    <data name='Title'><![CDATA[约会大作战(DATE A LIVE)]]></data>
    <data name='TotalHitsCount' value='2200395'/>
    <data name='PushCount' value='164396'/>
    <data name='FavCount' value='15114'/>
    </item>
    
    <item aid='1034'>
    <data name='Title'><![CDATA[恶魔高校DxD(High School DxD)]]></data>
    <data name='TotalHitsCount' value='2316361'/>
    <data name='PushCount' value='153422'/>
    <data name='FavCount' value='14416'/>
    </item>
    ...... ......
    </result>'''
    ReqTest10 = 'action=novellist&sort=allvote&page=1&t=0'
    #return getResult( ReqTest10, True );
    
    
    ##########
    # Here test: action=xml&item=2012&t=1 //xml item=对应xml文件名
    # (专题为special、文库分类为sort) 
    ##########
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <metadata>
      <item sort="1">电击文库</item>
      <item sort="2">富士见文库</item>
      <item sort="3">角川文库</item>
      <item sort="4">MF文库J</item>
      <item sort="5">Fami通文库</item>
      <item sort="6">GA文库</item>
      <item sort="7">HJ文库</item>
      <item sort="8">一迅社</item>
      <item sort="9">集英社</item>
      <item sort="10">小学馆</item>
      <item sort="11">讲谈社</item>
      <item sort="12">少女文库</item>
      <item sort="13">其他文库</item>
      <item sort="14">游戏剧本</item>
    </metadata> '''; # action=xml&item=sort&t=0
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <metadata>
    <topic>
    <name><![CDATA[这本轻小说真厉害！]]></name>
    <collection>
    <latest path="2014">2014</latest>
    <latest path="2013">2013</latest>
    <digest path="2012">2012</digest>
    <digest path="2011">2011</digest>
    <digest path="2010">2010</digest>
    <digest path="2009">2009</digest>
    <digest path="2008">2008</digest>
    <digest path="2007">2007</digest>
    <digest path="2006">2006</digest>
    <digest path="2005">2005</digest>
    </collection>
    <desc><![CDATA[每年日本宝岛社都会根据读者票选，选出年度轻小说奖，命名为“这本轻小说真厉害！”]]></desc>
    </topic>
    </metadata> '''; # action=xml&item=special&t=0
    '''
    <?xml version="1.0" encoding="utf-8"?>
    <result>
    <item aid="102"><![CDATA[凉宫春日物语]]></item>
    <item aid="17"><![CDATA[戏言系列]]></item>
    <item aid="165"><![CDATA[恶魔同盟]]></item>
    <item aid=""><![CDATA[流血女神传]]></item>
    <item aid="218"><![CDATA[大骚动(永生之酒)]]></item>
    <item aid="82"><![CDATA[七姬物语]]></item>
    <item aid="59"><![CDATA[天空之钟 响彻惑星]]></item>
    <item aid="19"><![CDATA[艾莉森]]></item>
    <item aid="89"><![CDATA[扑杀天使]]></item>
    <item aid="304"><![CDATA[混沌军团]]></item>
    <item aid="26"><![CDATA[终焉的年代记]]></item>
    <item aid="36"><![CDATA[银盘万花]]></item>
    <item aid=""><![CDATA[Dクラッカーズ]]></item>
    <item aid=""><![CDATA[楽園の魔女たち]]></item>
    <item aid="255"><![CDATA[圣母在上(玛莉亚的凝望)]]></item>
    <item aid="40"><![CDATA[仰望半月的夜空]]></item>
    <item aid="8"><![CDATA[全金属狂潮(惊爆危机)]]></item>
    <item aid="712"><![CDATA[罪人与龙共舞]]></item>
    <item aid="129"><![CDATA[吉永家的石像怪]]></item>
    <item aid="112"><![CDATA[空之境界]]></item>
    </result>'''; # action=xml&item=2005&t=0 注意，有空值！！！
    ReqTest10 = 'action=xml&item=2005&t=0'; # 查上面的这些年份也可以的，special相当于目录
    return getResult( ReqTest10, True );
    

    return [HELLO_WORLD] # for safe
    