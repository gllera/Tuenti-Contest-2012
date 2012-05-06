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

    int c;

    cin >> c;

    for (int i = 0; i < c; i++) {
        ulong num = 0;
        cin >> num;

        int sol = 0;
        int carring = 0;

        while (num != 0) {
            if ( num % 2 )
            {                    
                if ( carring )
                {
                    if ( num > 1 )
                        sol += 2;
                }
                else
                    sol += 1;
            } else if ( carring )
                sol += 1;
            else 
            {
                sol += 2;
                carring = true;
            }
            
            num /= 2;
        }

        cout << "Case #" << i + 1 << ": " << sol << '\n';
    }

    return 0;
}

