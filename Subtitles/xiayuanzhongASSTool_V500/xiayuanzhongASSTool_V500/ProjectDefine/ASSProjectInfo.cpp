#include "StdAfx.h"
#include "ASSProjectInfo.h"

void xiayuanzhongASSProjectInfo::setProjectName( std::string temp )       //设置项目名称(同时自动生成)
{
	ProjectName = temp;
	OutputFile = temp + "_Fin.ass";
	RecordFile = temp + "_Record.ass";
	return;
}

void xiayuanzhongASSProjectInfo::setLrcFile( std::string temp )           //设置歌词读取文件(Unicode)
{
	LrcFile = temp;
	return;
}

void xiayuanzhongASSProjectInfo::setTimelineFile( std::string temp )      //设置时间轴文件ASS(Unicode)
{
	TimelineFile = temp;
	return;
}

void xiayuanzhongASSProjectInfo::setFontFile( std::string temp )          //设置字体定义文件(Unicode)
{
	FontFile = temp;
	return;
}
