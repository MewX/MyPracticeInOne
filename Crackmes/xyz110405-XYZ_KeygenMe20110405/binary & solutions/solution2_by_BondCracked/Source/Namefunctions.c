#ifndef NAMEFUNCTIONS
#define NAMEFUNCTIONS
#endif

/**
                            *******************
******************************* C SOURCE FILE ********************************
**                          *******************                             **
**                                                                          **
** project : KeyGen for XYZ_KeygenMe 20110405                               **
** filename : NameFunctions.C                                               **
** version : 1                                                              **
** date : Apr 25, 2011                                                      **
** purpose : This file has functions to deal with input and validation of   **
**           the name.                                                      **
**                                                                          **
******************************************************************************
**                                                                          **
** Copyright (c) 2011, BondCracked                                          **
** All rights reserved.                                                     **
**                                                                          **
******************************************************************************
VERSION HISTORY:
----------------
Version : 1
Date : Apr 25, 2011
Revised by : BondCracked
Description : Original version.
*/

/****************************************************************************
**                                                                         **
**                         MODULES USED                                    **
**                                                                         **
****************************************************************************/

#include "General.h"

/****************************************************************************
**                                                                         **
**                        PROTOTYPES OF LOCAL FUNCTIONS                    **
**                                                                         **
****************************************************************************/

bool IsValidName( const char *pszName );
void AdjustName( const char *pszName, char *pszAdjustedName );
bool GetTheName( char *pszName );

/*****************************************************************************
**                                                                          **
** Function   GetTheName( char *pszName )                                   **
**                                                                          **
** Purpose:   To get a name.                                                **
**                                                                          **
** Entry:     pszName : Pointer to an array which receives the name.        **
**                                                                          **
** Called by: main                                                          **
**                                                                          **
** Returns:   bool: true = valid name; false = invalid name.                **
**            pszName: Receives the name.                                   **
**                                                                          **
** Comment:   This function is called until it returns a valid name.        **
**                                                                          **
** Error Handling: NA                                                       **
**                                                                          **
*****************************************************************************/
bool GetTheName( char *pszName )
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

    if ( !IsValidName( pszName ) )
    {
        fValid = false;
    }

    if ( !fValid )
    {
        printf( "Invalid name.\n" );
    }

    return fValid;
}

/*****************************************************************************
**                                                                          **
** Function   IsValidName( char *pszName )                                  **
**                                                                          **
** Purpose:   To say if the name is valid or not.                           **
**                                                                          **
** Entry:     pszName : Pointer to an array which holds the name.           **
**                                                                          **
** Called by: GetTheName                                                    **
**                                                                          **
** Returns:   bool: true = valid name; false = invalid name.                **
**                                                                          **
** Error Handling: NA                                                       **
**                                                                          **
*****************************************************************************/
bool IsValidName( const char *pszName )
{
    bool fValid = false;

    // The name must contain at least one capital letter
    while ( *pszName != '\0' )
    {
        if ( ( fValid = isupper( ( *pszName++ ) ) ) == true )
        {
            break;
        }

    }

    return fValid;
}

/*****************************************************************************
**                                                                          **
** Function   AdjustName( const char *pszName, char *pszAdjustedName )      **
**                                                                          **
** Purpose:   To produce a string to be attached into the password.         **
**                                                                          **
** Entry:     pszName : Pointer to an array which holds a valid name.       **
**            pszAdjustedName : Pointer to the new generated string.        **
**                                                                          **
** Called by: CreatePassword                                                **
**                                                                          **
** Returns:   A new string in pszAdjustedName.                              **
**                                                                          **
** Error Handling: NA                                                       **
**                                                                          **
*****************************************************************************/
void AdjustName( const char *pszName, char *pszAdjustedName )
{
	// The first character cannot be < 'A'
    if ( *pszName <= MinChar )
    {
        printf( "Ilegitimate string. Try again.\n" );
        exit( 1 );
    }

	// The new string receives only capital characters.
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
/*****************************************************************************
**                                 EOF                                      **
*****************************************************************************/
