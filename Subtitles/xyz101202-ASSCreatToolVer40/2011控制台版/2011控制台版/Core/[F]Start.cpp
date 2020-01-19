#include "stdafx.h"
#include "..\stdafx.h"

/*static*/ int ASScodeCore::Start(ScriptInfo TempScriptInfo, short PositionMode)
{
	ass_temp<<">>已进入主程序"<<endl;
	bool ofCN=false, ofJPrm=false, ofROCK=false;
	if(TempScriptInfo.GETopenfileCN[0]!='-'){ofCN=true;FileMode+=1;}
	if(TempScriptInfo.GETopenfileJPrm[0]!='-'){ofJPrm=true;FileMode+=2;}
	if(TempScriptInfo.GETopenfileROCK[0]!='-'){ofROCK=true;FileMode+=4;}
	//以上在判断有那些文件

	
	if(ofCN==true)
	{
		strcpy_s(openfileCN,TempScriptInfo.GETopenfileCN);
		ass_originCN.open(openfileCN,ios::out|ios::in|ios::trunc);
	}//
	strcpy_s(openfileJP,TempScriptInfo.GETopenfileJP);
	ass_originJP.open(openfileJP,ios::out|ios::in|ios::trunc);//
	if(ofJPrm==true)
	{
		strcpy_s(openfileJPrm,TempScriptInfo.GETopenfileJPrm);
		ass_originJPrm.open(openfileJPrm,ios::out|ios::in|ios::trunc);//
	}
	if(ofROCK==true)
	{
		strcpy_s(openfileROCK,TempScriptInfo.GETopenfileROCK);
		ass_originROCK.open(openfileROCK,ios::out|ios::in|ios::trunc);//
	}
	strcpy_s(savefile,TempScriptInfo.GETsavefile);
	ass_out.open(savefile,ios::out|ios::trunc);//
	strcpy_s(karaokefile,TempScriptInfo.GETkaraokefile);
	ass_in.open(karaokefile,ios::in);//
	strcpy_s(tempfile,TempScriptInfo.GETtempfile);
	ass_temp.open(tempfile,ios::out|ios::trunc);//
	//打开文件

	ass_temp<<">>已进入MyEncoder()"<<endl;
	MyEncoder();//这里面通过调用FileMode的判断，存储的格式是折取符能直接读的
	ass_temp<<"  >>已返回主程序"<<endl;

	ass_temp<<">>已进入GetData()"<<endl;
	Getdata();//这里面通过调用FileMode的判断，选择性存储数据
	ass_temp<<"  >>已返回主程序"<<endl;

	ass_temp<<">>已进入Order()"<<endl;
	Order();//这里面通过调用FileMode的判断，选择性排序
	ass_temp<<"  >>已返回主程序"<<endl;

	ass_temp<<">>已进入Compute()"<<endl;
	Compute(PositionMode);//这里面通过调用FileMode的判断，选择性计算
	ass_temp<<"  >>已返回主程序"<<endl;

	ass_temp<<">>已进入GetData()"<<endl;
	Ohead(TempScriptInfo);
	ass_temp<<"  >>已返回主程序"<<endl;
	ass_temp<<">>已退出主程序"<<endl;
	return 0;
}