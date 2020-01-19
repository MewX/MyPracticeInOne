#ifndef MAIN
#define MAIN
#endif

#include "General.h"

int fnCalcPassLen( int nLen, const char *pszName );

int main ( void )
{
    char szName[MaxNameLen + 0x1]  = {0x0};
    char szPass[MaxPassLen + 0x1]  = {0x0};

    // Get a valid name for this app.
    while ( !fnGetTheName( szName ) )
    {
        ;
    }

    fnCreatePassWord(szName, szPass);
    system("PAUSE");
    return 0x0;
}

#undef MAIN

