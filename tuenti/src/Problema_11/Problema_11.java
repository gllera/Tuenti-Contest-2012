// Optimized and hashed to get speed

package Problema_11;

import java.io.*;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Problema_11 {

	static ArrayList<SimpleEntry<Integer, Integer>>[] frecuencyHash = (ArrayList<SimpleEntry<Integer, Integer>>[]) new ArrayList[26];
	static byte[][] charCount;
	static int[] tokens = new int[256], values = new int[256];
	static int[] maxOfTheChar = new int[256];
	static boolean[][] charOnReserve;
	
	public static void main(String[] args) throws IOException {

		initialize();
		
		FileInputStream fis = new FileInputStream("/home/gllera/Desktop/descrambler_wordlist.txt");
		Scanner in = new Scanner(fis);
		
		String[] solString = new String[charCount.length];
		int[] solValue = new int[charCount.length];
		
		while ( in.hasNext() )
		{
			String text = in.nextLine();
			
			int value = 0;
			boolean[] tom = new boolean[256]; 
			byte[] charCountTest = new byte[256];
			int maxOfTheSame = 0;
			int token = 0;
			ArrayList<Character> characters = new ArrayList<Character>(26); 
			
			boolean isValid = true;
			
			for ( int i = 0; i < text.length(); i++ )
			{
				if ( ++charCountTest[text.charAt(i)] > maxOfTheChar[text.charAt(i)] )
				{
					isValid = false;
					break;
				}
				
				maxOfTheSame = Math.max(maxOfTheSame, charCountTest[text.charAt(i)]);
				token |= tokens[text.charAt(i)];
				
				if ( !tom[text.charAt(i)] )
				{
					tom[text.charAt(i)] = true;
					characters.add(text.charAt(i));
				}
				
				value += values[text.charAt(i)];
			}
			
			if ( isValid )
				for ( SimpleEntry<Integer, Integer> data : frecuencyHash[maxOfTheSame] )
				{
					if ( data.getKey() < token )
						break;
					
					if ( (data.getKey() & token) == token )
					{
						if ( solValue[data.getValue()] >= value )
							continue;
						
						boolean flag = true, reserveTom = false, canBe = false;
						
						for ( Character charToCheck : characters )
						{
							if ( charOnReserve[data.getValue()][charToCheck] )
								canBe = true;
							
							if ( charCountTest[charToCheck] > charCount[data.getValue()][charToCheck] )
								if ( !reserveTom && charOnReserve[data.getValue()][charToCheck] && ( charCountTest[charToCheck] - charCount[data.getValue()][charToCheck] == 1 ) )
									reserveTom = true;
								else
								{
									flag = false;
									break;
								}
						}
						
						if ( flag && canBe )
						{
							solValue[data.getValue()] = value;
							solString[data.getValue()] = text;
						}
					}
						
				}
		}
				
		for ( int i = 0; i < solString.length; i++ )
			System.out.println(solString[i] + " " + solValue[i]);
	}

	static void initialize() throws FileNotFoundException {

//		FileInputStream a = new FileInputStream("/home/gllera/Desktop/fe.in");
//
//		Scanner in = new Scanner(a);

		Scanner in = new Scanner(System.in);

		int c = in.nextInt();
		in.nextLine();
		
		values['A'] = values['E'] = values['I'] = values['L'] = values['N'] = values['O'] = values['R'] = values['S'] = values['T'] = values['U'] = 1;
		values['D'] = values['G'] = 2;
		values['B'] = values['C'] = values['M'] = values['P'] = 3;
		values['F'] = values['H'] = values['V'] = values['W'] = values['Y'] = 4;
		values['K'] = 5;
		values['J'] = values['X'] = 8;
		values['Q'] = values['Z'] = 10;
		
		charCount = new byte[c][256];
		charOnReserve = new boolean[c][256];

		for (int i = 0; i < 26; i++)
			frecuencyHash[i] = new ArrayList<SimpleEntry<Integer, Integer>>();

		for (int i = 'A', t = 1; i <= 'Z'; i++, t *= 2)
			tokens[i] = t;

		
		for (int i = 0; i < c; i++) {
			String[] line = in.nextLine().split(" ");

			int token = 0;
			int maxOfTheSame = 0;

			for (int j = 0; j < line[0].length(); j++) {
				maxOfTheSame = Math.max(maxOfTheSame, ++charCount[i][line[0].charAt(j)] );
				maxOfTheChar[line[0].charAt(j)] = Math.max(maxOfTheChar[line[0].charAt(j)], charCount[i][line[0].charAt(j)]);
				token |= tokens[line[0].charAt(j)];
			}

			for (int j = 0; j < line[1].length(); j++) {
				maxOfTheSame = Math.max(maxOfTheSame, charCount[i][line[1].charAt(j)] + 1);
				maxOfTheChar[line[1].charAt(j)] = Math.max(maxOfTheChar[line[1].charAt(j)], charCount[i][line[1].charAt(j)] + 1);
				charOnReserve[i][line[1].charAt(j)] = true;
				token |= tokens[line[1].charAt(j)];
			}

			SimpleEntry<Integer, Integer> newData = new SimpleEntry<Integer, Integer>(token, i);
			
			for ( int j = 1; j <= maxOfTheSame; j++ )
				frecuencyHash[j].add(newData);
		}

		for (ArrayList<SimpleEntry<Integer, Integer>> array : frecuencyHash)
			Collections.sort(array, new hashComparator());
	}

}
