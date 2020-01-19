#include "StdAfx.h"
#include "ASSComputeHelper.h"

int xiayuanzhongASSComputeHelper::Time_sti( std::string temp ) const           //String To Int
{
    int ok = 0;
	ok += 360000 * ( (int)temp[ 0 ] - 48 );
	ok += 6000 * ( 10 * ( (int)temp[ 2 ] - 48 ) + ( (int)temp[ 3 ] - 48 ) );
	ok += 100 * ( 10 * ( (int)temp[ 5 ] - 48 ) + ( (int)temp[ 6 ] - 48 ) );
	ok += 10 * ( (int)temp[ 8 ] - 48 ) + ( (int)temp[ 9 ] - 48 );
    return ok;
}

std::string xiayuanzhongASSComputeHelper::Time_its( int temp ) const           //Int To String
{
	char cc[ 11 ];
	cc[ 0 ] = (char)( (int)( temp / 360000 ) + 48 );
	temp -= (int)( temp / 360000 ) * 360000;
	cc[ 1 ] = ':';
	cc[ 2 ] = (char)( (int)( (int)( temp / 6000 ) / 10 ) + 48 );
	cc[ 3 ] = (char)( (int)( temp / 6000 ) - 10 * (int)( (int)( temp / 6000 ) / 10 ) + 48 );
	cc[ 4 ] = ':';
	temp -= (int)( temp / 6000 ) * 6000;
	cc[ 5 ] = (char)( (int)( (int)( temp / 100 ) / 10 ) + 48 );
	cc[ 6 ] = (char)( (int)( temp / 100 ) - 10 * (int)( (int)( temp / 100 ) / 10 ) + 48 );
	temp -= (int)( temp / 100 ) * 100;
	cc[ 7 ] = '.';
	cc[ 8 ] = (char)( (int)( temp / 10 ) + 48 );
	cc[ 9 ] = (char)( temp - 10 * (int)( temp / 10 ) + 48 );
	cc[ 10 ] = '\0';
	return cc;
}

int xiayuanzhongASSComputeHelper::RAND( int range_min, int range_max ) const   //My Rand
{
	// ==>  [ range_min, range_max )
	// ==>  range_min <= random number < range_max
	return (int)((double)rand() / (RAND_MAX + 1) * (range_max - range_min) + range_min);
}
