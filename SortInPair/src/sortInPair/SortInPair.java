package sortInPair;

import java.util.Arrays;

// LeetCode #280 (Wiggle Sort).

// Given an array A with integers, sort the array such that  
// A[0] < A[1] > A[2] < A[3] > A[4] < A[5] > …

// Assumptions:
// 1. A is guaranteed to be not null.
// 2. There are no duplicate elements in A.

public class SortInPair {

	public void sortInPair(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}
		Arrays.sort(array);
		int left = 1, right = 2;
		while (right < array.length) {
			swap(array, left, right);
			left += 2;
			right += 2;
		}
	}

	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	// Time complexity is O(n*log(n)), because of quick sort (for primitive types)..
	// Space complexity is O(log(n)).

	// one pass linear time
	public void sortInPair2(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}
		for (int i = 0; i < array.length - 1; i++) {
			if ((i % 2 == 0 && array[i] > array[i + 1]) || (i % 2 == 1 && array[i] < array[i + 1])) {
				swap(array, i, i + 1);
			}
		}
	}

	// Time complexity is O(n).
	// Space complexity is O(1).
}
