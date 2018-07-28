package largestSumOfValidNumbers;

import java.util.ArrayList;
import java.util.List;

// Given a 2D array A[8][8] with all integer numbers if we take a number 
// a[i][j], then we cannot take its 8 neighboring cells. 

// How should we take the numbers to make their sum as large as possible?

// Assumption: The given matrix is 8 * 8.

public class LargestSumOfValidNumbers {

	private static int k = 8;

	public int largestSum(int[][] matrix) {
		List<Integer> configs = validConfigs(k);
		int[][] largest = new int[k][configs.size()];
		for (int i = 0; i < k; i++) { // i-th row
			// M[i][j] = max(M[i - 1][k]), for all k
			for (int j = 0; j < configs.size(); j++) { // pick according to the j-th config in this row
				largest[i][j] = Integer.MIN_VALUE;
				if (i == 0) {
					largest[i][j] = sum(matrix[i], configs.get(j));
				} else {
					for (int l = 0; l < configs.size(); l++) {
						if (noConflict(configs.get(j), configs.get(l))) {
							largest[i][j] = Math.max(largest[i][j], largest[i - 1][l] + sum(matrix[i], configs.get(j)));
						}
					}
				}
			}
		}
		// pick the largest in the last row
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < configs.size(); i++) {
			result = Math.max(result, largest[k - 1][i]);
		}
		return result;
	}

	// Gets all possible configurations, each one is represented as an integer and
	// the least significant 8 bits are used. Needs to guarantee that no adjacent
	// bit is chosen in the least significant 8 bits.
	private List<Integer> validConfigs(int k) {
		List<Integer> configs = new ArrayList<Integer>();
		DFS(configs, 0, k, 0);
		return configs;
	}

	private void DFS(List<Integer> configs, int index, int k, int cur) {
		configs.add(cur);
		for (int i = index; i < k; i++) {
			DFS(configs, i + 2, k, cur | (1 << i));
		}
	}

	private int sum(int[] array, int config) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			if (((config >>> i) & 1) != 0) {
				sum += array[i];
			}
		}
		return sum;
	}

	private boolean noConflict(int c1, int c2) {
		return (c1 & c2) == 0 && ((c1 << 1) & c2) == 0 && (c1 & (c2 << 1)) == 0;
	}
}
