#include "stdafx.h"
static int RAND( int range_min, int range_max)
{
	// Generate random numbers in the half-closed interval
	// [range_min, range_max). 
	// In other words, range_min <= random number < range_max
	return (int)((double)rand() / (RAND_MAX + 1) * (range_max - range_min) + range_min);
}