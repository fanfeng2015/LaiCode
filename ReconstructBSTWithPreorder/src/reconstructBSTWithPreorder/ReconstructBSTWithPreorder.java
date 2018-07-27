package reconstructBSTWithPreorder;

// Given the pre-order traversal sequence of a binary search tree, reconstruct the original tree.

// Assumption:
// 1. The given sequence is not null.
// 2. There are no duplicate keys in the binary search tree.

public class ReconstructBSTWithPreorder {

	private int index = 0;

	public TreeNode reconstruct(int[] pre) {
		return helper(pre, Integer.MAX_VALUE);
	}

	private TreeNode helper(int[] pre, int max) {
		if (index >= pre.length || pre[index] > max) {
			return null;
		}
		TreeNode root = new TreeNode(pre[index++]);
		root.left = helper(pre, root.key);
		root.right = helper(pre, max);
		return root;
	}

	// Time complexity is O(n).
	// Space complexity is O(n).
}
