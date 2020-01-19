#include <iostream>
#include <string>
using namespace std;
short Fibonacci_like( short Temp_Length );

int main( )
{
    int LengthA = 0, ME = 0;
    string A;
    while ( 1 ) {//Get string A
        cout << "Input: ";
        cin >> A;
        if ( A.length( ) < 6 || A.length( ) > 14 ) cout << "Wrong Number!" << endl << endl;
        else break;
    }
    /*work out Length*/
    if ( 5 < A.length( ) && A.length( ) < 9 ) LengthA = 2 * Fibonacci_like( A.length( ) );
	else if ( 10 < A.length( ) && A.length( ) < 15 ) LengthA = Fibonacci_like( A.length( ) ) / 2;
	else LengthA = Fibonacci_like( A.length( ) );
	cout << "Length of Password: " << LengthA << endl;
	/*output the Password*/
	ME = A.length( );
	cout << "Password: X3_";
	for ( short i = 0; i < LengthA - 3; i ++ ) {
		if( (short)A[ i%ME ] + i < 127 ) cout << (char)( (int)A[ i%ME ] + i );
		else cout << (char)( (int)A[ i%ME ] - i );
	}
	cout << endl;
	system( "PAUSE" );
	return 0;
}
short Fibonacci_like( short Temp_Length )
{
	if ( Temp_Length < 1 ) return 0;
	else if ( Temp_Length ==1 || Temp_Length ==2 || Temp_Length ==3 ) return 1;
	else return Fibonacci_like( Temp_Length - 1 ) + Fibonacci_like( Temp_Length - 3 );
}
