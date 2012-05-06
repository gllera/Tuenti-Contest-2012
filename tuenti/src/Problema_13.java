import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Problema_13 {

	static boolean matIndex = true;

	public static void main(String[] args) throws IOException {

//	    FileInputStream a = new
//		FileInputStream("/home/gllera/Desktop/fe.in");

//		Scanner in = new Scanner(a);

		Scanner in = new Scanner(System.in);

		long c = in.nextLong();

		for (int o = 1; o <= c; o++) {
			int l, n;
			BigInteger sol = new BigInteger("1");
			n = in.nextInt();
			l = in.nextInt();

			int[] matrix = new int[n + 1];

			indexL = l;
			indexR = n;
			matIndex = true;

			for (int i = 1; i <= n; i++) {
				matrix[matIndex ? getLeft(n, l) : getRigth(n, l)] = i;
				matIndex = !matIndex;
			}

			boolean[] tom = new boolean[n + 1];

			for (int i = 1; i <= n; i++)
				if (!tom[i]) {
					Integer count = 0, index = i;

					while (!tom[index]) {
						tom[index] = true;
						index = matrix[index];
						count++;
					}

					BigInteger parameter2 = new BigInteger(count.toString());
					sol = sol.multiply(parameter2).divide(gcd(sol, parameter2));
				}

			System.out.println("Case #" + o + ": " + sol);
		}

		in.nextLine();
	}

	static public BigInteger gcd(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO))
			return a;
		else
			return gcd(b, a.mod(b));
	}

	static int indexL = 1;

	static int getLeft(int n, int l) {
		return indexL >= 1 ? indexL-- : indexR--;
	}

	static int indexR;

	static int getRigth(int n, int l) {
		return indexR > l ? indexR-- : indexL--;
	}
}
