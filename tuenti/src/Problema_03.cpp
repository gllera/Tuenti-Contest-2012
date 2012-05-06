/* 
 * File:   main.cpp
 * Author: gllera
 *
 * Created on April 28, 2012, 1:45 PM
 */

#include <cstdlib>
#include <iostream>
#include <math.h>

using namespace std;

int main(int argc, char** argv) {
    
    ulong solMin = 0,     solI = 0,       solJ = 0;
    ulong    min = 0,        i = 0,          j = 0;
    ulong      t = 0;
    
    cin >> min;
    
    if ( cin.fail() )
        return 0;
    
    while ( cin >> t, !cin.fail() )
    {
        j+= 100;
        
        //cin >> t;
        
        if ( t > min && t - min > solMin )
        {
            solMin = t - min;
            solI = i;
            solJ = j;
        }
        
        if ( t < min )
        {
            min = t;
            i = j;
        }
    }
    
    cout << solI << " " << solJ << " " << solMin;
    
    return 0;
}


