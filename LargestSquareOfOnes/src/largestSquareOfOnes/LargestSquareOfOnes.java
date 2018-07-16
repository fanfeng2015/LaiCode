package largestSquareOfOnes;

// LeetCode #221 (Maximal Square).

// Determine the largest square of 1s in a binary matrix (a binary matrix only contains
// 0 and 1), return the length of the largest square.

// Assumption: The given matrix is not null and guaranteed to be of size N * N, N >= 0.

public class LargestSquareOfOnes {

	// M[i][j]: length of the largest square from position (0, 0) to (i, j),
	// inclusive, of the matrix, including the (i, j) element of the matrix
	public int largest(int[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int max = 0;
		int[][] result = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i == 0 || j == 0) {
					result[i][j] = matrix[i][j];
				} else if (matrix[i][j] == 1) {
					result[i][j] = min(result[i - 1][j - 1], result[i - 1][j], result[i][j - 1]) + 1;
				}
				max = Math.max(max, result[i][j]);
			}
		}
		return max;
	}

	private int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}

	// Time complexity is O(n^2).
	// Space complexity is O(n^2).
}
