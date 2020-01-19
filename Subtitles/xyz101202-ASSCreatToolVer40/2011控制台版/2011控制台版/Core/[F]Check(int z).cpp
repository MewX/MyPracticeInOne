#include "stdafx.h"
#include "..\stdafx.h"
/*static*/ void ASScodeCore::Check(int z)//检测出错时就显示行数，开始使i=-1
{
	i=-1;
	char j[1000];
	if(z==1){
	while(!ass_in.eof())
	{
		//cout<<j<<endl;
		
		d++;
		h++;
		ass_in.getline(j,999,'\n');
		a=strlen(j);
		//cout<<a<<endl;
		if(a==0||(int)j[0]==59)
		{
			h--;
			continue;
		}
		for(c=0;c<a;c++)
		{
			if((int)j[c]==44)//c是第一个逗号的位置-1
			{
				break;
			}
		}
		if(c==a)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)这一行貌似一个逗号都没有就结束了！"<<endl;
			i++;
			continue;
		}
		else if(c==a-1)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)这一行貌似只有一个逗号就结束了！"<<endl;
			i++;
			continue;
		}
		else if(c==0)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第一个半角逗号前似乎没有字符！"<<endl;
			i++;
			e=c;
		}
		else
		{
			e=c;//position of first char ','
			for(c=0;c<e;c++)
			{
				if((int)j[c]<48||(int)j[c]>57)
				{
					cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)在第一个半角逗号前似乎出现了不该出现的字符！"<<endl;
					i++;
					break;
				}
			}
		}
		f=e+1;//pos of the word after first ','
		for(c=f;c<a;c++)
		{
			if((int)j[c]==44)
			{
				break;
			}
		}
		if(c==a-1)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)这一行貌似只有两个逗号就结束了！"<<endl;
			i++;
			continue;
		}
		else if(c==a)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)在第一个半角逗号后似乎没有半角逗号了！"<<endl;
			i++;
			continue;
		}
		else if(c==f)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第二个半角逗号前似乎没有字符！"<<endl;
			i++;
			e=c;
		}
		else
		{
			e=c;//pos of char ','
			for(c=f;c<e;c++)
			{
				if((int)j[c]<48||(int)j[c]>57)
				{
					cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)在第二个半角逗号前似乎出现了不该出现的字符！"<<endl;
					i++;
					break;
				}
			}
		}
		f=e+1;
		for(c=f;c<a;c++)
		{
			if((int)j[c]==44)
			{
				break;
			}
		}
		if(c==a-1)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)这一行貌似只有三个逗号就结束了！"<<endl;
			i++;
			continue;
		}
		else if(c==a)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)在第二个半角逗号后似乎没有半角逗号了！"<<endl;
			i++;
			continue;
		}
		else if(c==f)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第三个半角逗号前似乎没有字符！"<<endl;
			i++;
			e=c;
		}
		else
		{
			e=c;//number of char','
			for(c=f;c<e;c++)
			{
				if((int)j[c]<48||(int)j[c]>57)
				{
					cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)在第三个半角逗号前似乎出现了不该出现的字符！"<<endl;
					i++;
					break;
				}
			}
		}
		f=e+1;
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(c=f;c<a;c++)
		{
			if((int)j[c]==44)
			{
				break;
			}
		}
		if(c==a-1)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)这一行貌似只有四个逗号就结束了！"<<endl;
			i++;
			continue;
		}
		else if(c==a)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)在第三个半角逗号后似乎没有半角逗号了！"<<endl;
			i++;
			continue;
		}
		else if(c==f)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第四个半角逗号前似乎没有字符！"<<endl;
			i++;
			e=c;
		}
		else
		{
			e=c;//number of char','
			if(c-f!=10)
			{
				cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第四个半角逗号前似乎字符数目不对！"<<endl;
				i++;
			}
			else if((int)j[f]<48||(int)j[f]>57||(int)j[f+1]!=58||(int)j[f+2]<48||(int)j[f+2]>53||(int)j[f+3]<48||(int)j[f+3]>57||(int)j[f+4]!=58||(int)j[f+5]<48||(int)j[f+5]>53||(int)j[f+6]<48||(int)j[f+6]>57||(int)j[f+7]!=46||(int)j[f+8]<48||(int)j[f+8]>57||(int)j[f+9]<48||(int)j[f+9]>57)
			{
				cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第四个半角逗号前似乎含有非法字符！"<<endl;
				i++;
			}
		}
		f=e+1;
		for(c=f;c<a;c++)
		{
			if((int)j[c]==44)
			{
				break;
			}
		}
		if(c==a-1)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)这一行貌似只有五个逗号就结束了！"<<endl;
			i++;
			continue;
		}
		else if(c==a)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)在第四个半角逗号后似乎没有半角逗号了！"<<endl;
			i++;
			continue;
		}
		else if(c==f)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第五个半角逗号前似乎没有字符！"<<endl;
			i++;
			e=c;
		}
		else
		{
			e=c;//number of char','
			if(c-f!=10)
			{
				cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第五个半角逗号前似乎字符数目不对！"<<endl;
				i++;
			}
			else if((int)j[f]<48||(int)j[f]>57||(int)j[f+1]!=58||(int)j[f+2]<48||(int)j[f+2]>53||(int)j[f+3]<48||(int)j[f+3]>57||(int)j[f+4]!=58||(int)j[f+5]<48||(int)j[f+5]>53||(int)j[f+6]<48||(int)j[f+6]>57||(int)j[f+7]!=46||(int)j[f+8]<48||(int)j[f+8]>57||(int)j[f+9]<48||(int)j[f+9]>57)
			{
				cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)第五个半角逗号前似乎含有非法字符！"<<endl;
				i++;
			}
		}
		f=e+1;
		for(c=f;c<a;c++)
		{
			if((int)j[c]==59)
			{
				break;
			}
		}
		if(c==a)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)这里或许少了收尾的半角分号！"<<endl;
			i++;
		}
		else if(c==f)
		{
			cout<<"\n错误行："<<d<<" ==> "<<j<<"\n(1级检查)收尾的半角分号前似乎没有字符！"<<endl;
			i++;
		}
		else{}
	}
	if(h==0)
	{
		cout<<"\n有效行为0,无法完成后续操作！"<<endl;
		i++;
	}}
	else if(z==2)
	{
	
		for(k=0;k<h-1;k++)
		{
			if(all[k].num_s==all[k+1].num_s && all[k].num_l==all[k+1].num_l && all[k].num_w==all[k+1].num_w)
			{
				cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
				cout<<"错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
				cout<<"(2级检查)这两行的文字坐标似乎相同=001！"<<endl;
				i++;
			}
			if(all[k].num_l!=1&&all[k].num_l!=2)
			{
				cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
				cout<<"(2级检查)这行的语言坐标含有非法数字=002！"<<endl;
				i++;
			}
			if(k==h-2 && all[k+1].num_l!=1&&all[k+1].num_l!=2)
			{
				cout<<"\n错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
				cout<<"(2级检查)这行的语言坐标含有非法数字=003！"<<endl;
				i++;
			}
			if(k==h-2 && cti(all[k+1].stime)>cti(all[k+1].etime)) 
			{
				cout<<"\n错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
				cout<<"(2级检查)这行的时间轴的开始时间大于结束时间=004！"<<endl;
				i++;
			}
			if(cti(all[k].stime)>cti(all[k].etime)) 
			{
				cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
				cout<<"(2级检查)这行的时间轴的开始时间大于结束时间=005！"<<endl;
				i++;
			}
			if(all[k].num_l==all[k+1].num_l && cti(all[k].etime)>cti(all[k+1].stime)&&((cti(all[k].etime)!=cti(all[k+1].etime) && cti(all[k].stime)!=cti(all[k+1].stime)) && cti(all[k].stime)!=cti(all[k].etime) && cti(all[k+1].stime)!=cti(all[k+1].etime)))
			{
				cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
				cout<<"错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
				cout<<"(2级检查)这两行的时间轴存在交叉现象=006！"<<endl;
				i++;
			}
			if(k==0 && (all[k].num_s!=1 || all[k].num_l!=1 || all[k].num_w!=1))
			{
				cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
				cout<<"(2级检查)这行的初始坐标存在跳越现象=007！"<<endl;
				i++;
			}
			if(all[k].num_s==all[k+1].num_s || all[k].num_s==all[k+1].num_s-1)
			{
				if(all[k].num_s==all[k+1].num_s-1 && all[k+1].num_l!=1)
				{
					cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
					cout<<"错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
					cout<<"(2级检查)这两行的语言坐标存在跳越现象=008！"<<endl;
					i++;
				}
				if(all[k].num_l==all[k+1].num_l || all[k].num_l==all[k+1].num_l-1)
				{
					if(all[k].num_l!=all[k+1].num_l-1 && all[k].num_l==all[k+1].num_l-1 && all[k+1].num_l!=1)
					{
						cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
						cout<<"错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
						cout<<"(2级检查)这两行的字坐标存在跳越现象=009！"<<endl;
						i++;
					}
					if((all[k].num_l==all[k+1].num_l-1 || all[k].num_s==all[k+1].num_s-1) && all[k+1].num_w!=1)
					{
						cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
						cout<<"错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
						cout<<"(2级检查)这两行的字坐标存在跳越现象=010！"<<endl;
						i++;
					}
					else
					{
					}
				}
				else
				{
					if(all[k].num_s!=all[k+1].num_s-1)
					{
						cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
						cout<<"错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
						cout<<"(2级检查)这两行的语言坐标存在跳越现象=011！"<<endl;
						i++;
					}
				}
			}
			else
			{
				cout<<"\n错误行："<<all[k].num_line<<" ==> "<<all[k].num_s<<","<<all[k].num_l<<","<<all[k].num_w<<","<<all[k].stime<<","<<all[k].etime<<","<<all[k].word<<";"<<endl;
				cout<<"错误行："<<all[k+1].num_line<<" ==> "<<all[k+1].num_s<<","<<all[k+1].num_l<<","<<all[k+1].num_w<<","<<all[k+1].stime<<","<<all[k+1].etime<<","<<all[k+1].word<<";"<<endl;
				cout<<"(2级检查)这两行的行坐标存在跳越现象=012！"<<endl;
				i++;
			}
		}
	}
	else{}
	cout<<"共"<<d<<"行数据，其中有效行为"<<h<<"行，一共找到了"<<i+1<<"条错误！"<<endl;
	a=0;
	b=0;
	c=0;
	e=0;
	f=0;
	g=0;
	k=0;
}
