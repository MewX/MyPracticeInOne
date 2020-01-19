#include "..\CommenHead.h"

struct ScriptInfo
{
	char GETopenfileCN[100];              //注：如果输入"-"，则表示忽略
	char GETopenfileJP[100];//输出My卡轴
	char GETopenfileJPrm[100];            //注：如果输入"-"，则表示忽略
	char GETopenfileROCK[100];            //注：如果输入"-"，则表示忽略
	char GETsavefile[100];//输出成品文件
	char GETkaraokefile[100];//读入
	char GETtempfile[100];//输出临时文件

	char Title[100];                        //标题
	short PlayResX;                         //x的长度,宽度
	short PlayResY;                         //y的长度,高度
	long SynchPoint;                    //开始的时间轴偏移
	short FontAmount;                       //包含的字体数量
	struct FI
	{
		char Name[20];                      //字体变量名
		char Fontname[40];                  //字体的原名
		short Fontsize;                     //字体的大小8-72
		char PrimaryColour[7];              //第一色
		char SecondaryColour[7];            //第二色
		char OutlineColour[7];              //边框色
		char BackColour[7];                 //投影色
		short Bold;                         //粗体? 0关闭,-1开启
		short Italic;                       //斜体? 0关闭,-1开启
		short Underline;                    //下划线? 0关闭,-1开启
		short StrikeOut;                    //删除线? 0关闭,-1开启
		short ScaleX;                       //X方向大小(%)
		short ScaleY;                       //Y方向大小(%)
		short Spacing;                      //间距
		short Angle;                        //倾斜角
		short BorderStyle;                  //边框样式:取值1,正常;取值3,有一覆盖区域
		float Outline;                      //边框宽度:取值范围1-4,数字越大边框越宽
		short Shadow;                       //阴影距离:取值范围0-4,数字越大阴影越厚
		short Alignment;                    //对齐方式1-11
		short MarginL, MarginR, MarginV;    //边距,默认30
		short Encoding;                     //字符编码,默认:1
	}FontInfo[20];
};