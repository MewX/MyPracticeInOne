#include "stdafx.h"
/*static string OTH(short O)
{
	const char HHH[16]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	char H[5];
	short Oa[5];
	short cal=0, tp=0;
	string last;
	for(short OTHtemp=0; OTHtemp<5; OTHtemp++)
	{
		Oa[OTHtemp]=0;
		H[OTHtemp]='\0';
	}
	Oa[0]=O/16;
	O=O%16;
	for(tp=0; O>=16; tp++)
	{
		Oa[tp]=O/16;
		O=O%16;
	}
	if(tp==0)
	{
		H[0]=HHH[O];
		return H;
	}
	cal=tp;
	H[tp]=HHH[O];
	for(; tp>0; tp--) H[tp-1]=HHH[cal-tp-1];
	return H;
}*/