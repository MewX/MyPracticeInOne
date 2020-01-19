#include "..\CommenHead.h"

static void KCreator()
{
	char FILENAME[100];
	short LanguageMode;
	char SAVE[100];
	cout<<"FileName: ";
	cin>>FILENAME;
	cout<<"LanguageMode: ";
	cin>>LanguageMode;
	cout<<"SaveFile: ";
	cin>>SAVE;
	fstream CreatorFile;
	fstream SaveFile;
	CreatorFile.open(FILENAME,ios::in);
	SaveFile.open(SAVE,ios::trunc|ios::out);
	char TempLine[100];
	short TempWordAmount=0; 
	short kc=0;


	SaveFile<<"[Script Info]"<<endl;
	SaveFile<<"Title:KCreatorFile"<<endl;
	SaveFile<<"Synch Point:0"<<endl;
	SaveFile<<"ScriptType:v4.00+"<<endl;
	SaveFile<<"Collisions:Normal"<<endl;
	SaveFile<<"PlayResX:640"<<endl;
	SaveFile<<"PlayResY:360"<<endl;
	SaveFile<<"Timer:100.0000"<<endl;
	SaveFile<<endl;
	SaveFile<<"[V4+ Styles]"<<endl;
	SaveFile<<"Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing, Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, Encoding"<<endl;
	SaveFile<<"Style: Default,¿¬Ìå,25,&H00FFFFFF,&HF0000000,&H00000000,&HF0000000,1,0,0,0,100,100,0,0.00,1,1,0,2,30,30,10,134"<<endl;
	SaveFile<<endl;
	SaveFile<<"[Events]"<<endl;
	SaveFile<<"Format: Layer, Start, End, Style, Actor, MarginL, MarginR, MarginV, Effect, Text"<<endl;
	if(LanguageMode==1||LanguageMode==2)
	{
		while(!CreatorFile.eof())
		{
			CreatorFile.getline(TempLine,100,'\n');
			TempWordAmount=strlen(TempLine);
			if(TempWordAmount==0||TempLine[0]==' '||TempLine[0]==';')
			{
				continue;
			}
			if(LanguageMode==1)
			{
				SaveFile<<"Dialogue: 0,0:00:00.00,0:00:00.00,*Default,NTP,0000,0000,0000,,--"<<endl;
			}
			else if(LanguageMode==2)
			{
				SaveFile<<"Dialogue: 0,0:00:00.00,0:00:00.00,*Default,NTP,0000,0000,0000,,=="<<endl;
			}
			/*for(kc=0;kc<TempWordAmount;kc+=2)
			{
				if(TempWordAmount-kc==2)
				{
					SaveFile<<"Dialogue: 0,0:00:00.00,0:00:00.00,*Default,NTP,0000,0000,0000,,"<<TempLine[kc]<<TempLine[kc]<<endl;
				}
				else
				{
					SaveFile<<"Dialogue: 0,0:00:00.00,0:00:00.00,*Default,NTP,0000,0000,0000,,"<<TempLine[kc]<<TempLine[kc]<<endl;
				}
			}*/
			for(kc=0;kc<TempWordAmount;kc++)
			{
				SaveFile<<"Dialogue: 0,0:00:00.00,0:00:00.00,*Default,NTP,0000,0000,0000,,";
				while(1)
				{
					if(TempLine[kc]=='/')
					{
						break;
					}
					SaveFile<<TempLine[kc];
					kc++;
				}
				SaveFile<<endl;
			}
		}
	}
	else
	{
	}
	CreatorFile.close();
	SaveFile.close();
}