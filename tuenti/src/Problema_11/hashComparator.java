package Problema_11;

import java.util.Comparator;
import java.util.AbstractMap.SimpleEntry;

public class hashComparator implements Comparator<SimpleEntry<Integer, Integer>> {
		public int compare(SimpleEntry<Integer, Integer> e1, SimpleEntry<Integer, Integer> e2) {
			if (e1.getKey() < e2.getKey())
				return 1;
			else if (e1.getKey() > e2.getKey())
				return -1;
			
			return 0;
		}
}
