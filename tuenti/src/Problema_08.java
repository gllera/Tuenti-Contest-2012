import java.io.*;
import java.security.*;
import java.util.*;

public class Problema_08 {

	public static void main(String[] args) throws IOException,
			NoSuchAlgorithmException {

		//FileInputStream a = new FileInputStream("/home/gllera/Desktop/fe.in");

		//Scanner in = new Scanner(a);

		Scanner in = new Scanner(System.in);

		String queue = in.nextLine();

		LinkedList<Byte>[] transf = (LinkedList<Byte>[]) new LinkedList[52];

		byte[] pos = new byte[256];

		for (byte i = 'a', count = 0; i <= 'z'; i++, count++) {
			pos[i] = count;
			pos[count + 'A'] = (byte) (count + 26);

			transf[count] = new LinkedList<Byte>();
			transf[count + 26] = new LinkedList<Byte>();

			transf[count].add(i);
			transf[count + 26].add((byte) (count + 'A'));
		}

		while (in.hasNext()) {
			String[] tmp = in.nextLine().split(",");
			String[] changes = new String[256];

			for (String s : tmp)
				changes[s.charAt(0)] = s.substring(3);

			for (int i = 0; i < 52; i++) {
				ListIterator<Byte> it = transf[i].listIterator();

				while (it.hasNext()) {
					byte c = it.next();

					if (changes[c] != null) {
						it.remove();
						for (int j = 0; j < changes[c].length(); j++)
							it.add((byte) changes[c].charAt(j));
					}

				}
			}
		}

		byte[][] code = new byte[256][];

		for (int i = 0; i < 52; i++) {

			int j = 0, charCode = i > 25 ? i + 'A' - 26 : i + 'a';

			code[charCode] = new byte[transf[i].size()];
			ListIterator<Byte> it = transf[i].listIterator();

			while (it.hasNext())
				code[charCode][j++] = it.next();
		}

		MessageDigest md = MessageDigest.getInstance("MD5");

		for (int i = 0; i < queue.length(); i++)
			md.update(code[queue.charAt(i)]);

		byte[] array = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i)
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));

		System.out.println(sb.toString());
	}
}
