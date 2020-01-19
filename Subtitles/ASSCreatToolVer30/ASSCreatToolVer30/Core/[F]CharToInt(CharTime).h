#include "..\CommenHead.h"

static int cti(char a[11])
{
    int ii=0;
    ii=360000*((int)a[0]-48)+6000*(10*((int)a[2]-48)+((int)a[3]-48))+100*(10*((int)a[5]-48)+((int)a[6]-48))+(10*((int)a[8]-48)+((int)a[9]-48));
    return ii;
}

