package reconstructBinaryTreeWithPostorderAndInorder;

import java.util.HashMap;
import java.util.Map;

// LeetCode #106 (Construct Binary Tree from Inorder and Postorder Traversal).

// Given the post-order and in-order traversal sequence of a binary tree, reconstruct the original tree.

// Assumption:
// 1. The given sequences are not null and they have the same length.
// 2. There are no duplicate keys in the binary tree.

public class ReconstructBinaryTreeWithPostorderAndInorder {

	public TreeNode reconstruct(int[] in, int[] post) {
		Map<Integer, Integer> map = indexMap(in);
		return helper(post, map, 0, in.length - 1, 0, post.length - 1);
	}

	private Map<Integer, Integer> indexMap(int[] in) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return map;
	}

	private TreeNode helper(int[] post, Map<Integer, Integer> map, int inLeft, int inRight, int postLeft,
			int postRight) {
		if (inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(post[postRight]);
		int inMid = map.get(root.key);
		root.left = helper(post, map, inLeft, inMid - 1, postLeft, postLeft + inMid - inLeft - 1);
		root.right = helper(post, map, inMid + 1, inRight, postLeft + inMid - inLeft, postRight - 1);
		return root;
	}

	// Time complexity is O(n).
	// Space complexity is O(n), when binary tree is highly unbalanced.
}
