import java.io.*;
import java.util.*;

public class Problema_01 {

	static int[] deep = new int[256];
	
	public static void main(String[] args) throws IOException {
		
		//FileInputStream a = new FileInputStream("/home/gllera/Desktop/fe.in");
		
		//Scanner in = new Scanner(a);
		
		Scanner in = new Scanner(System.in);
		initialize();

		long n = in.nextLong();
		in.nextLine();

		for (long i = 0; i < n; i++) {

			String line = in.nextLine();
			
			long sol = 0;
			int[] pos = {3, 1};
			boolean uppercase = false;
			
			for ( int j = 0; j < line.length(); j++ )
			{
				if ( Character.isLetter(line.charAt(j)) )
					if ( Character.isUpperCase(line.charAt(j)) != uppercase )
					{
						sol += press(pos, '^', j == 0);
						uppercase = !uppercase;
					}
				
				sol += press (pos, line.charAt(j), j == 0);
			}
			
			System.out.println(sol);
		}
	}
	
	static int press(int[] start, char end, boolean first)
	{
		end = Character.toUpperCase(end);
		
		int[] endPosition = position(end);
		
		int sol = moveCost( start, endPosition );
		
		if ( sol == 0 && !first )
			sol = 500;
		
		sol += deep[end] * 100;
		
		start[0] = endPosition[0];
		start[1] = endPosition[1];
		
		return sol;
	}
	
	static int moveCost(int[] start, int[] end)
	{
		int dy = Math.abs(start[0] - end[0]);
		int dx = Math.abs(start[1] - end[1]);
		int min = Math.min(dx, dy);
		
		dx -= min;
		dy -= min;
		
		return min * 350 + dx * 200 + dy * 300;
	}

	static int[] position(char t) {		
		if ( t == '^' )
			return new int[] {3, 2};
		
		if (Character.isDigit(t))
			if (t == '0')
				return new int[] { 3, 1 };
			else
				return new int[] { (t - '1') / 3, (t - '1') % 3 };

		if (t == ' ')
			return new int[] { 0, 0 };

		if ( t < 'P' )
		{
			t += 3 - 'A';
			t /= 3;

			return new int[] { t / 3, t % 3 };
		}
		
		if ( t <= 'S' )
			return new int[] { 2, 0 };
		
		if ( t <= 'V' )
			return new int[] { 2, 1 };
		
		return new int[] { 2, 2 };
	}
	
	static void initialize()
	{
		deep['^'] = 1;
		
		deep[' '] = deep['A'] = deep['D'] = deep['G'] = deep['J'] = deep['M'] = deep['P'] = deep['T'] = deep['W'] = deep['0'] = 1;
		deep['1'] = deep['B'] = deep['E'] = deep['H'] = deep['K'] = deep['N'] = deep['Q'] = deep['U'] = deep['X'] = 2;
		deep['C'] = deep['F'] = deep['I'] = deep['L'] = deep['O'] = deep['R'] = deep['V'] = deep['Y'] = 3;
		deep['2'] = deep['3'] = deep['4'] = deep['5'] = deep['6'] = deep['S'] = deep['8'] = deep['Z'] = 4;
		deep['7'] = deep['9'] = 5;
	}

}
