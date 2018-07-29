package nQueens;

import java.util.ArrayList;
import java.util.List;

// LeetCode #51 (N-Queens).

// Get all valid ways of putting N Queens on an N * N chess board so that no two 
// queens threaten each other.

public class NQueens {

	public List<List<Integer>> nqueens(int n) {
		List<Integer> cur = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		helper(n, cur, result);
		return result;
	}

	private void helper(int n, List<Integer> cur, List<List<Integer>> result) {
		if (cur.size() == n) {
			result.add(new ArrayList<Integer>(cur));
		}
		for (int i = 0; i < n; i++) {
			if (valid(cur, i)) {
				cur.add(i);
				helper(n, cur, result);
				cur.remove(cur.size() - 1);
			}
		}
	}

	private boolean valid(List<Integer> cur, int col) {
		int row = cur.size();
		for (int i = 0; i < row; i++) {
			if (cur.get(i) == col || Math.abs(cur.get(i) - col) == row - i) {
				return false;
			}
		}
		return true;
	}

	// Time complexity is O(n*n!*n), this is an upper bound, which is not tight.
	// Space complexity is O(n).

	// another solution that validates queen's position in O(1) time
	public List<List<Integer>> nqueens2(int n) {
		int[] cur = new int[n];
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		boolean[] usedColumns = new boolean[n]; // columns with a queen
		boolean[] usedDiagonals = new boolean[2 * n - 1]; // diagonals with a queen
		boolean[] usedRevDiagonals = new boolean[2 * n - 1]; // reverse diagonals with a queen
		helper2(n, 0, cur, result, usedColumns, usedDiagonals, usedRevDiagonals);
		return result;
	}

	private void helper2(int n, int row, int[] cur, List<List<Integer>> result, boolean[] usedColumns,
			boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		if (row == n) {
			result.add(toList(cur));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (valid2(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals)) {
				cur[row] = i;
				flip(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals);
				helper2(n, row + 1, cur, result, usedColumns, usedDiagonals, usedRevDiagonals);
				flip(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals);
			}
		}
	}

	private List<Integer> toList(int[] array) {
		List<Integer> list = new ArrayList<>();
		for (int i : array) {
			list.add(i);
		}
		return list;
	}

	private boolean valid2(int n, int row, int col, boolean[] usedColumns, boolean[] usedDiagonals,
			boolean[] usedRevDiagonals) {
		return !usedColumns[col] && !usedDiagonals[row + col] && !usedRevDiagonals[col - row + n - 1];
	}

	private void flip(int n, int row, int col, boolean[] usedColumns, boolean[] usedDiagonals,
			boolean[] usedRevDiagonals) {
		usedColumns[col] = !usedColumns[col];
		usedDiagonals[row + col] = !usedDiagonals[row + col];
		usedRevDiagonals[col - row + n - 1] = !usedRevDiagonals[col - row + n - 1];
	}

	// Time complexity is O(n*n!), this is an upper bound, which is not tight.
	// Space complexity is O(n).
}
