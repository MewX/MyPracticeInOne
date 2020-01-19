#pragma once
#include <string>

#include "ProjectDefine\ASSProjectInfo.h"
#include "ComputeHelper\ASSComputeHelper.h"
#include "Core\ASSComputeCore.h"
#include "FilterFunctions\VSFilter.h"

class xiayuanzhongUnionAllClasses
{
public:
	xiayuanzhongUnionAllClasses( std::string ID );                 //声明时就传入ID号
	
private:
	void WRITE( short mode, std::string temp );                    //逐字节，注意'\n'/mode = 1-->CN & 2-->EN
};
