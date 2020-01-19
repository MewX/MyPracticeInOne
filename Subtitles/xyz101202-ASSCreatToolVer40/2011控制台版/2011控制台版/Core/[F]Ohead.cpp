#include "stdafx.h"
/*static*/ void ASScodeCore::Ohead(ScriptInfo TempScriptInfo)//输入完成后，将i改为-1
{
	short jjjjj=0;
	ass_out<<"[Script Info]"<<endl;
	ass_out<<"Title:"<<TempScriptInfo.Title<<endl;
	ass_out<<";Created by XiaYuanzhong, with Microsoft Visual C++ 2010 Ultimate."<<endl;
	ass_out<<"Version: "<<Version<<endl;
	ass_out<<";Thank 'Adobe Fireworks' & 'Popsub' & 'ASSdraw3'!"<<endl;
	ass_out<<"ScriptType: v4.00+"<<endl;
	ass_out<<"Synch Point:"<<TempScriptInfo.SynchPoint<<endl;
	ass_out<<"Collisions:Normal"<<endl;
	ass_out<<"PlayResX:"<<TempScriptInfo.PlayResX<<endl;//////////
	ass_out<<"PlayResY:"<<TempScriptInfo.PlayResY<<endl;//////////
	ass_out<<"Timer:100.0000"<<endl;
	ass_out<<endl;
	ass_out<<"[V4+ Styles]"<<endl;//////////

	//按照数据结构编写

	ass_out<<"Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, Italic, Underline, Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, Encoding"<<endl;
	for(jjjjj=0; jjjjj<TempScriptInfo.FontAmount; jjjjj++)
	{
		ass_out<<"Style:"<<TempScriptInfo.FontInfo[jjjjj].Name<<","<<TempScriptInfo.FontInfo[jjjjj].Fontname<<","<<TempScriptInfo.FontInfo[jjjjj].Fontsize<<",&H"<<TempScriptInfo.FontInfo[jjjjj].PrimaryColour<<",&H"<<TempScriptInfo.FontInfo[jjjjj].SecondaryColour<<",&H"<<TempScriptInfo.FontInfo[jjjjj].OutlineColour<<",&H"<<TempScriptInfo.FontInfo[jjjjj].BackColour<<","<<TempScriptInfo.FontInfo[jjjjj].Bold<<","<<TempScriptInfo.FontInfo[jjjjj].Italic<<","<<TempScriptInfo.FontInfo[jjjjj].Underline<<","<<TempScriptInfo.FontInfo[jjjjj].Angle<<","<<TempScriptInfo.FontInfo[jjjjj].BorderStyle<<","<<TempScriptInfo.FontInfo[jjjjj].Outline<<","<<TempScriptInfo.FontInfo[jjjjj].Shadow<<","<<TempScriptInfo.FontInfo[jjjjj].Alignment<<","<<TempScriptInfo.FontInfo[jjjjj].MarginL<<","<<TempScriptInfo.FontInfo[jjjjj].MarginR<<","<<TempScriptInfo.FontInfo[jjjjj].MarginV<<","<<TempScriptInfo.FontInfo[jjjjj].Encoding<<endl;
	}
	//ass_out<<"Style:OP_J1,华文楷体,30,&HFFFF99,&HFFFF99,&HFF0000,&HF0000000,-1,0,0,100,100,0,1,2,0,10,30,30,23,1"<<endl;
	//ass_out<<"Style:OP_C1,华文楷体,30,&HFFFF99,&HFFFF99,&HF0000F,&HF0000000,-1,0,0,100,100,0,1,2,0,10,30,30,23,1"<<endl;
	ass_out<<endl;
	//e.g.Style: ED_JP2,DFGMaruMoji-W9,33,&HB447CF28,&H00EEEEEE,&HC8FFFFFF,&HF0000000,-1,0,0,0,100,100,0,0,1,1.5,0,10,30,30,23,1
	//ass_out<<""<<endl;
	ass_out<<"[Events]"<<endl;
	ass_out<<"Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text"<<endl;
	i=-1;
}
