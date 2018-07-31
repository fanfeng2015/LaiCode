package minimumCutsForPalindromes;

// LeetCode #132 (Palindrome Partitioning II).

// Given a string, a partitioning of the string is a palindrome partitioning if every
// substring of the partition is a palindrome. 

// Determine the fewest cuts needed for a palindrome partitioning of a given string.

// Assumption: The given string is not null.

public class MinimumCutsForPalindromes {

	// isPalindrome[i][j]: whether substring in [i, j] is a palindrome
	// minCuts[i]: min cuts needed to palindrome partition substring in [0, i]
	public int minCuts(String input) {
		if (input.length() == 0) {
			return 0;
		}
		int n = input.length();
		boolean[][] isPalindrome = new boolean[n][n];
		int[] minCuts = new int[n];
		for (int i = 1; i < n; i++) {
			minCuts[i] = i;
			for (int j = 0; j <= i; j++) {
				if (input.charAt(j) == input.charAt(i) && (i - j < 2 || isPalindrome[j + 1][i - 1])) {
					isPalindrome[j][i] = true;
					minCuts[i] = (j == 0) ? 0 : Math.min(minCuts[i], minCuts[j - 1] + 1);
				}
			}
		}
		return minCuts[n - 1];
	}

	// Time complexity is O(n^2).
	// Space complexity is O(n^2).
}
