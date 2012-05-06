//Approximate time complexity: O(n + C*(number of chars per word in input))

import java.io.*;
import java.util.*;

public class Problema_09 {
	
	static StringBuilder documentsDir = new StringBuilder("/home/gllera/Desktop/documents/");

	public static void main(String[] args) throws IOException {

		//FileInputStream a = new FileInputStream("/home/gllera/Desktop/fe.in");

		//Scanner in = new Scanner(a);

		Scanner in = new Scanner(System.in);

		int c = in.nextInt();
		in.nextLine();

		ArrayList<int[]> cases = new ArrayList<int[]>(150);
		ArrayList<PriorityQueue<Integer>> remain = new ArrayList<PriorityQueue<Integer>>(150);
		ArrayList<Integer> count = new ArrayList<Integer>(150);
		ArrayList<String> word = new ArrayList<String>(150);

		ArrayList<String> inputWords = new ArrayList<String>();
		ArrayList<Integer> inputCount = new ArrayList<Integer>();
		ArrayList<Integer> inputPos = new ArrayList<Integer>();

		cases.add(new int[256]);
		remain.add(new PriorityQueue<Integer>());
		count.add(0);
		word.add(null);

		String[] sol = new String[c];

		for (int i = 0; i < c; i++) {
			String[] line = in.nextLine().split(" ");

			line[0] = line[0].toLowerCase();

			inputWords.add(line[0]);
			inputCount.add(Integer.parseInt(line[1]));
			inputPos.add(i);

			int index = 0;

			for (char j : line[0].toCharArray()) {
				if (cases.get(index)[j] == 0) {
					cases.get(index)[j] = cases.size();

					cases.add(new int[256]);
					remain.add(new PriorityQueue<Integer>());
					count.add(0);
					word.add(null);
				}
				index = cases.get(index)[j];
			}

			word.set(index, line[0]);
			remain.get(index).add(inputCount.get(inputCount.size() - 1));
		}

		int currentFile = 0;

		while (inputWords.size() != 0 && ++currentFile <= 800) {
			String path = documentsDir + String.format("%04d", currentFile);

			Scanner reader = new Scanner(new FileInputStream(path));
			
			int linePos = 0;

			while (reader.hasNext() && inputWords.size() != 0) {
				String line = reader.nextLine();

				linePos++;
				int index = 0;
				int wordPos = 1;

				for (int i = 0; i < line.length(); i++) {
					
					char character = Character.toLowerCase(line.charAt(i));
					
					if (cases.get(index)[character] == 0) {
						i = line.indexOf(' ', i);
						wordPos++;

						if (i == -1)
							break;

						index = 0;
					} else {
						index = cases.get(index)[character];

						if (i == line.length() - 1 || Character.toLowerCase(line.charAt(i + 1) ) == ' ' ) {
							if (remain.get(index).size() != 0) {

								count.set(index, count.get(index) + 1);
								
								while (count.get(index).equals(remain.get(index).peek())) {
									remain.get(index).poll();

									for (int j = 0; j < inputWords.size(); j++)
										if (inputWords.get(j).equals(word.get(index)) && count.get(index).equals(inputCount.get(j))) {
											
											sol[inputPos.get(j)] = currentFile + "-" + linePos + "-" + wordPos;

											inputWords.remove(j);
											inputCount.remove(j);
											inputPos.remove(j);

											break;
										}
								}
							}

							if (inputWords.size() == 0)
								break;

							index = 0;
						}
					}
				}
			}
		}

		for (int i = 0; i < sol.length; i++)
			System.out.println(sol[i] == null ? "" : sol[i]);
	}
}
