// xiayuanzhongASSTool_V500.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"

int _tmain( int argc, _TCHAR *argv[ ] )
{
	setlocale( LC_ALL, "chinese" );
	clock_t start = clock( );//初始化计数

	//TODO:调用函数

	/*ofstream ofs( "test.txt", ios::binary | ios::out );
	ifstream ifs( "Test_Read.txt", ios::binary | ios::in  );
	wchar_t temp[ 2 ];
	ifs.read( (char *)temp, 2 );
	memset( temp, L'\0', sizeof(temp) );
	ofs.write( "\xFF\xFE", 2 );
	int k = 0;
    while ( 1 ) {
        k ++;
        ifs.read( (char *)temp, 2 );
        if ( ifs.eof( ) ) break;//经验表明，放在这里最恰当！~
		ofs.write( (char *)temp, 2 );
    }
	ifs.close( );
	ofs.close( );*/


	ofstream ofs( "test.txt", ios::binary | ios::out );
	ifstream ifs( "Test_Read.txt", ios::binary | ios::in  );
	wchar_t temp[ 2 ];
	wstring Temp;
	ifs.read( (char *)temp, 2 );
	memset( temp, L'\0', sizeof(temp) );
	cout << "sizeof= " << sizeof(temp) << endl;
	ofs.write( "\xFF\xFE", 2 );
	int k = 0;
    while ( 1 ) {
        k ++;
        ifs.read( (char *)temp, 2 );
        if ( ifs.eof( ) ) break;//经验表明，放在这里最恰当！~
		ofs.write( (char *)temp, 2 );
    }
	ifs.close( );
	ofs.close( );

	cout << "Used " << clock( ) - start << " ms." << endl;
	system( "PAUSE" );
	return 0;
}
