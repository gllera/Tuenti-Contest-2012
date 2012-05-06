import java.io.*;
import java.util.*;

public class Problema_06 {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);

		int c = in.nextInt();
		in.nextLine();

		for (int i = 0; i < c; i++) {

			int w = in.nextInt();
			int h = in.nextInt();
			int ct = in.nextInt();
			in.nextLine();

			w *= ct;
			h *= ct;

			String[] words = in.nextLine().split(" ");

			int maxLetters = 0;
			int lettersCount = 0;

			for (int j = 0; j < words.length; j++) {

				lettersCount += words[j].length();

				if (words[j].length() > maxLetters)
					maxLetters = words[j].length();
			}

			int maxFontSize = 0;

			for (int j = Math.min(w / maxLetters, h); j > 0; j--) {
				int tw = words[0].length() * j, th = j;

				for (int k = 1; k < words.length; k++)
					if (words[k].length() * j + tw + j <= w)
						tw += words[k].length() * j + j;
					else {
						th += j;
						if (th <= h)
							tw = words[k].length() * j;
						else
							break;
					}

				if (th <= h) {
					maxFontSize = j;
					break;
				}
			}

			double tmp = (1.0 / ct) * lettersCount * ((maxFontSize * maxFontSize) / 2.0);
			int sol;

			if (Math.abs(tmp - (int) tmp) <= 0.000001)
				sol = (int) tmp;
			else
				sol = (int) Math.ceil(tmp);

			System.out.println("Case #" + (i + 1) + ": " + sol);
		}
	}
}
