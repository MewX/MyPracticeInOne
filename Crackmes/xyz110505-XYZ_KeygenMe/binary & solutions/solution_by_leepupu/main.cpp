#include <iostream>
#include <string.h>

using namespace std;

int count_passlen(int);

int main()
{
    while(1)
    {
        char name[30] = {0};
        char pass[100] = {0};
        pass[0] = 'X';
        pass[1] = '3';
        pass[2] = '_';
        cout << "Name(Length 6~14): ";
        cin >> name;
        int l = strlen(name);
        int pass_len = count_passlen(l);
        if(l<=5 || l>8)
        {
            if(l<=10)
                pass_len = pass_len;
            else if(l<=14)
                pass_len = pass_len >> 1;
        }
        else
            pass_len *= 2;
        //cout << pass_len << endl;
        pass_len -= 3;
        for(int i=0;i<pass_len;i++)
        {
            int n = (name[i%l]);
            if( n > 126)
                pass[i+3] = n-i;
            else
                pass[i+3] = n+i;
        }
        cout << "The password is: " << pass << endl;
    }
    return 0;
}

int count_passlen(int n)
{
    if(n<0) return 0;
    if(n<4)
        return 1;
    else
        return count_passlen(n-1)+count_passlen(n-3);
}
