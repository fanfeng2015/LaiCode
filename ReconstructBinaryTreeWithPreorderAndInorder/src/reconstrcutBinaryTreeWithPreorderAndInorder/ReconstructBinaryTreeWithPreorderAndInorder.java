package reconstrcutBinaryTreeWithPreorderAndInorder;

import java.util.HashMap;
import java.util.Map;

// LeetCode #105 (Construct Binary Tree from Preorder and Inorder Traversal).

// Given the pre-order and in-order traversal sequence of a binary tree, reconstruct
// the original tree.

// Assumption:
// 1. The given sequences are not null and they have the same length.
// 2. There are no duplicate keys in the binary tree.

public class ReconstructBinaryTreeWithPreorderAndInorder {

	public TreeNode reconstruct(int[] in, int[] pre) {
		Map<Integer, Integer> map = mapKeyToIndex(in);
		return helper(pre, map, 0, in.length - 1, 0, pre.length - 1);
	}

	private Map<Integer, Integer> mapKeyToIndex(int[] in) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return map;
	}

	private TreeNode helper(int[] pre, Map<Integer, Integer> map, int inLeft, int inRight, int preLeft, int preRight) {
		if (inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preLeft]);
		int inMid = map.get(root.key);
		root.left = helper(pre, map, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
		root.right = helper(pre, map, inMid + 1, inRight, preLeft + inMid - inLeft + 1, preRight);
		return root;
	}

	// Time complexity is O(n).
	// Space complexity is O(n), when binary tree is highly unbalanced.
}
