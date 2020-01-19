#ifndef NAMEFUNCTIONS
#define NAMEFUNCTIONS
#endif

#include "General.h"

bool fnIsValidName( const char *pszName );
void fnAdjustName( const char *pszName, char *pszAdjustedName );
bool fnGetTheName( char *pszName );

bool fnGetTheName( char *pszName )
{
    int NameLen = 0x0;
    bool fValid = true;

    printf( "Enter the Name: " );
    fgets( pszName, MaxNameLen + 0x1, stdin );

    NameLen = strlen( pszName ) - 0x1;
    pszName[NameLen] = '\0';

    if ( NameLen == 0x0 || NameLen > MaxNameLen )
    {
        fValid = false;
    }

    if ( !fnIsValidName( pszName ) )
    {
        fValid = false;
    }

    if ( !fValid )
    {
        printf( "Invalid name.\n" );
    }

    return fValid;
}

bool fnIsValidName( const char *pszName )
{
    bool fValid = false;

    if ( *pszName < MinChar )
    {
        fValid = false;
        return fValid;
    }

    while ( *pszName != '\0' )
    {
        if ( ( fValid = isupper( ( *pszName++ ) ) ) == true )
        {
            break;
        }

    }

    return fValid;
}

void fnAdjustName( const char *pszName, char *pszAdjustedName )
{
    if ( *pszName <= MinChar )
    {
        printf( "Ilegitimate string. Try again.\n" );
        exit( 1 );
    }

    while ( *pszName != '\0' )
    {
        if ( ( *pszName <= MinChar ) || ( *pszName > MaxChar ) )
        {
            ++pszName;
            continue;
        }
        else
        {
            *pszAdjustedName++ = *pszName++;
        }
    }
}

#undef NAMEFUNCTIONS
