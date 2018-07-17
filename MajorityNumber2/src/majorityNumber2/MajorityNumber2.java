package majorityNumber2;

import java.util.ArrayList;
import java.util.List;

// LeetCode #229 (Majority Element II).

// Given an integer array of length L, find all numbers that occur more than 1/3 * L 
// times if any exists.

// Assumption: The given array is not null.

public class MajorityNumber2 {

	// Solution 1: Sort the array, then linear scan.
	// O(n * log(n)) time, O(n) space, because of merge sort (for primitive types).

	// Solution 2: Hash map.
	// O(n) time, O(n) space.

	// Solution 3: Boyer-Moore Majority Vote Algorithm
	public List<Integer> majority(int[] array) {
		List<Integer> result = new ArrayList<>();
		if (array == null || array.length == 0) {
			return result;
		}
		int candidate1 = array[0], count1 = 0;
		int candidate2 = array[0], count2 = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == candidate1) {
				count1++;
			} else if (array[i] == candidate2) {
				count2++;
			} else if (count1 == 0) {
				candidate1 = array[i];
				count1 = 1;
			} else if (count2 == 0) {
				candidate2 = array[i];
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = count2 = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == candidate1) {
				count1++;
			} else if (array[i] == candidate2) {
				count2++;
			}
		}
		if (count1 > array.length / 3) {
			result.add(candidate1);
		}
		if (count2 > array.length / 3) {
			result.add(candidate2);
		}
		return result;
	}

	// Time complexity is O(n).
	// Space complexity is O(1).
}
