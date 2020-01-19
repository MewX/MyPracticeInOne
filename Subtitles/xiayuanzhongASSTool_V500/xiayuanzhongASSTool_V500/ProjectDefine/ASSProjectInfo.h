#pragma once
#include <string>

class xiayuanzhongASSProjectInfo
{
public:
	void setProjectName( std::string temp );                         //设置项目名称(同时自动生成1,3,6)
	void setLrcFile( std::string temp );                             //设置歌词读取文件(Unicode)
	void setTimelineFile( std::string temp );                        //设置时间轴文件ASS(Unicode)
	void setFontFile( std::string temp );                            //设置字体定义文件(Unicode)

private:
	std::string ProjectName;                                         //ID:1 项目名称
	std::string LrcFile;                                             //ID:2 歌词文件，非时间轴(Unicode)
	std::string OutputFile;                                          //ID:3 输出的成品ASS(Unicode)
	std::string TimelineFile;                                        //ID:4 时间轴文件ASS(Unicode)
	std::string FontFile;                                            //ID:5 字体设定文件(Unicode)
	std::string RecordFile;                                          //ID:6 生成流程记录文件(ANSI)
};
