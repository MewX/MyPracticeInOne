#pragma once
#include "targetver.h"
#include <stdio.h>
#include <tchar.h>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cmath>
#include <stdlib.h>
using namespace std;

static const char Version[5]="3.0";
static const short KaraokeLineAmount=500;
static const short SingleWordLenth=20;
static const short AllSentenceAmount=40;
static const short WordsInLineAmount=30;
static short height=408;
static short width=720;
static const short A92=92;
static const double PI=3.1415926536;
static const short EnglishWordWidth[26]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
static short amount[AllSentenceAmount][2];//format:[sentence][language]=the amount of words
static short max_line;
static char jdtime[AllSentenceAmount][WordsInLineAmount][6][11];//save some Char time[CN];format[sentence][word][menu][time]
static char cdtime[AllSentenceAmount][WordsInLineAmount][6][11];//save some Char time[JP]
static short jdpos[AllSentenceAmount][WordsInLineAmount][2];//save JP words' position;format[sentence][word][pos]
static short cdpos[AllSentenceAmount][WordsInLineAmount][2];//save CN words' position
static short lenth[AllSentenceAmount][2];//save lenth of all sentences
static char etime_last[AllSentenceAmount][2][11];//save every sentence's last word's Etime

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


#include "UsefulFunction\[F]RAND.h"
#include "FilterDLL\VSFilter.h"
#include "FilterDLL\VSFilterMod.h"

#include "Core\[C]ASScodeCore.h"
/*#include "Core\[F]CharToInt(CharTime).h"
#include "Core\[F]Check(int z).h"
#include "Core\[F]Compute(int x).h"
#include "Core\[F]Getdata.h"
#include "Core\[F]IntToString(IntTime).h"
#include "Core\[F]MyEncoder.h"
#include "Core\[F]Ohead.h"
#include "Core\[F]Order.h"
#include "Core\[F]Start.h"*/


#include "Animation\AllAnimation.h"
#include "Test\TestList.h"