package sortInSpecifiedOrder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Given two integer arrays A1 and A2, sort A1 in such a way that
// the relative order among the elements will be same as those are in A2.

// For the elements that are not in A2, append them in the right end of the A1
// in an ascending order.

// Assumptions:
// 1. A1 and A2 are both not null.
// 2. There are no duplicate elements in A2.

public class SortInSpecifiedOrder {

	private static class MyComparator implements Comparator<Integer> {

		private Map<Integer, Integer> map;

		public MyComparator(int[] array) {
			this.map = new HashMap<>();
			for (int i = 0; i < array.length; i++) {
				map.put(array[i], i);
			}
		}

		@Override
		public int compare(Integer o1, Integer o2) {
			Integer index1 = map.get(o1);
			Integer index2 = map.get(o2);
			if (index1 != null && index2 != null) {
				return index1.compareTo(index2);
			} else if (index1 == null && index2 == null) {
				return o1.compareTo(o2);
			}
			return index1 != null ? -1 : 1;
		}

	}

	public int[] sortSpecial(int[] A1, int[] A2) {
		Integer[] temp = new Integer[A1.length];
		for (int i = 0; i < A1.length; i++) {
			temp[i] = A1[i];
		}
		Arrays.sort(temp, new MyComparator(A2));
		for (int i = 0; i < temp.length; i++) {
			A1[i] = temp[i];
		}
		return A1;
	}

	// Time complexity is O(m*log(m) + n).
	// Space complexity is O(m + n), because of merge sort (for non-primitive
	// types).
}
