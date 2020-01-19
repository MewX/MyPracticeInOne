#include "..\CommenHead.h"


static void Getdata()
{
	//FileMode=1;
	char TimeTemp[11];
	ass_originJP.seekg(0,ios_base::beg);
	while(!ass_originJP.eof())
	{
		ass_originJP>>all[StructAmount].num_s;
		if(all[StructAmount].num_s==0)
		{
			all[StructAmount].num_s=-1;
			break;
		}
		//cout<<"      "<<all[StructAmount].num_s<<endl;
		ass_originJP>>all[StructAmount].num_l;
		//cout<<"      "<<all[StructAmount].num_l<<endl;
		ass_originJP>>all[StructAmount].num_w;
		//cout<<"      "<<all[StructAmount].num_w<<endl;
		ass_originJP>>TimeTemp;
		all[StructAmount].stime=cti(TimeTemp);
		//cout<<"      "<<all[StructAmount].stime<<endl;
		ass_originJP>>TimeTemp;
		all[StructAmount].etime=cti(TimeTemp);
		//cout<<"      "<<all[StructAmount].etime<<endl;
		ass_originJP>>all[StructAmount].word;
		//cout<<"      "<<all[StructAmount].word<<endl;

		//ass_originJP.getline(TMP,50,'\n');
		//cout<<TMP<<endl;

		all[StructAmount].num_line=StructAmount+1;
		StructAmount+=1;
		//cout<<StructAmount<<endl;
		//system("PAUSE");
	}
	if(FileMode==1||FileMode==3||FileMode==5||FileMode==7)
	{
		ass_originCN.seekg(0,ios_base::beg);
		system("PAUSE");
		while(!ass_originCN.eof())
		{
			ass_originCN>>all[StructAmount].num_s;
			if(all[StructAmount].num_s==0)
			{
				all[StructAmount].num_s=-1;
				//ass_originCN.getline(TMP,50,'\n');
				break;
			}
			ass_originCN>>all[StructAmount].num_l;
			ass_originCN>>all[StructAmount].num_w;
			ass_originCN>>TimeTemp;
			all[StructAmount].stime=cti(TimeTemp);
			ass_originCN>>TimeTemp;
			all[StructAmount].etime=cti(TimeTemp);
			ass_originCN>>all[StructAmount].word;
			all[StructAmount].num_line=StructAmount+1;
			StructAmount+=1;
			//cout<<StructAmount<<endl;
		}
	}
	if(FileMode==2||FileMode==3||FileMode==6||FileMode==7)
	{
		ass_originJPrm.seekg(0,ios_base::beg);
		system("PAUSE");
		while(!ass_originJPrm.eof())
		{
			ass_originJPrm>>all[StructAmount].num_s;
			if(all[StructAmount].num_s==0)
			{
				all[StructAmount].num_s=-1;
				//ass_originJPrm.getline(TMP,50,'\n');
				break;
			}
			ass_originJPrm>>all[StructAmount].num_l;
			ass_originJPrm>>all[StructAmount].num_w;
			ass_originJPrm>>TimeTemp;
			all[StructAmount].stime=cti(TimeTemp);
			ass_originJPrm>>TimeTemp;
			all[StructAmount].etime=cti(TimeTemp);
			ass_originJPrm>>all[StructAmount].word;
			all[StructAmount].num_line=StructAmount+1;
			StructAmount+=1;
			//ass_temp<<StructAmount<<endl;
		}
	}
	if(FileMode==4||FileMode==5||FileMode==6||FileMode==7)
	{
	}


	for(k=0; k<StructAmount; k++)
	{
		ass_temp<<all[k].num_s<<' '<<all[k].num_l<<' '<<all[k].num_w<<' '<<itc(all[k].stime)<<' '<<itc(all[k].etime)<<' '<<all[k].word<<' '<<all[k].num_line<<endl;
	}
}