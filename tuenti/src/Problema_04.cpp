/* 
 * File:   main.cpp
 * Author: gllera
 *
 * Created on April 28, 2012, 4:29 PM
 */

#include <cstdlib>
#include <iostream>
#include <math.h>

using namespace std;

int main(int argc, char** argv) {

    int n;

    cin >> n;

    for (int i = 0; i < n; i++) {
        long r, k, g, pos = 0;

        cin >> r;
        cin >> k;
        cin >> g;

        int* turn = new int[g];
        int* groups = new int[g];
        long* acumulatedSum = new long[g];

        for (long j = 0; j < g; j++)
        {
            cin >> groups[j];
            turn[j] = -1;
        }

        long done = 0, sum = 0, tpos = 0, sol = 0;
        bool passed = false;

        while (done < r) {

            // Just for is repeated case is funded
            if (turn[pos] != -1 && !passed) {

                long timesRepeted = (r - done) / (done - turn[pos]);

                sol += (sol - acumulatedSum[pos]) * timesRepeted;

                done += (done - turn[pos]) * timesRepeted;

                passed = true;

                if (r == done)
                    break;
            }

            turn[pos] = done;
            acumulatedSum[pos] = sol;

            sum = 0;

            do {
                sum += groups[tpos];
                tpos = tpos + 1 == g ? 0 : tpos + 1;

            } while (tpos != pos && groups[tpos] + sum <= k);

            pos = tpos;
            sol += sum;
            done++;
        }

        cout << sol << '\n';
    }

    return 0;
}


