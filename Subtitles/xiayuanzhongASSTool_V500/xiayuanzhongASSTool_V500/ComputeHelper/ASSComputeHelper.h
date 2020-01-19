#pragma once
#include <string>

class xiayuanzhongASSComputeHelper
{
public:
	int Time_sti( std::string temp ) const;                             //String To Int
	std::string Time_its( int temp ) const;                             //Int To String
	int RAND( int range_min, int range_max ) const;                     //My Rand
};
