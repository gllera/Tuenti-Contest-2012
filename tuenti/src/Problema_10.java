import java.io.*;
import java.util.*;

public class Problema_10 {

	public static void main(String[] args) throws IOException {

		//FileInputStream a = new FileInputStream("/home/gllera/Desktop/fe.in");

		//Scanner in = new Scanner(a);
		
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			String line = in.nextLine();
			Stack<String> values = new Stack<String>();

			for (String s : line.split(" "))
				if (s.equals("fire"))
					values.pop();
				else if (s.equals("breadandfish"))
					values.add(new String(values.peek()));
				else
					values.add(s);

			values.pop();

			System.out.println(process(values));
		}
	}

	static long process(Stack<String> stack) {

		String s = stack.pop();

		if (s.equals("@"))
			return process(stack) + process(stack);
		else if (s.equals("$"))
			return - process(stack) + process(stack);
		else if (s.equals("#"))
			return process(stack) * process(stack);
		else if (s.equals("mirror"))
			return -process(stack);
		else if (s.equals("&"))
		{
			long t1 = process(stack);
			long t2 = process(stack);
			
			return t2 / t1;
		}
		else if (s.equals("conquer"))
		{
			long t1 = process(stack);
			long t2 = process(stack);
			
			return t2 % t1;
		}
		else if (s.equals("dance"))
		{
			long t1 = process(stack);
			long t2 = process(stack);
			
			stack.add(String.valueOf(t1));
			return t2;
		}
		
		return Long.parseLong(s);
	}
}
