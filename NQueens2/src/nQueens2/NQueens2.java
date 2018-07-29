package nQueens2;

// LeetCode #52 (N-Queens II).

// Follow up for N-Queens problem. Now, instead outputting board configurations, 
// return the total number of distinct solutions.

public class NQueens2 {

	private int count = 0;

	public int totalNQueens(int n) {
		boolean[] usedColumns = new boolean[n];
		boolean[] usedDiagonals = new boolean[2 * n - 1];
		boolean[] usedRevDiagonals = new boolean[2 * n - 1];
		helper(n, 0, usedColumns, usedDiagonals, usedRevDiagonals);
		return count;
	}

	private void helper(int n, int row, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		if (row == n) {
			count++;
			return;
		}
		for (int col = 0; col < n; col++) {
			if (!usedColumns[col] && !usedDiagonals[row + col] && !usedRevDiagonals[row - col + n - 1]) {
				flip(n, row, col, usedColumns, usedDiagonals, usedRevDiagonals);
				helper(n, row + 1, usedColumns, usedDiagonals, usedRevDiagonals);
				flip(n, row, col, usedColumns, usedDiagonals, usedRevDiagonals);
			}
		}
	}

	private void flip(int n, int row, int col, boolean[] usedColumns, boolean[] usedDiagonals,
			boolean[] usedRevDiagonals) {
		usedColumns[col] = !usedColumns[col];
		usedDiagonals[row + col] = !usedDiagonals[row + col];
		usedRevDiagonals[row - col + n - 1] = !usedRevDiagonals[row - col + n - 1];
	}

	// Time complexity is O(n*n!), this is an upper bound, which is not tight.
	// Space complexity is O(n).
}
