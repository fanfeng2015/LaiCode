package heightOfBinaryTree;

// LeetCode #104 (Maximum Depth of Binary Tree).

// Find the height of binary tree.

public class HeightOfBinaryTree {

	public int height(TreeNode root) {
		// base case
		if (root == null) {
			return 0;
		}
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	// Time complexity is O(n).
	// Space complexity is O(n), when the binary tree is highly unbalanced.
}
