#ifndef DOPASSWORD
#define DOPASSWORD
#endif

#include "General.h"

#define iSubVal     0x07
#define iAddVal     0x0A
#define iStartAdd   0x02
#define iEndAdd     0x03

int fnCalcPassLen( int nLen, const char *pszName );
void fnCreatePassWord( const char *pszName, char *pszPass );

void fnCreatePassWord( const char *pszName, char *pszPass )
{
    size_t NameLen = 0x0;
    size_t OrNameLen = 0x0;
    size_t PassLen = 0x0;
    size_t CutPoint = 0x0;
    int i = 0x0, j = 0x0, k = 0x0;
    int rest = 0x0, pos = 0x0;
    char szChoice[0x3]    = {'z', 'x', 'y'};
    char szAdjustedName[MaxNameLen + 0x2] = {0x0};

    NameLen = strlen( pszName );
    OrNameLen = NameLen;

    // Calculates the length of password
    PassLen = fnCalcPassLen ( NameLen, pszName );

    // Adjust the name for lower case char.
    fnAdjustName( pszName, szAdjustedName );
    NameLen = strlen( szAdjustedName );

    // Calculates the cut points inside the password
    CutPoint = ( PassLen / NameLen );

    // Shows me what I get until now
    printf( "\nThe name is %s and its length is: %d\n", pszName, OrNameLen );
    printf( "The AdjustedName is %s and its length is %d\n", szAdjustedName, NameLen );
    printf( "Password length = %d; Cut each = %d chars.\n", PassLen, CutPoint );

    for ( j = 0x0; j < PassLen ; j++ )
    {
        if ( pos >= PassLen )
        {
            break;
        }

        if ( ( pos % CutPoint == 0x0 ) )
        {

            if ( i < NameLen )
            {
                pszPass[pos++] = szAdjustedName[i++];

                if ( j == 0x0 )
                {
                    continue;
                }
            }
        }

        while ( k < OrNameLen )
        {
            if ( pszName[k] < MinChar || pszName[k] > MaxChar )
            {
                pszPass[pos] = pszName[k];
                ++pos;
            }

            ++k;
        }

        rest = ( j % 0x3 );
        pszPass[pos] = szChoice[rest];
        ++pos;
    }

    printf( "Password = %s Name = %s\n\n", pszPass, pszName );
    return ;
}

int fnCalcPassLen( int nLen, const char *pszName )
{
    int i = 0x0;
    int PasswordLength = 0x0;

    PasswordLength = 0x2 * nLen + iAddVal - iSubVal;

    if ( ( pszName[i] > MinChar ) && ( pszName[i] < MaxChar ) )
    {
        PasswordLength += iStartAdd;
    }

    if ( ( pszName[nLen - 0x1] > MinChar && pszName[nLen - 0x1] < MaxChar ) )
    {
        PasswordLength += iEndAdd;
    }

    return PasswordLength;
}
#undef DOPASSWORD
