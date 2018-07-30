package interleavePositiveAndNegativeElements;

// Given an array with both positive and negative numbers in random order. Shuffle the
// array so that positive and negative numbers are put in position with even and odd indices,
// respectively.

// If there are more positive/negative numbers, put them at the end of the array. The ordering
// of positive/negative numbers does not matter.

// Assumption:
// 1. The given array is not null.
// 2. There is no 0 in the array.

public class InterleavePositiveAndNegativeElements {

	public int[] interleave(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		// rearrange all negative elements to be before all positive elements
		int left = 0, right = array.length - 1;
		while (left < right) {
			if (array[left] < 0) {
				left++;
			} else if (array[right] > 0) {
				right--;
			} else {
				swap(array, left++, right--);
			}
		}
		left = 0;
		right = (array[right] < 0) ? right + 1 : right;
		while (left < right && right < array.length && array[left] < 0) {
			swap(array, left, right);
			left += 2;
			right += 1;
		}
		return array;
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// Time complexity is O(n).
	// Space complexity is O(1).
}
