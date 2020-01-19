#ifndef GENERAL_H
#define GENERAL_H
#endif

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

/****************************************************************************
**                                                                         **
**                        Definitions and macros                           **
**                                                                         **
****************************************************************************/
#define MaxNameLen 0x14		// Max. length for the name
#define MaxnPass 0x32		// Max. length for the password
#define MinChar 0x40		// Char 'A'
#define MaxChar 0x5A		// Char 'Z'

/****************************************************************************
**                                                                         **
**                         COMMON VARIABLES AND FUNCTIONS                  **
**                                                                         **
****************************************************************************/
#ifndef NAMEFUNCTIONS
extern bool IsValidName( const char *pszName );
extern void AdjustName( const char *pszName, char *pszAdjustedName );
extern bool GetTheName( char *pszName );
extern int  CalcnPass( const char *pszName );
extern void CreatePassWord( const char *pszName, char *pszPass );
#endif

#undef GENERAL_H
/*****************************************************************************
**                                 EOF                                      **
*****************************************************************************/
