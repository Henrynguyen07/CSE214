#include <stdio.h>
main(void)
{
    int i = 5;
    int  j = 2;
    int  k = 3;
    /*$print LOCAL*/
    /*$print k*/
    while(i > 0){
        j =j+1;
        if(k>0){
            int i = 8;
            k=k-1；
            /*$print i*/
            /*$print k*/
            /*$print j*/
        }
        i=i-1;
        int k = -1;  
        /*$print k*/   
        /*$print i*/  
    }
    k = k + 4;
    /*$print k*/   
    return 0;
}
