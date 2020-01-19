##########################################
## Makefile for XYZ_KeygenMe 20110405   ##
## KeygenMe's author: Xia Yuanzhong     ##
## KeyGen's author: BondCracked         ##
##########################################

##########################################
##              Path macros             ##
##      Adjust your paths here, please  ##
##########################################
BASEFOLDER = C:\MINGW
BIN=$(BASEFOLDER)\BIN
LB=$(BASEFOLDER)\LIB
INC=$(BASEFOLDER)\INCLUDE

##########################################
##              Tools macros            ##
##      Select your tools here, please  ##
##########################################
CC=$(BIN)\mingw32-gcc.exe
LN=$(BIN)\mingw32-g++.exe

##########################################
##              Project macros          ##
##      General your maros here, please ##
##########################################
NAME=KeyGen
FILES= main.c Namefunctions.c DoPassword.c
OBJS = main.o Namefunctions.o DoPassword.o
TARGET = $(NAME).EXE

##########################################
##              Flags macros            ##
##        Tune your flags here, please  ##
##########################################
CFLAGS= -Wall -m32 -mconsole -O3 -g -c
LFLAGS= -o $(TARGET) -s

##########################################
##                                      ##
##              Inference rules         ##
##                                      ##
##########################################
$(TARGET): OBJECTS
		@$(LN) $(LFLAGS) $(OBJS)
	@echo linking files
	@echo Generated: $(TARGET)

##########################################

OBJECTS: $(FILES)
		@$(CC) $(CFLAGS) $(FILES)
	@echo compiling files

##########################################
##       To execute the program         ##
##########################################
run: $(NAME).exe
		$(NAME).exe

##########################################
##          To clean up the dir         ##
##########################################
clean:
		@erase *.o
