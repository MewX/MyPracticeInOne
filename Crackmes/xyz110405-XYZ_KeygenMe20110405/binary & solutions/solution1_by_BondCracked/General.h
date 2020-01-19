#ifndef GENERAL_H
#define GENERAL_H
#endif

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define MaxNameLen 0x14
#define MaxPassLen 0x32
#define MinChar 0x40
#define MaxChar 0x5A

#ifndef NAMEFUNCTIONS
extern bool fnIsValidName( const char *pszName );
extern void fnAdjustName( const char *pszName, char *pszAdjustedName );
extern bool fnGetTheName( char *pszName );
extern int  fnCalcPassLen( int nLen, const char *pszName );
extern void fnCreatePassWord( const char *pszName, char *pszPass );
#endif

#undef GENERAL_H
