#include <iostream>
#include <stdio.h>
#include <string>

using namespace std;

void Announcement( );                  //show announcement
short turnToMyChar( char t );          //turn my word
char getMyChar( short t );             //get my word
void Congratulations( );
void Error( );

int main( )
{
	Announcement( );
	string A, B;
	short large = 0, Switch = -1;
	while( 1 ) {
		Switch = 1;
		printf( "%s", "Please enter the Name: " );
		cin >> A;
		if ( A.length( ) >=999 ) {
			printf( "To long! ReType!\n\n" );
			continue;
		}
		printf( "%s", "Please enter the Password: " );
		cin >> B;
		if ( B.length( ) >=999 ) {
			printf( "To long! ReType!\n\n" );
			continue;
		}
		large = A.length( )/*strlen( A )*/;
		if ( large + 4 != B.length( )/*strlen( B )*/ ) {
			Error( );
			continue;
		}
		for ( short i = 0; i < large; i ++ ) {
			if ( A[ i ] != B[ large - 1 - i ] ) {
				Switch = turnToMyChar( getMyChar( -1 ) );
				Error( );
				break;
			}
		}
		if ( B[ large + 4 - 1 ] != 'z' ) Switch = turnToMyChar( getMyChar( -1 ) );
		if ( B[ large + 1 - 1 ] != '_' ) Switch = turnToMyChar( getMyChar( -1 ) );
		if ( B[ large + 2 - 1 ] != 'x' ) Switch = turnToMyChar( getMyChar( -1 ) );
		if ( B[ large + 3 - 1 ] != 'y' ) Switch = turnToMyChar( getMyChar( -1 ) );
		if ( turnToMyChar( getMyChar( Switch ) ) > turnToMyChar( getMyChar( 0 ) ) ) break;
	}
	if ( turnToMyChar( getMyChar( Switch ) ) > turnToMyChar( getMyChar( 0 ) ) ) Congratulations( );
	system( "PAUSE" );
	return 0;
}

void Error( )
{
	printf( "%s", "Error! Try Again Please!~\n\n" );
}

void Announcement( )
{
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "\n%s", "Thanks for using it!\nBecause it's my first time to write CrackMe!\n( I'm a Chinese, so there may some mistakes in my words. )\n" );
	printf( "%s", "\nI think the most difficult part when you crack me is unpacking.\nIn fact, I also want to know how to unpack it." );
	printf( "%s", "\nThus if you crack me successfully. You will be able to see my email address.\n" );
	printf( "%s", "Please and please teach how to unpack the shell. Thanks and Good Luck!~\n\n" );
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "%c", '\n' );
}

void Congratulations( )
{
	printf( "%c", '\n' );
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "You're so COOL!~ WEEL-DONE!\nMy email address is " );
	printf( "%c", getMyChar( turnToMyChar( 'x' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'i' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'a' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'y' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'u' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'a' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'n' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'z' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'h' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'o' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'n' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'g' ) ) );
	printf( "%c", getMyChar( turnToMyChar( '@' ) ) );
	printf( "%c", getMyChar( turnToMyChar( '1' ) ) );
	printf( "%c", getMyChar( turnToMyChar( '2' ) ) );
	printf( "%c", getMyChar( turnToMyChar( '6' ) ) );
	printf( "%c", getMyChar( turnToMyChar( '.' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'c' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'o' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'm' ) ) );
	printf( "%c", '\n' );
	printf( "My name is " );
	printf( "%c", getMyChar( turnToMyChar( 'x' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'i' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'a' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'y' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'u' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'a' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'n' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'z' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'h' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'o' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'n' ) ) );
	printf( "%c", getMyChar( turnToMyChar( 'g' ) ) );
	printf( "%c", '\n' );
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "%c", '\n' );
}


short turnToMyChar( char t )
{
	return ( short )t - 10;
}
char getMyChar( short t )
{
	return ( char )( t + 10 );
}
