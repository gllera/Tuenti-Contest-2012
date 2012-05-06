import java.io.*;
import java.text.*;
import java.util.*;

public class Problema_05 {

	static int[] parsedLedsArray, ledsCountArray, acumulativeTable;
	static int ONE_DAY = 60 * 60 * 24 * 1000;
	static Calendar c1, c2;

	public static void main(String[] args) throws FileNotFoundException, ParseException {

		//FileInputStream a = new FileInputStream("/home/gllera/Desktop/fe.in");

		//Scanner in = new Scanner(a);

		initialize();

		Scanner in = new Scanner(System.in);

		DateFormat formatter = new SimpleDateFormat("y-M-d k:m:s");

		while (in.hasNext()) {
			String[] dates = in.nextLine().split(" - ");
			
			c1.setTime(formatter.parse(dates[0]));
			c2.setTime(formatter.parse(dates[1]));
						
			int daysC1 = (int)(( c1.getTimeInMillis() + ONE_DAY / 24 ) / ONE_DAY);
			int daysC2 = (int)(( c2.getTimeInMillis() + ONE_DAY / 24 ) / ONE_DAY);
						
			int diferenceInDays = daysC2 - daysC1;
			int secsOnADayC1 = (int)((c1.getTimeInMillis() + ONE_DAY / 24) % ONE_DAY) / 1000;
			int secsOnADayC2 = (int)((c2.getTimeInMillis() + ONE_DAY / 24) % ONE_DAY) / 1000;
			
			long sol = 0;
			
			if ( diferenceInDays == 0 )
				sol = acumulativeTable[secsOnADayC2] - acumulativeTable[secsOnADayC1];
			else
			{
				sol  = acumulativeTable[ONE_DAY / 1000] * (diferenceInDays - 1);
				sol += acumulativeTable[ONE_DAY / 1000] - acumulativeTable[secsOnADayC1];
				sol += acumulativeTable[secsOnADayC2];
			}
				
			System.out.println(sol);
		}
	}

	static void initialize() {

		acumulativeTable = new int[60 * 60 * 24 + 1];
		
		c1    = Calendar.getInstance();
		c2    = Calendar.getInstance();
		
		parsedLedsArray = new int[10];
		parsedLedsArray[0] = Integer.parseInt("0111111", 2);
		parsedLedsArray[1] = Integer.parseInt("0011000", 2);
		parsedLedsArray[2] = Integer.parseInt("1101101", 2);
		parsedLedsArray[3] = Integer.parseInt("1111100", 2);
		parsedLedsArray[4] = Integer.parseInt("1011010", 2);
		parsedLedsArray[5] = Integer.parseInt("1110110", 2);
		parsedLedsArray[6] = Integer.parseInt("1110111", 2);
		parsedLedsArray[7] = Integer.parseInt("0011100", 2);
		parsedLedsArray[8] = Integer.parseInt("1111111", 2);
		parsedLedsArray[9] = Integer.parseInt("1111110", 2);

		ledsCountArray = new int[256];

		for (int i = 0; i < 256; i++) {
			int t = i;
			int cont = 0;

			while (t != 0) {
				cont += t % 2;
				t /= 2;
			}

			ledsCountArray[i] = cont;
		}
		
		buildAcumulativeTable();
	}
	
	static void buildAcumulativeTable()
	{
		int totalNewClock = 0, totalOldClock = 0;
		int lastH = 0, lastM = 0, lastS = 0;
		
		for (int i = 1; i <= ONE_DAY / 1000; i++) {

			int h = i / 3600;
			int m = (i / 60) % 60;
			int s = i % 60;

			totalOldClock += ledsCountArray[parsedLedsArray[h / 10]] + ledsCountArray[parsedLedsArray[h % 10]];
			totalOldClock += ledsCountArray[parsedLedsArray[m / 10]] + ledsCountArray[parsedLedsArray[m % 10]];
			totalOldClock += ledsCountArray[parsedLedsArray[s / 10]] + ledsCountArray[parsedLedsArray[s % 10]];
			
			totalNewClock += ledsCountArray[parsedLedsArray[h / 10] - (parsedLedsArray[h / 10] & parsedLedsArray[lastH / 10])];
			totalNewClock += ledsCountArray[parsedLedsArray[h % 10] - (parsedLedsArray[h % 10] & parsedLedsArray[lastH % 10])];
			
			totalNewClock += ledsCountArray[parsedLedsArray[m / 10] - (parsedLedsArray[m / 10] & parsedLedsArray[lastM / 10])];
			totalNewClock += ledsCountArray[parsedLedsArray[m % 10] - (parsedLedsArray[m % 10] & parsedLedsArray[lastM % 10])];
			
			totalNewClock += ledsCountArray[parsedLedsArray[s / 10] - (parsedLedsArray[s / 10] & parsedLedsArray[lastS / 10])];
			totalNewClock += ledsCountArray[parsedLedsArray[s % 10] - (parsedLedsArray[s % 10] & parsedLedsArray[lastS % 10])];
			
			lastH = h;
			lastM = m;
			lastS = s;
			
			acumulativeTable[i] = totalOldClock - totalNewClock;
		}
	}
}
