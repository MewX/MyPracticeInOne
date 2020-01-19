#include "..\CommenHead.h"
static string  itc(int aaaa)
{
	char cc[11];
	cc[0]=(char)((int)(aaaa/360000)+48);
	aaaa=aaaa-(int)(aaaa/360000)*360000;
	cc[1]=':';
	cc[2]=(char)((int)((int)(aaaa/6000)/10)+48);
	cc[3]=(char)((int)(aaaa/6000)-10*(int)((int)(aaaa/6000)/10)+48);
	cc[4]=':';
	aaaa=aaaa-(int)(aaaa/6000)*6000;
	cc[5]=(char)((int)((int)(aaaa/100)/10)+48);
	cc[6]=(char)((int)(aaaa/100)-10*(int)((int)(aaaa/100)/10)+48);
	aaaa=aaaa-(int)(aaaa/100)*100;
	cc[7]='.';
	cc[8]=(char)((int)(aaaa/10)+48);
	cc[9]=(char)(aaaa-10*(int)(aaaa/10)+48);
	cc[10]='\0';
	return cc;
}
