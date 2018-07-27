package editDistance;

// LeetCode #72 (Edit Distance).

// Given two strings of alphanumeric characters, determine the minimum number of 
// Replace, Delete, and Insert operations needed to transform one string into the other.

// Assumption: Both strings are not null.

public class EditDistance {

	// recursive
	public int editDistance(String one, String two) {
		if (one.length() == 0) {
			return two.length();
		}
		if (two.length() == 0) {
			return one.length();
		}
		int nothing = Integer.MAX_VALUE;
		if (one.charAt(0) == two.charAt(0)) {
			nothing = editDistance(one.substring(1), two.substring(1));
		}
		int replace = 1 + editDistance(one.substring(1), two.substring(1));
		int delete = 1 + editDistance(one.substring(1), two);
		int insert = 1 + editDistance(one, two.substring(1));
		return min(nothing, replace, delete, insert);
	}

	// Time complexity is O(4^(m+n)).
	// Space complexity is O(m + n).

	// DP
	// M[i][j]: minimum number of operations needed to transfer one in [0, i - 1] to
	// two in [0, j - 1]
	public int editDistance2(String one, String two) {
		if (one.length() == 0) {
			return two.length();
		}
		if (two.length() == 0) {
			return one.length();
		}
		int[][] matrix = new int[one.length() + 1][two.length() + 1];
		for (int i = 0; i < one.length() + 1; i++) {
			for (int j = 0; j < two.length() + 1; j++) {
				if (i == 0) {
					matrix[i][j] = j;
				} else if (j == 0) {
					matrix[i][j] = i;
				} else {
					int nothing = one.charAt(i - 1) == two.charAt(j - 1) ? matrix[i - 1][j - 1] : Integer.MAX_VALUE;
					matrix[i][j] = min(nothing, 1 + matrix[i - 1][j - 1], 1 + matrix[i - 1][j], 1 + matrix[i][j - 1]);
				}
			}
		}
		return matrix[one.length()][two.length()];
	}

	// Time complexity is O(m*n).
	// Space complexity is O(m*n), but obviously can be optimized to O(1).

	private int min(int nothing, int replace, int delete, int insert) {
		return Math.min(nothing, Math.min(replace, Math.min(delete, insert)));
	}
}
