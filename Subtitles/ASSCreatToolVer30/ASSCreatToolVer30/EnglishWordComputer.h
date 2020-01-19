#include "CommenHead.h"

static void EnglishWordComputer(char WordName[40], short WordSize)
{
	short Count1=0,Count2=0,Count3=0,Count4=0,Count5=0,Count6=0;
	ScriptInfo EnglishWordComputer;

	strcpy_s(EnglishWordComputer.GETopenfileCN,"--");              //注：如果输入"-"，则表示忽略
	strcpy_s(EnglishWordComputer.GETopenfileJP,"--");//输出My卡轴
	strcpy_s(EnglishWordComputer.GETopenfileJPrm,"--");            //注：如果输入"-"，则表示忽略
	strcpy_s(EnglishWordComputer.GETopenfileROCK,"--");            //注：如果输入"-"，则表示忽略
	strcpy_s(EnglishWordComputer.GETsavefile,"EnglishWordComputer.ass");//输出成品文件
	strcpy_s(EnglishWordComputer.GETkaraokefile,"--");//读入
	strcpy_s(EnglishWordComputer.GETtempfile,"EnglishWordComputerTemp.txt");//输出临时文件
	strcpy_s(EnglishWordComputer.Title,"EnglishWordComputerTest");                        //标题
	EnglishWordComputer.PlayResX=720;                         //x的长度,宽度
	EnglishWordComputer.PlayResY=480;                         //y的长度,高度
	EnglishWordComputer.SynchPoint=0;
	EnglishWordComputer.FontAmount=1;                       //包含的字体数量
	strcpy_s(EnglishWordComputer.FontInfo[0].Name,"JP1");                      //字体变量名
	strcpy_s(EnglishWordComputer.FontInfo[0].Fontname,WordName);                  //字体的原名
	EnglishWordComputer.FontInfo[0].Fontsize=WordSize;                     //字体的大小8-72
	strcpy_s(EnglishWordComputer.FontInfo[0].PrimaryColour,"FF0066");              //第一色
	strcpy_s(EnglishWordComputer.FontInfo[0].SecondaryColour,"996666");            //第二色
	strcpy_s(EnglishWordComputer.FontInfo[0].OutlineColour,"996666");              //边框色
	strcpy_s(EnglishWordComputer.FontInfo[0].BackColour,"000000");                 //投影色
	EnglishWordComputer.FontInfo[0].Bold=0;                         //粗体? 0关闭,-1开启
	EnglishWordComputer.FontInfo[0].Italic=0;                       //斜体? 0关闭,-1开启
	EnglishWordComputer.FontInfo[0].Underline=0;                    //下划线? 0关闭,-1开启
	
	EnglishWordComputer.FontInfo[0].StrikeOut=0;                    //删除线? 0关闭,-1开启
	EnglishWordComputer.FontInfo[0].ScaleX=100;                       //X方向大小(%)
	EnglishWordComputer.FontInfo[0].ScaleY=100;                       //Y方向大小(%)
	EnglishWordComputer.FontInfo[0].Spacing=10;                      //间距

	EnglishWordComputer.FontInfo[0].Angle=0;                        //倾斜角
	EnglishWordComputer.FontInfo[0].BorderStyle=1;                  //边框样式:取值1,正常;取值3,有一覆盖区域
	EnglishWordComputer.FontInfo[0].Outline=1;                      //边框宽度:取值范围1-4,数字越大边框越宽
	EnglishWordComputer.FontInfo[0].Shadow=2;                       //阴影距离:取值范围0-4,数字越大阴影越厚
	EnglishWordComputer.FontInfo[0].Alignment=1;                    //对齐方式1-11
	EnglishWordComputer.FontInfo[0].MarginL=30;
	EnglishWordComputer.FontInfo[0].MarginR=30;
	EnglishWordComputer.FontInfo[0].MarginV=30;    //边距,默认30
	EnglishWordComputer.FontInfo[0].Encoding=1;                     //字符编码,默认:1

	ass_out.open(EnglishWordComputer.GETsavefile,ios::out|ios::trunc);
	ass_temp.open(EnglishWordComputer.GETtempfile,ios::out|ios::trunc);

	Ohead(EnglishWordComputer);
	cout<<"Finished Ohead()!~"<<endl;
	for(Count1=0;Count1<26;Count1++)//小写区
	{//字母计数
		ass_temp<<Count1<<endl;
		for(Count2=0;Count2<6;Count2++)
		{//列数计数
			for(Count3=0;Count3<8;Count3++)
			{//行数计数
				Count6=Count1+asc2;
				Count5=6*Count2+Count3+1;
				LineBegin(100,itc((int)Count1*100),itc(((int)Count1+1)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd(Count5);
				
				LineBegin(1,itc((int)Count1*100),itc(((int)Count1+1)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);

				LineBegin(2,itc((int)Count1*100),itc(((int)Count1+1)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35+Count5,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);
			}
		}
	}
	for(Count1=0;Count1<26;Count1++)//大写区
	{
		for(Count2=0;Count2<6;Count2++)
		{
			for(Count3=0;Count3<8;Count3++)
			{
				Count6=Count1+asc1;
				Count5=6*Count2+Count3+1;
				LineBegin(100,itc(((int)Count1+26)*100),itc(((int)Count1+1+26)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd(Count5);
				
				LineBegin(1,itc(((int)Count1+26)*100),itc(((int)Count1+1+26)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);

				LineBegin(2,itc(((int)Count1+26)*100),itc(((int)Count1+1+26)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35+Count5,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);
			}
		}
	}
	Count1=0;
	for(Count2=0;Count2<6;Count2++)//中文
	{
		for(Count3=0;Count3<8;Count3++)
		{
			//Count6=Count1+asc1;
			Count5=6*Count2+Count3+25;
			LineBegin(100,itc(((int)Count1+52)*100),itc(((int)Count1+1+52)*100),"JP1");
			StyleBegin();
			an(7);
			pos(Count2*120,Count3*60);//y-60;x-120
			StyleEnd();
			LineEnd(Count5);
			
			LineBegin(1,itc(((int)Count1+52)*100),itc(((int)Count1+1+52)*100),"JP1");
			StyleBegin();
			an(7);
			pos(Count2*120+35,Count3*60);//y-60;x-120
			StyleEnd();
			LineEnd("夏");

			LineBegin(2,itc(((int)Count1+52)*100),itc(((int)Count1+1+52)*100),"JP");
			StyleBegin();
			an(7);
			pos(Count2*120+35+Count5,Count3*60);//y-60;x-120
			StyleEnd();
			LineEnd("夏");
		}
	}
}



static void EnglishWordComputer()
{
	short Count1=0,Count2=0,Count3=0,Count4=0,Count5=0,Count6=0;
	
	ScriptInfo TOMOYOafterDEMO;
	strcpy_s(TOMOYOafterDEMO.GETopenfileCN,"--");
	strcpy_s(TOMOYOafterDEMO.GETopenfileJP,"TOMOYOafterDEMO_k.txt");
	strcpy_s(TOMOYOafterDEMO.GETopenfileJPrm,"--");
	strcpy_s(TOMOYOafterDEMO.GETopenfileROCK,"--");
	strcpy_s(TOMOYOafterDEMO.GETsavefile,"TOMOYOafterDEMO.ass");
	strcpy_s(TOMOYOafterDEMO.GETkaraokefile,"TOMOYO_DEMO.ass");
	strcpy_s(TOMOYOafterDEMO.GETtempfile,"TOMOYOafterDEMOTemp.txt");
	strcpy_s(TOMOYOafterDEMO.Title,"TOMOYOafter_DEMO");
	TOMOYOafterDEMO.PlayResX=640;
	TOMOYOafterDEMO.PlayResY=480;
	TOMOYOafterDEMO.SynchPoint=0;
	TOMOYOafterDEMO.FontAmount=1;
	strcpy_s(TOMOYOafterDEMO.FontInfo[0].Name,"JP1");
	strcpy_s(TOMOYOafterDEMO.FontInfo[0].Fontname,"文鼎中隶繁");
	TOMOYOafterDEMO.FontInfo[0].Fontsize=22;
	strcpy_s(TOMOYOafterDEMO.FontInfo[0].PrimaryColour,"E49B08");
	strcpy_s(TOMOYOafterDEMO.FontInfo[0].SecondaryColour,"000000");
	strcpy_s(TOMOYOafterDEMO.FontInfo[0].OutlineColour,"FDFFFB");
	strcpy_s(TOMOYOafterDEMO.FontInfo[0].BackColour,"666666");
	TOMOYOafterDEMO.FontInfo[0].Bold=0;
	TOMOYOafterDEMO.FontInfo[0].Italic=0;
	TOMOYOafterDEMO.FontInfo[0].Underline=0;
	TOMOYOafterDEMO.FontInfo[0].StrikeOut=0;
	TOMOYOafterDEMO.FontInfo[0].ScaleX=120;
	TOMOYOafterDEMO.FontInfo[0].ScaleY=100;
	TOMOYOafterDEMO.FontInfo[0].Spacing=0;                      //间距
	TOMOYOafterDEMO.FontInfo[0].Angle=0;                        //倾斜角
	TOMOYOafterDEMO.FontInfo[0].BorderStyle=1;                  //边框样式:取值1,正常;取值3,有一覆盖区域
	TOMOYOafterDEMO.FontInfo[0].Outline=2;                      //边框宽度:取值范围1-4,数字越大边框越宽
	TOMOYOafterDEMO.FontInfo[0].Shadow=2;                       //阴影距离:取值范围0-4,数字越大阴影越厚
	TOMOYOafterDEMO.FontInfo[0].Alignment=1;                    //对齐方式1-11
	TOMOYOafterDEMO.FontInfo[0].MarginL=30;
	TOMOYOafterDEMO.FontInfo[0].MarginR=30;
	TOMOYOafterDEMO.FontInfo[0].MarginV=30;
	TOMOYOafterDEMO.FontInfo[0].Encoding=1;
	ass_out.open(TOMOYOafterDEMO.GETsavefile,ios::out|ios::trunc);
	ass_temp.open(TOMOYOafterDEMO.GETtempfile,ios::out|ios::trunc);
	Ohead(TOMOYOafterDEMO);
	cout<<"Finished Ohead()!~"<<endl;
	for(Count1=0;Count1<26;Count1++)//小写区
	{//字母计数
		ass_temp<<Count1<<endl;
		for(Count2=0;Count2<6;Count2++)
		{//列数计数
			for(Count3=0;Count3<8;Count3++)
			{//行数计数
				Count6=Count1+asc2;
				Count5=6*Count2+Count3+1;
				LineBegin(100,itc((int)Count1*100),itc(((int)Count1+1)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd(Count5);
				
				LineBegin(1,itc((int)Count1*100),itc(((int)Count1+1)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);

				LineBegin(2,itc((int)Count1*100),itc(((int)Count1+1)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35+Count5,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);
			}
		}
	}
	for(Count1=0;Count1<26;Count1++)//大写区
	{
		for(Count2=0;Count2<6;Count2++)
		{
			for(Count3=0;Count3<8;Count3++)
			{
				Count6=Count1+asc1;
				Count5=6*Count2+Count3+1;
				LineBegin(100,itc(((int)Count1+26)*100),itc(((int)Count1+1+26)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd(Count5);
				
				LineBegin(1,itc(((int)Count1+26)*100),itc(((int)Count1+1+26)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);

				LineBegin(2,itc(((int)Count1+26)*100),itc(((int)Count1+1+26)*100),"JP1");
				StyleBegin();
				an(7);
				pos(Count2*120+35+Count5,Count3*60);//y-60;x-120
				StyleEnd();
				LineEnd((char)Count6);
			}
		}
	}
	Count1=0;
	for(Count2=0;Count2<6;Count2++)//中文
	{
		for(Count3=0;Count3<8;Count3++)
		{
			//Count6=Count1+asc1;
			Count5=6*Count2+Count3+25;
			LineBegin(100,itc(((int)Count1+52)*100),itc(((int)Count1+1+52)*100),"JP1");
			StyleBegin();
			an(7);
			pos(Count2*120,Count3*60);//y-60;x-120
			StyleEnd();
			LineEnd(Count5);
			
			LineBegin(1,itc(((int)Count1+52)*100),itc(((int)Count1+1+52)*100),"JP1");
			StyleBegin();
			an(7);
			pos(Count2*120+35,Count3*60);//y-60;x-120
			StyleEnd();
			LineEnd("夏");

			LineBegin(2,itc(((int)Count1+52)*100),itc(((int)Count1+1+52)*100),"JP1");
			StyleBegin();
			an(7);
			pos(Count2*120+35+Count5,Count3*60);//y-60;x-120
			StyleEnd();
			LineEnd("夏");
		}
	}
}