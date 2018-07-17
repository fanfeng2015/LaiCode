package majorityNumber;

// LeetCode #169 (Majority Element).

// Given an integer array of length L, find the number that occurs more than 0.5 * L times.

// Assumptions:
// 1. The given array is not null or empty
// 2. It is guaranteed there exists such a majority number

public class MajorityNumber {

	// Solution 1: Sort the array, then return the middle element.
	// O(n * log(n)) time, O(n) space, because of merge sort (for primitive types).

	// Solution 2: Hash map.
	// O(n) time, O(n) space.

	// Solution 3: Boyer-Moore Majority Vote Algorithm
	public int majority(int[] array) {
		int element = array[0];
		int count = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == element) {
				count++;
			} else if (count > 0) {
				count--;
			} else {
				element = array[i];
				count = 1;
			}
		}
		return element;
	}

	// Time complexity is O(n).
	// Space complexity is O(1).
}
