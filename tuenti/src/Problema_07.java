import java.io.*;
import java.util.*;

public class Problema_07 {

	static long[] needed;
	static long[] token;
	
	static StringBuilder sol;
	static long taken = 0;
	static char[] letters;
	
	public static void main(String[] args) throws IOException {

//		FileInputStream a = new FileInputStream("/home/gllera/Desktop/fe.in");
//		
//		Scanner in = new Scanner(a);
		
		Scanner in = new Scanner(System.in);
		
		initialize();
		boolean[] isUsed = new boolean[256];

		while ( in.hasNext() )
		{
			String word = in.nextLine();
			
			isUsed[word.charAt(0)] = isUsed[word.charAt(1)] = isUsed[word.charAt(2)] = true;
			
			needed[word.charAt(1)] |= token[word.charAt(0)];
			needed[word.charAt(2)] |= token[word.charAt(1)] | token[word.charAt(0)];
		}
		
		int letterCount = 0;
		
		for ( int i = 0; i < 256; i++ )
			if ( isUsed[i] )
				letterCount++;
		
		letters = new char[letterCount];
		sol = new StringBuilder(letterCount);
		
		for ( int i = 0, t = 0; i < 256; i++ )
			if ( isUsed[i] )
				letters[t++] = (char) i;
		
		compute();
	}
	
	static void compute()
	{
		if ( sol.length() == letters.length )
			System.out.println(sol);
		else
			for ( int i = 0; i < letters.length; i++ )
				if ( (taken & token[letters[i]]) == 0 && (needed[letters[i]] & taken) == needed[letters[i]] )
				{
					taken += token[letters[i]];
					sol.append(letters[i]);
					compute();
					sol.deleteCharAt(sol.length()-1);
					taken -= token[letters[i]];
				}
	}
	
	static void initialize()
	{
		long index = 1;
		
		token = new long[256];
		needed = new long[256];
		
		for ( int i = '0'; i <= '9'; i++ )
		{
			token[i] = index;
			index *= 2;
		}
		for ( int i = 'A'; i <= 'Z'; i++ )
		{
			token[i] = index;
			index *= 2;
		}
		for ( int i = 'a'; i <= 'z'; i++ )
		{
			token[i] = index;
			index *= 2;
		}
	}
}
