package reconstructBSTWithPostorder;

// Given the post-order traversal sequence of a binary search tree, reconstruct 
// the original tree.

// Assumption:
// 1. The given sequence is not null.
// 2. There are no duplicate keys in the binary search tree.

public class ReconstructBSTWithPostorder {

	private int index = 0;

	public TreeNode reconstruct(int[] post) {
		index = post.length - 1;
		return helper(post, Integer.MIN_VALUE);
	}

	private TreeNode helper(int[] post, int min) {
		if (index < 0 || post[index] < min) {
			return null;
		}
		TreeNode root = new TreeNode(post[index--]);
		root.right = helper(post, root.key);
		root.left = helper(post, min);
		return root;
	}

	// Time complexity is O(n).
	// Space complexity is O(n).
}
