#ifndef MAIN
#define MAIN
#endif

/**
                            *******************
******************************* C SOURCE FILE ********************************
**                          *******************                             **
**                                                                          **
** project : KeyGen for XYZ_KeygenMe 20110405                               **
** filename : Main.C                                                        **
** version : 1                                                              **
** date : Apr 25, 2011                                                      **
** purpose : This file has the main function to start and exit the program. **
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

int main ( void )
{
    char szName[MaxNameLen + 0x1]  = {0x0};
    char szPass[MaxnPass + 0x1]  = {0x0};

    // Get a valid name for this app
    while ( !GetTheName( szName ) )
    {
        ;
    }

	// Create a valid password for the name
    CreatePassWord(szName, szPass);

    return 0x0;
}

#undef MAIN
/*****************************************************************************
**                                 EOF                                      **
*****************************************************************************/
