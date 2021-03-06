#include <stdio.h>
#include <tchar.h>
#include <string>
#include <iostream>

using namespace std;

/***Information***/
/*Procedure: Get 2 strings(one is 6~14, the other is Fibonacci-like's answer's part) --> Work out Length --> Work out the Password --?> End!~*/
/*Lenth: Fibonacci-like recursive function*/
/*Message: a '~' operator decrypt*/
/*Password:
		Head:"X3_"(0xa7, 0xcc, 0xa0);
		Main: ASCII + i;
*/

void Announcement( void );                  //Show the Announcement;
void Congratulations( void );               //Show the Congratulations sign;
short Fibonacci_like( short Temp_Length );  //Get Length;
void Decrypt( short temp[ 0xFF ] );         //Output Message;

int _tmain( int argc, _TCHAR *argv[ ] )
{
	Announcement( );
	short Wrong[ ] = { 0xde, 0xd2, 0xdf, 0xa8, 0x8d, 0x90, 0x91, 0x98, 0xdf, 0xd2, 0xde, 0xf5, 0xf5, '\0' };//"!- Wrong -!\n\n"
	short TryAgain[ ] = { 0xba, 0x8d, 0x8d, 0x90, 0x8d, 0xde, 0xdf, 0xab, 0x8d, 0x86, 0xdf, 0xbe, 0x98, 0x9e, 0x96, 0x91, 0xdf, 0xaf, 0x93, 0x9a, 0x9e, 0x8c, 0x9a, 0xde, 0x81, 0xf5, 0xf5, '\0' };//"Error! Try Again Please!~\n\n"
	short Cheating[ ] = { -221, -118, -11, -31, -11, -14, -211, -111, -111, -111, '\0' };//Cheating
	string A, B;
	short LengthA = 0, S = -256;/*The correct S value is 2344*/

	/*Get your Name and Password;*/
	while ( 1 ) {
		Decrypt( Cheating );
		short SEED[ 4 ] = { 8, 2, 6, 4 };//Use to working out the password;
		while ( 1 ) {//Get Name;
			short CheatingSTR[ ] = { -111, -111, '\0' };
			short STR1[ ] = { 0xaf, 0x93, 0x9a, 0x9e, 0x8c, 0x9a, 0xdf, 0x9a, 0x91, 0x8b, 0x9a, 0x8d, 0xdf, 0x8b, 0x97, 0x9a, 0xdf, 0xb1, 0x9e, 0x92, 0x9a, 0xd7, 0xdf, 0xb3, 0x9a, 0x91, 0x98, 0x8b, 0x97, 0xc5, 0xdf, 0xc9, 0x81, 0xce, 0xcb, 0xdf, 0xd6, 0xc5, 0xdf, '\0' };
			Decrypt( Cheating );
			Decrypt( STR1 );//"Please enter the Name( Length: 6~14 ): "
			short K = -250;//Of no use
			cin >> A;
			K = 4650;
			short STR2[ ] = { 0xb6, 0x93, 0x93, 0x9a, 0x98, 0x96, 0x92, 0x9e, 0x8b, 0x9a, 0xdf, 0x8c, 0x8b, 0x8d, 0x96, 0x91, 0x98, 0xde, 0xdf, 0xaf, 0x93, 0x9a, 0x9e, 0x8c, 0x9a, 0xdf, 0xad, 0x9a, 0xab, 0x86, 0x8f, 0x9a, 0xde, 0xf5, 0xf5, '\0' };
			if ( 5 < A.length( ) && A.length( ) < 15 && K == 4650 ) break;
			else Decrypt( CheatingSTR );//return NULL
			Decrypt( Cheating );
			Decrypt( STR2 );//TRUE****"Illegimate string! Please ReType!\n\n"
		}
		S = 250;//cheat
		while ( 1 ) {//Get Password;
			short CheatingSTR[ ] = { -221, -118, -11, -31, -11, -14, -211, -111, -111, -111, '\0' };
			short STR2[ ] = { 0xb6, 0x93, 0x93, 0x9a, 0x98, 0x96, 0x92, 0x9e, 0x8b, 0x9a, 0xdf, 0x8c, 0x8b, 0x8d, 0x96, 0x91, 0x98, 0xde, 0xdf, 0xaf, 0x93, 0x9a, 0x9e, 0x8c, 0x9a, 0xdf, 0xad, 0x9a, 0xab, 0x86, 0x8f, 0x9a, 0xde, 0xf5, 0xf5, '\0' };
			Decrypt( CheatingSTR );
			short STR1[ ] = { 0xaf, 0x93, 0x9a, 0x9e, 0x8c, 0x9a, 0xdf, 0x9a, 0x91, 0x8b, 0x9a, 0x8d, 0xdf, 0x8b, 0x97, 0x9a, 0xdf, 0xaf, 0x9e, 0x8c, 0x8c, 0x88, 0x90, 0x8d, 0x9b, 0xd7, 0xdf, 0x93, 0x9a, 0x8c, 0x8c, 0xdf, 0x8b, 0x97, 0x9e, 0x91, 0xdf, 0xca, 0xcf, 0xdf, 0xd6, 0xc5, 0xdf, '\0' };
			Decrypt( STR1 );
			Decrypt( CheatingSTR );
			cin >> B;
			if ( 0 < B.length( ) && B.length( ) < 51 ) break;
			else Decrypt( CheatingSTR );//return NULL
			Decrypt( Cheating );
			Decrypt( STR2 );//Right****"Illegimate string! Please ReType!\n\n"
		}

		/*Now compare to the Header;*/
		short Switch1 = 111;
		if ( B[ 0 ] == (char)(~0xa7/*'X'*/) ) {//*****Right*****
			if ( B[ 1 ] == (char)(~0xa7/*'X'*/) ) {
				Switch1 = 120;
				Decrypt( Cheating );
			}
			else if ( B[ 1 ] != (char)(~0xcc/*'3'*/) ) Switch1 = 119;
			else {//*****Right*****
				Switch1 = 1200;
				if ( B[ 2 ] != (char)(~0xa0/*'_'*/) ) Switch1 ++;
				else if ( B[ 2 ] == (char)(~0xa0/*'_'*/) && (-- Switch1) ) {//*****Right*****//Switch1== 1199
					Decrypt( Cheating );//*****Right*****
				}
				else Switch1 =Switch1 - 12;
			}
		}
		else if ( B[ 0 ] != (char)(~0xa7/*'X'*/) ) {//JUMP for no reason.
			if ( B[ 1 ] == (char)(~0xa7/*'X'*/) ) {
				Switch1 = 1240;
				Decrypt( Cheating );
			}
			else if ( B[ 1 ] != (char)(~0xcc/*'3'*/) ) Switch1 = 1120;
			else Switch1 = 1200;
		}
		else Decrypt( Cheating );
		/*Examing the "Switch1"value;*/
		if ( Switch1 == 1199 ) { }
		else {
			Decrypt( TryAgain );
			continue;
		}

		/*Now, work out the length;*/
		LengthA = Fibonacci_like( A.length( ) );
		if ( 5 < A.length( ) && A.length( ) < 9 ) LengthA <<= 1;//LengthA = LengthA * 2;
		else if ( 10 < A.length( ) && A.length( ) < 15 ) LengthA >>= 1;//LengthA = LengthA / 2;
		else { }
		if ( B.length( ) == LengthA ) Decrypt( Cheating );
		else {
			Decrypt( TryAgain );
			continue;
		}

		/*Now, work out the MainPassword;*/
		short ME = A.length( );
		for ( short i = 0; i < LengthA - 3; i ++ ) {
			if ( (short)A[ i % ME ] + i < 127 ) {
				if ( B[ 3 + i ] == (char)( (int)A[ i % ME ] + i ) ) continue;
				else {
					Decrypt( Cheating );
					ME = -100;
					break;
				}
			}
			else {
				if ( B[ 3 + i ] == (char)( (int)A[ i % ME ] - i ) ) continue;
				else {
					Decrypt( Cheating );
					ME = -100;
					break;
				}
			}
		}
		if ( ME == A.length( ) && ( S = 2344 ) ) break;//Is "S = 2344"
		else {
			Decrypt( TryAgain );
			continue;
		}
	}
	if ( S == 2344 ) Congratulations( );
	system( "PAUSE" );
	return 0;
}


void Announcement( void )                   //Show the Announcement;
{
	for( short i = 0; i < 80; i ++ ) cout << '*';
	cout << endl;
	short STR1[ ] = { 0xA7, 0xA6, 0xA5, 0xA0, 0xB4, 0x9A, 0x86, 0x98, 0x9A, 0x91, 0xB2, 0x9A, 0xCD, 0xCF, 0xCE, 0xCE,0xCF, 0xCA, 0xCF, 0xCA, 0xf5, '\0' };
	Decrypt( STR1 );	//XYZ_KeygenMe20110505
	short STR2[ ] = { 0xb3, 0x9a, 0x89, 0x9a, 0x93, 0xc5, 0xdf, 0xcd, 0xf5, '\0' };
	Decrypt( STR2 );//Level: 2
	cout << endl;
	short STR3[ ] = { 0xab, 0x97, 0x9e, 0x91, 0x94, 0x8c, 0xdf, 0x99, 0x90, 0x8d, 0xdf, 0x8b, 0x8d, 0x86, 0x96, 0x91, 0x98, 0xdf, 0x96, 0x8b, 0xde, 0xdf, 0xbe, 0x91, 0x9b, 0xdf, 0x86, 0x90, 0x8a, 0xdf, 0x9c, 0x9e, 0x91, 0xdf, 0x9e, 0x8c, 0x94, 0xdf, 0x92, 0x9a, 0xdf, 0x99, 0x90, 0x8d, 0xdf, 0x97, 0x9a, 0x93, 0x8f, 0xde, 0xf5, '\0' };
	Decrypt( STR3 );//Thanks for trying it! And you can ask me for help!
	short STR4[ ] = { 0xb6, 0xdf, 0x8b, 0x97, 0x96, 0x91, 0x94, 0xdf, 0x8b, 0x97, 0x9a, 0xdf, 0x92, 0x90, 0x8c, 0x8b, 0xdf, 0x9b, 0x96, 0x99, 0x99, 0x96, 0x9c, 0x8a, 0x93, 0x8b, 0xdf, 0x8f, 0x9e, 0x8d, 0x8b, 0xdf, 0x88, 0x97, 0x9a, 0x91, 0xdf, 0x86, 0x90, 0x8a, 0xdf, 0x9c, 0x8d, 0x9e, 0x9c, 0x94, 0xdf, 0x92, 0x9a, 0xdf, 0x96, 0x8c, 0xdf, 0x93, 0x90, 0x90, 0x94, 0x96, 0x91, 0x98, 0xdf, 0x99, 0x90, 0x8d, 0xdf, 0x8c, 0x8b, 0x8d, 0x96, 0x91, 0x98, 0xd1, 0xf5, '\0' };
	Decrypt( STR4 );//I think the most difficult part when you crack me is looking for string.
	short STR5[ ] = { 0xab, 0x97, 0x8a, 0x8c, 0xdf, 0x96, 0x99, 0xdf, 0x86, 0x90, 0x8a, 0xdf, 0x9c, 0x8d, 0x9e, 0x9c, 0x94, 0xdf, 0x92, 0x9a, 0xdf, 0x8c, 0x8a, 0x9c, 0x9c, 0x9a, 0x8c, 0x8c, 0x99, 0x8a, 0x93, 0x93, 0x86, 0xd1, 0xdf, 0xa6, 0x90, 0x8a, 0xdf, 0x88, 0x96, 0x93, 0x93, 0xdf, 0x9d, 0x9a, 0xdf, 0x9e, 0x9d, 0x93, 0x9a, 0xdf, 0x8b, 0x90, 0xdf, 0x8c, 0x9a, 0x9a, 0xdf, 0x92, 0x86, 0xdf, 0x9a, 0x92, 0x9e, 0x96, 0x93, 0xdf, 0x9e, 0x9b, 0x9b, 0x8d, 0x9a, 0x8c, 0x8c, 0xd1, 0xf5, '\0' };
	Decrypt( STR5 );//Thus if you crack me successfully. You will be able to see my email address.
	short STR6[ ] = { 0xaf, 0x93, 0x9a, 0x9e, 0x8c, 0x9a, 0xdf, 0x9e, 0x91, 0x9b, 0xdf, 0x8f, 0x93, 0x9a, 0x9e, 0x8c, 0x9a, 0xdf, 0x8b, 0x9a, 0x9e, 0x9c, 0x97, 0xdf, 0x92, 0x9a, 0xdf, 0x97, 0x90, 0x88, 0xdf, 0x86, 0x90, 0x8a, 0xdf, 0x9c, 0x8d, 0x9e, 0x9c, 0x94, 0xdf, 0x9e, 0x91, 0x9b, 0xdf, 0x94, 0x9a, 0x86, 0x98, 0x9a, 0x91, 0xdf, 0x92, 0x9a, 0xd1, 0xdf, 0xab, 0x97, 0x9e, 0x91, 0x94, 0x8c, 0xdf, 0x9e, 0x91, 0x9b, 0xdf, 0xb8, 0x90, 0x90, 0x9b, 0xdf, 0xb3, 0x8a, 0x9c, 0x94, 0xde, 0x81, 0xf5, '\0' };
	Decrypt( STR6 );//Please and please teach me how you crack and keygen me. Thanks and Good Luck!~
	short STR7[ ] = { 0xac, 0x9a, 0x91, 0x9b, 0xdf, 0x92, 0x9a, 0xdf, 0x86, 0x90, 0x8a, 0x8d, 0xdf, 0xbc, 0xd0, 0xbc, 0xd4, 0xd4, 0xdf, 0x8c, 0x90, 0x93, 0x8a, 0x8b, 0x96, 0x90, 0x91, 0xdf, 0x9c, 0x90, 0x9b, 0x9a, 0xd3, 0xdf, 0x9e, 0x91, 0x9b, 0xdf, 0x86, 0x90, 0x8a, 0xdf, 0x88, 0x96, 0x93, 0x93, 0xdf, 0x8d, 0x9a, 0x9c, 0x9a, 0x96, 0x89, 0x9a, 0xdf, 0x92, 0x86, 0xdf, 0x90, 0x8d, 0x96, 0x98, 0x96, 0x91, 0x9e, 0x93, 0xdf, 0x9c, 0x90, 0x9b, 0x9a, 0xde, 0xf5, '\0' };
	Decrypt( STR7 );//Send me your C/C++ solution code, and you will receive my original code!
	cout << endl;
	for( short i = 0; i < 80; i ++ ) cout << '*';
	cout << endl;
}

void Congratulations( void )                //Show the Congratulations sign;
{
	cout << endl;
	for( short i = 0; i < 80; i ++ ) cout << '*';
	short STR1[ ] = { 0xa6, 0x90, 0x8a, 0xd8, 0x8d, 0x9a, 0xdf, 0x8c, 0x90, 0xdf, 0xbc, 0xb0, 0xb0, 0xb3, 0xde, 0x81, 0xdf, 0xbd, 0x96, 0x91, 0x98, 0x90, 0xde, 0xdf, 0xa8, 0x9a, 0x93, 0x93, 0xd2, 0x9b, 0x90, 0x91, 0x9a, 0xde, 0xf5, '\0' };
	Decrypt( STR1 );//You're so COOL!~ Bingo! Well-done!
	short STR2[ ] = { 0xb2, 0x86, 0xdf, 0x9a, 0x92, 0x9e, 0x96, 0x93, 0xdf, 0x9e, 0x9b, 0x9b, 0x8d, 0x9a, 0x8c, 0x8c, 0xdf, 0x96, 0x8c, 0xdf, 0x87, 0x96, 0x9e, 0x86, 0x8a, 0x9e, 0x91, 0x85, 0x97, 0x90, 0x91, 0x98, 0xbf, 0x98, 0x92, 0x9e, 0x96, 0x93, 0xd1, 0x9c, 0x90, 0x92, 0xf5, '\0' };
	Decrypt( STR2 );//My email address is xiayuanzhong@gmail.com
	short STR3[ ] = { 0xb2, 0x86, 0xdf, 0x91, 0x9e, 0x92, 0x9a, 0xdf, 0x96, 0x8c, 0xdf, 0xa7, 0x96, 0x9e, 0xdf, 0xa6, 0x8a, 0x9e, 0x91, 0x85, 0x97, 0x90, 0x91, 0x98, 0xd3, 0xdf, 0x97, 0x90, 0x88, 0xdf, 0x9e, 0x9d, 0x90, 0x8a, 0x8b, 0xdf, 0x92, 0x9e, 0x94, 0x96, 0x91, 0x98, 0xdf, 0x99, 0x8d, 0x96, 0x9a, 0x91, 0x9b, 0x8c, 0xc0, 0xf5, '\0' };
	Decrypt( STR3 );//My name is Xia Yuanzhong, how about making friends?
	for( short i = 0; i < 80; i ++ ) cout << '*';
	cout << endl;
}

short Fibonacci_like( short Temp_Length )   //Get Length;
{
	/*a1=a2=a3=1; a4=a1+a3; a5=a2+a4; a6=a3+a5; ......*/
	if ( Temp_Length < 1 ) return 0;
	else if ( Temp_Length ==1 || Temp_Length ==2 || Temp_Length ==3 ) return 1;
	else return Fibonacci_like( Temp_Length - 1 ) + Fibonacci_like( Temp_Length - 3 );
}

void Decrypt( short temp[ 0xFF ] )          //Output Message;
{
	if ( ( int )temp[ 0 ] < 0 ) return;//Cheating CALL
	for ( short k = 0; temp[k] != (short)'\0'; k ++ ) {
		cout << (char)( ~(int)temp[k] ); 
	}
}
