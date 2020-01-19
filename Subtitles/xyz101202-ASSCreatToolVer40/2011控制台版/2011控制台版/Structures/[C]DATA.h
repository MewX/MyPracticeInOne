#include "stdafx.h"

struct DATA
{
	short num_s;                  //sentense number
	short num_l;                  //language number(Commenly:Jap-1,Chn-2)
	short num_w;                  //word number
	char stime[11];               //Start Time
	char etime[11];               //Ending Time
	char word[SingleWordLenth];   //save single word
	short num_line;               //line of the number
};