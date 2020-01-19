#include "..\CommenHead.h"


static short FileMode;
	//
	//P.S. ofCN=1,ofJPrm=2,ofROCK=4;
	//That means: 1=ofCN; 2=ofJPrm; 3=ofCN+ofJPrem; 4=ofROCK; 5=ofCN+ofROCK; 6=ofJPrm+ofROCK; 7=ofCN+ofJPrm+ofROCK
	//That means: ofCN-1,3,5,7; ofJPrm-2,3,6,7; ofROCK-4,5,6,7
	//
static short StructAmount;
static int a,b,c,d,e,f,g,h,k,i; 

#include "[F]CharToInt(CharTime).h"
#include "[F]IntToString(IntTime).h"
#include "[F]Compute(int x).h"
#include "[F]Getdata.h"
#include "[F]MyEncoder.h"
#include "[F]Ohead.h"
#include "[F]Order.h"
#include "[F]Start.h"


/*using namespace std;
class ASScodeCore
{
public:
	//ASScode();
	int  Start(ScriptInfo TempScriptInfo, short PositionMode);
	void MyEncoder();
	void Check(int z);// 'z' can take you to different mode
	void Getdata();
	void Order();
	void Ohead(ScriptInfo TempScriptInfo);
	void Compute(int x);
	int cti(char a[11]);
	string itc(int aaaa);

private:
	short FileMode;
	//
	//P.S. ofCN=1,ofJPrm=2,ofROCK=4;
	//That means: 1=ofCN; 2=ofJPrm; 3=ofCN+ofJPrem; 4=ofROCK; 5=ofCN+ofROCK; 6=ofJPrm+ofROCK; 7=ofCN+ofJPrm+ofROCK
	//That means: ofCN-1,3,5,7; ofJPrm-2,3,6,7; ofROCK-4,5,6,7
	//
	short StructAmount;
	int a,b,c,d,e,f,g,h,k,i;
};*/