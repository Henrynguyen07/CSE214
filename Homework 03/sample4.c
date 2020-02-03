#include <stdio.h>
int main(void)
{
    /*$print k*/
    int interest = 0;
    int k=3,n;
    k = 20 + interest; /*Doesn't affect the initial value*/
    /*$print LOCAL*/
    /*$print k*/
    --interest; /*Neither does this*/
    if(k >= 3)
    {
            int interest = 15;
            interest++; /*Doesn't affect the initial value*/
            /*$print LOCAL*/
            int interest, n = 10;
            /*$print interest*/
    }else{
            int interest = 20;
            /*$print interest*/
    }
    /*$print LOCAL*/
    return 0;
}
