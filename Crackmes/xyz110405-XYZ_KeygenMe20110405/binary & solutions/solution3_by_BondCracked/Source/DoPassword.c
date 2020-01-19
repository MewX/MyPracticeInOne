#ifndef DOPASSWORD
#define DOPASSWORD
#endif

/**
                            *******************
******************************* C SOURCE FILE ********************************
**                          *******************                             **
**                                                                          **
** project : KeyGen for XYZ_KeygenMe 20110405                               **
** filename : DoPassword.C                                                  **
** version : 1                                                              **
** date : Apr 25, 2011                                                      **
** purpose : This file has functions create the password, since the name is **
**           valid.                                                         **
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
**                        Definitions and macros                           **
**                                                                         **
****************************************************************************/
#define iSubVal     0x07	// These values are used to calculate
#define iAddVal     0x0A	// the lenght of the password
#define iStartAdd   0x02
#define iEndAdd     0x03

/****************************************************************************
**                                                                         **
**                        PROTOTYPES OF LOCAL FUNCTIONS                    **
**                                                                         **
****************************************************************************/
int CalcnPass( const char *pszName );
void CreatePassWord( const char *pszName, char *pszPass );

/*****************************************************************************
**                                                                          **
** Function   CreatePassWord( const char *pszName, char *pszPass )          **
**                                                                          **
** Purpose:   To create a correct password.                                 **
**                                                                          **
** Entry:     pszName : Pointer to any valid name.                          **
**            pszPass : Pointer to an array which receives the password.    **
**                                                                          **
** Called by: main                                                          **
**                                                                          **
** Returns:   The calculated password in pszPass.                           **
**                                                                          **
** Error Handling: NA                                                       **
**                                                                          **
*****************************************************************************/
void CreatePassWord( const char *pszName, char *pszPass )
{
    size_t NameLen = 0x0;	// Length of the name
    size_t nPass = 0x0;		// Length of the password
    size_t CutPoint = 0x0;	// Cut points inside password
    size_t nCapital = 0x0;	// Number of capital letters in the name
    int i = 0x0;			// Walks the string of capitals
    int k = 0x0;			// Walks inside the name
    int u = 01;
    int rest = 0x0;			// Takes the remainder from of j division by 3
    int pos = 0x0;
    char szChoice[0x3]    = {'z', 'x', 'y'};
    char szCapitals[MaxNameLen + 0x2] = {0x0};

    NameLen = strlen( pszName );

    // Calculates the length of password
    nPass = CalcnPass ( pszName );

    // Auxiliary string which contains only capital letters
    AdjustName( pszName, szCapitals );
    nCapital = strlen( szCapitals );

    // Calculates the 'cut' points inside the password
    CutPoint = ( nPass / nCapital );

    // Shows me what I get until now
    printf( "\nThe name is %s and its length is: %d\n", pszName, NameLen );
    printf( "The capitals string is %s and its length is %u\n", szCapitals, strlen(szCapitals));
    printf( "Password length = %d; Cut each = %d chars.\n", nPass, CutPoint );

    while(pos < nPass )
    {
		// 'Cut' points inside the password have
		// capital letters
        if ( ( pos % CutPoint == 0x0 ) )
        {
            if ( i < nCapital )
            {
                pszPass[pos++] = szCapitals[i++];

            }
        }

		// All characters that aren't capitals must follow the
		// first letter on the password
        if ( k < NameLen )
        {

            if ( pszName[k] < MinChar || pszName[k] > MaxChar )
            {
                pszPass[pos++] = pszName[k];
            }

            ++k;
            continue;
        }

        // The other positions are filled with x, y or z
        rest = ( u % 0x3 );
        pszPass[pos] = szChoice[rest];
        ++pos;
        ++u;

    }
    return ;
}

/*****************************************************************************
**                                                                          **
** Function   CalcnPass(const char *pszName )                               **
**                                                                          **
** Purpose:   To calculate the length of password.                          **
**                                                                          **
** Entry:     pszName : Pointer to any valid name.                          **
**                                                                          **
** Called by: CreatePassword                                                **
**                                                                          **
** Returns:   The calculated lenght of password.                            **
**                                                                          **
** Error Handling: NA                                                       **
**                                                                          **
*****************************************************************************/
int CalcnPass(const char *pszName )
{
    int i = 0x0;
    int result = 0x0;
    size_t nLen = strlen(pszName);

    result = 0x2 * nLen + iAddVal;

    if (result != 0x32)
     {
     	result -= iSubVal;
     }

	// Adjust in 0x2 the length of the password if the first
	// character of the name is a capital letter.
    if ( ( pszName[i] > MinChar ) && ( pszName[i] < MaxChar ) )
    {
        result += iStartAdd;
    }
	// Adjust in 0x3 the length of the password if the last
	// character of the name is a capital letter.
    if ( ( pszName[nLen - 0x1] > MinChar && pszName[nLen - 0x1] < MaxChar ) )
    {
        result += iEndAdd;
    }

    return result;
}
#undef DOPASSWORD
/*****************************************************************************
**                                 EOF                                      **
*****************************************************************************/
