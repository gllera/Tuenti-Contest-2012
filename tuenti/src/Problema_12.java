import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

public class Problema_12 {

	public static void main(String[] args) throws IOException {
	
		String[] man = new String[] {
				"62cd275989e78ee56a81f0265a87562e",   // Least significant bit 	- power
				"ed8ce15da9b7b5e2ee70634cc235e363",   // Qr code  				- courage
				"a541714a17804ac281e6ddda5b707952",	  // Meta data  			- wisdom
				"1ee7453658914cd7463B77032fdbb623"    // key
				};

		int[][] val = new int[4][man[0].length()];

		for (int i = 0; i < man.length; i++)
			for (int j = 0; j < man[0].length(); j++)
				val[i][j] = Integer.valueOf(
						Character.toString(man[i].charAt(j)), 16);

		StringBuilder sol = new StringBuilder();

		for (int i = 0; i < man[0].length(); i++)
			sol.append(Integer.toHexString(((val[0][i] + val[1][i] + val[2][i] + val[3][i]) % 16)));

		System.out.print(sol);
	}
}
