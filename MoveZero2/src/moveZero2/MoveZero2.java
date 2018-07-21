package moveZero2;

import java.util.Arrays;

// LeetCode #283 (Move Zeroes).

// Given an array of integers, move all the 0s to the right end of the array.

// The relative order of the elements in the original array need to be maintained.

public class MoveZero2 {

	public int[] moveZero(int[] array) {
		int left = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				array[left++] = array[i];
			}
		}
		Arrays.fill(array, left, array.length, 0);
		return array;
	}

	// Time complexity is O(n).
	// Space complexity is O(1).
}
