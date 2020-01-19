#include "..\CommenHead.h"


static void MyEncoder()//注：在句子的末尾是"--"表示日文1;末尾是"=="表示中文2;末尾是"__"表示罗马音3;末尾是".."表示rock4
{
	char TempEncoder[300], Save[SingleWordLenth], TempStartTime[11], TempEndTime[11];
	register short Count=0, Count2=0;
	short JustUse=0, LanguageMode=0;
	short SenJP=0, SenCN=0, SenJPrm=0, SenROCK=0;
	short LineCount=0;
	//cout<<">>Entered"<<endl;
	//ass_in.seekg(0,ios_base::beg);
	while(!ass_in.eof())
	{
		//cout<<"  >>Entered"<<endl;
		memset(TempEncoder,300,'\0');
		memset(TempStartTime,11,'\0');
		memset(TempEndTime,11,'\0');
		//ass_in>>TempEncoder;
		ass_in.getline(TempEncoder,300,'\n');
		//cout<<TempEncoder<<endl;
		//ass_temp<<TempEncoder<<endl;
		//system("PAUSE");
		if(JustUse<3&&TempEncoder[0]=='[')
		{
			JustUse+=1;
			continue;
		}
		if(TempEncoder[0]==';')continue;

		if(strlen(TempEncoder)<20)continue;

		if(TempEncoder[strlen(TempEncoder)-1]=='-'&&TempEncoder[strlen(TempEncoder)-2]=='-'&&TempEncoder[strlen(TempEncoder)-3]==',')
		{
			LanguageMode=1;
			cout<<"LanguageMode=1;"<<endl;
			SenJP+=1;
			LineCount=0;
			continue;
		}//JP
		else if(TempEncoder[strlen(TempEncoder)-1]=='='&&TempEncoder[strlen(TempEncoder)-2]=='='&&TempEncoder[strlen(TempEncoder)-3]==',')
		{
			LanguageMode=2;
			cout<<"LanguageMode=2;"<<endl;
			SenCN+=1;
			LineCount=0;
			continue;
		}//CN
		else if(TempEncoder[strlen(TempEncoder)-1]=='_'&&TempEncoder[strlen(TempEncoder)-2]=='_'&&TempEncoder[strlen(TempEncoder)-3]==',')
		{
			LanguageMode=3;
			cout<<"LanguageMode=3;"<<endl;
			SenJPrm+=1;
			LineCount=0;
			continue;
		}//JPrm
		else if(TempEncoder[strlen(TempEncoder)-1]=='.'&&TempEncoder[strlen(TempEncoder)-2]=='.'&&TempEncoder[strlen(TempEncoder)-3]==',')
		{
			LanguageMode=4;
			cout<<"LanguageMode=4;"<<endl;
			SenROCK+=1;
			LineCount=0;
			continue;
		}//ROCK
		else{}//设定后面输出的模式
		//输出的格式:语言编号,句子编号,字编号,开始时间,结束时间,字7
		if(LanguageMode==0)continue;
		else if(LanguageMode==1)//JP
		{
			LineCount+=1;
			Count2=0;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempStartTime[Count]='\0';
				else TempStartTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2++;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempEndTime[Count]='\0';
				else TempEndTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2=strlen(TempEncoder)-1;
			while(TempEncoder[Count2]!=',')Count2-=1;
			for(Count=Count2+1;Count<=(short)strlen(TempEncoder);Count++)
			{
				if(Count==strlen(TempEncoder))
				{
					Save[Count-Count2-1]='\0';
					break;
				}
				Save[Count-Count2-1]=TempEncoder[Count];
			}
			ass_originJP<<SenJP<<' '<<"1 "<<LineCount<<' '<<TempStartTime<<' '<<TempEndTime<<' '<<Save<<endl;
			ass_temp<<SenJP<<' '<<"1 "<<LineCount<<' '<<TempStartTime<<' '<<TempEndTime<<' '<<Save<<endl;
		}
		else if(LanguageMode==2)//CN
		{
			LineCount+=1;
			Count2=0;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempStartTime[Count]='\0';
				else TempStartTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2++;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempEndTime[Count]='\0';
				else TempEndTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2=strlen(TempEncoder)-1;
			while(TempEncoder[Count2]!=',')Count2-=1;
			for(Count=Count2+1;Count<=(short)strlen(TempEncoder);Count++)
			{
				if(Count==strlen(TempEncoder))
				{
					Save[Count-Count2-1]='\0';
					break;
				}
				Save[Count-Count2-1]=TempEncoder[Count];
			}
			ass_originCN<<SenCN<<' '<<"2 "<<LineCount<<' '<<TempStartTime<<' '<<TempEndTime<<' '<<Save<<endl;
			ass_temp<<SenCN<<' '<<"2 "<<LineCount<<' '<<TempStartTime<<' '<<TempEndTime<<' '<<Save<<endl;
		}
		else if(LanguageMode==3)//JPrm
		{
			LineCount+=1;
			Count2=0;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempStartTime[Count]='\0';
				else TempStartTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2++;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempEndTime[Count]='\0';
				else TempEndTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2=strlen(TempEncoder)-1;
			while(TempEncoder[Count2]!=',')Count2-=1;
			for(Count=Count2+1;Count<=(short)strlen(TempEncoder);Count++)
			{
				if(Count==strlen(TempEncoder))
				{
					Save[Count-Count2-1]='\0';
					break;
				}
				Save[Count-Count2-1]=TempEncoder[Count];
			}
			ass_originJPrm<<SenJPrm<<' '<<"3 "<<LineCount<<' '<<TempStartTime<<' '<<TempEndTime<<' '<<Save<<endl;
			ass_temp<<SenJPrm<<' '<<"3 "<<LineCount<<' '<<TempStartTime<<' '<<TempEndTime<<' '<<Save<<endl;
		}
		else if(LanguageMode==4)//ROCK
		{/*
			LineCount+=1;
			Count2=0;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempStartTime[Count]='\0';
				else TempStartTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2++;
			while(TempEncoder[Count2]!='.')Count2+=1;
			for(Count=0;Count<11;Count++)
			{
				if(Count==10)TempEndTime[Count]='\0';
				else TempEndTime[Count]=TempEncoder[Count2-7+Count];
			}
			Count2=strlen(TempEncoder)-1;
			while(TempEncoder[Count2]!=',')Count2-=1;
			for(Count=0;Count<(short)strlen(TempEncoder);Count++)
			{
				if(Count=strlen(TempEncoder)-1)Save[Count]='\0';
				Save[Count]=TempEncoder[Count2+Count];
			}
			ass_originROCK<<SenROCK<<' '<<"4 "<<LineCount<<' '<<TempStartTime<<' '<<TempEndTime<<' '<<Save<<endl;*/
		}
		else{}
	}
}