package twoSubsetsWithMinDifference;

import java.util.ArrayList;
import java.util.List;

// Given a set of n integers, divide the set in two subsets (a: of size n/2 each, 
// b: not necessarily of size n/2 each) such that the difference of the sum of two
// subsets is as minimum as possible.

// Return the minimum difference (absolute value).

// Assumption: The given integer array is not null and it has length of >= 2.

public class TwoSubsetsWithMinDifference {

	private int min = Integer.MAX_VALUE, cur = 0, sum = 0;

	// DFS
	public int minDifference(int[] array) {
		sum = sum(array);
		List<Integer> list = new ArrayList<>();
		DFS2(array, 0, list);
		return min;
	}

	private int sum(int[] array) {
		int sum = 0;
		for (int i : array) {
			sum += i;
		}
		return sum;
	}

	// Assumption: The two subsets don't need to be of about equal size.
	private void DFS(int[] array, int level, List<Integer> list) {
		if (level == array.length) {
			if (Math.abs(sum - 2 * cur) < min) {
				min = Math.abs(sum - 2 * cur);
			}
			return;
		}
		cur += array[level];
		list.add(array[level]);
		DFS(array, level + 1, list);
		cur -= array[level];
		list.remove(list.size() - 1);
		DFS(array, level + 1, list);
	}

	// Assumption: The two subsets need to be of about equal size.
	private void DFS2(int[] array, int level, List<Integer> list) {
		if (level == array.length) {
			if ((array.length % 2 == 0 && list.size() == array.length / 2) || (array.length % 2 == 1
					&& (list.size() == array.length / 2 || list.size() == array.length / 2 + 1))) {
				if (Math.abs(sum - 2 * cur) < min) {
					min = Math.abs(sum - 2 * cur);
				}
			}
			return;
		}
		cur += array[level];
		list.add(array[level]);
		DFS2(array, level + 1, list);
		cur -= array[level];
		list.remove(list.size() - 1);
		DFS2(array, level + 1, list);
	}

	// Time complexity is O(2^n).
	// Space complexity is O(n).

	// DP
	// Assumptions:
	// 1. All elements are non-negative.
	// 2. The two subsets don't need to be of about equal size.

	// M[i][j]: whether elements in array [0, i] can sum to j
	public int minDifference2(int[] array) {
		int sum = sum(array);
		boolean[][] matrix = new boolean[array.length][sum + 1];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j <= sum; j++) {
				if (i == 0) {
					matrix[i][j] = (j == 0) || (array[i] == j);
				} else {
					matrix[i][j] = matrix[i - 1][j] || (j - array[i] >= 0 && matrix[i - 1][j - array[i]]);
				}
			}
		}
		// post processing
		int offset = 0, index = sum / 2;
		while (!matrix[array.length - 1][index + offset] && !matrix[array.length - 1][index - offset]) {
			offset++;
		}
		return Math.min(
				(matrix[array.length - 1][index - offset]) ? Integer.MAX_VALUE : Math.abs(sum - 2 * (index - offset)),
				(matrix[array.length - 1][index + offset]) ? Integer.MAX_VALUE : Math.abs(sum - 2 * (index + offset)));
	}

	// Time complexity is O(n*n*k), where [0, k] is range of elements.
	// Space complexity is O(n*n*k).
}
