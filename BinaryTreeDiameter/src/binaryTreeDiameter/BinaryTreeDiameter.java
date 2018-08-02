package binaryTreeDiameter;

// LeetCode #543 (Diameter of Binary Tree).

// Note the different definition of diameter between LaiCode and LeetCode.

// Basically, when the path is required to be from leaf to leaf, max should only be updated 
// when root has both left child and right child. 

// Given a binary tree in which each node contains an integer number. 

// The diameter is defined as the longest distance from one leaf node to another leaf node. 
// The distance is the number of nodes on the path.
// If there does not exist any such paths, return 0.

public class BinaryTreeDiameter {

	private int max = 0;

	public int diameter(TreeNode root) {
		helper(root);
		return max;
	}

	private int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left);
		int right = helper(root.right);
		if (root.left != null && root.right != null) { // update max only if root has both left child and right child
			max = Math.max(max, left + right + 1);
		}
		return Math.max(left, right) + 1;
	}

	// Time complexity is O(n).
	// Space complexity is O(n), because of call-stack, if the binary tree is highly unbalanced.
}
