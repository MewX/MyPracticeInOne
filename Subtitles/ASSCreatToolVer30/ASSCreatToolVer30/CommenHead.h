#pragma once

#include <sdkddkver.h>
#define _WIN32_WINNT_WINXP 0x0501

#include <stdio.h>
#include <tchar.h>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cmath>
#include <stdlib.h>
#define PI M_PI
#define asc1 65
#define asc2 97;
using namespace std;

static const char Version[5]="3.0";
static const short KaraokeLineAmount=500;
static const short SingleWordLenth=20;
static const short AllSentenceAmount=40;
static const short WordsInLineAmount=30;
static short Height=408;
static short Width=720;
static short WordSize=30;
static short EnglishWordWidthB[26]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//大写
static short EnglishWordWidthL[26]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//小写
static short ChineseWordWidth=0;

/*time计算公用*/
static short AccentuationTime=60;//强调需要的时间(单位：1)
static short AppearanceAlternation=15;//字与字的标准间隔时=第二个字的出现-第一个字的出现=第二个字的退出-第二个字的退出(单位：毫秒)
static short TimeToEnter=25;//进入所需要的时间
static short TimeToStart=20;//进入结束的时间与开始强调的时间间隔
static short TimeToLeave=20;//最后一个字强调结束的时间与开始退出的最短时间间隔
static short TimeToDisappear=25;//退出所需要的时间
/*pos计算公用*/
static short EdgeDistanceX=0;//X方向边距30
static short EdgeDistanceY=0;//Y方向边距20

static short LineAmount;//句子总数
static char WordDetailJP[AllSentenceAmount][WordsInLineAmount][SingleWordLenth];//存储JP字符细节
static char WordDetailCN[AllSentenceAmount][WordsInLineAmount][SingleWordLenth];//存储CN字符细节
static short AmountJP[AllSentenceAmount];//存储每句JP话分割的字数
static short AmountCN[AllSentenceAmount];//存储每句CN话分割的字数
static long TimeJP[AllSentenceAmount][WordsInLineAmount][6];//存储整型时间[JP];format[sentence][word][menu]
static long TimeCN[AllSentenceAmount][WordsInLineAmount][6];//存储整型时间[CN];format[sentence][word][menu]
static short WordLenthJP[AllSentenceAmount][WordsInLineAmount];//存储每个JP字的像素长度
static short WordLenthCN[AllSentenceAmount][WordsInLineAmount];//存储每个CN字的像素长度
static short LineLenthJP[AllSentenceAmount];//存储每句JP话的像素长度
static short LineLenthCN[AllSentenceAmount];//存储每句CN话的像素长度
static short PosJP[AllSentenceAmount][WordsInLineAmount][2];//存储位置[JP];format[sentence][word][pos]
static short PosCN[AllSentenceAmount][WordsInLineAmount][2];//存储位置[CN];format[sentence][word][pos]

static fstream ass_originCN;
static fstream ass_originJP;
static fstream ass_originJPrm;
static fstream ass_originROCK;
static char openfileCN[100];
static char openfileJP[100];
static char openfileJPrm[100];
static char openfileROCK[100];
static fstream ass_out;
static char savefile[100];
static fstream ass_in;
static char karaokefile[100];
static fstream ass_temp;
static char tempfile[100];


#include "Structures\[C]DATA.h"
static DATA all[KaraokeLineAmount];
#include "Structures\[C]ScriptInfo.h"

#include "Core\[C]ASScodeCore.h"

#include "UsefulFunction\[F]RAND.h"
#include "UsefulFunction\[F]KCreator.h"
#include "FilterDLL\VSFilter.h"
#include "FilterDLL\VSFilterMod.h"

#include "Animation\AllAnimation.h"
//#include "Test\TestList.h"