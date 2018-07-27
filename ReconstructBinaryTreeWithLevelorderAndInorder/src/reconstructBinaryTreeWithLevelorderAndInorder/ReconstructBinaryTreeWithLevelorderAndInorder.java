package reconstructBinaryTreeWithLevelorderAndInorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Given the level-order and in-order traversal sequence of a binary tree, reconstruct
// the original tree.

// Assumption:
// 1. The given sequences are not null and they have the same length.
// 2. There are no duplicate keys in the binary tree.

public class ReconstructBinaryTreeWithLevelorderAndInorder {

	public TreeNode reconstruct(int[] in, int[] level) {
		Map<Integer, Integer> map = indexMap(in);
		List<Integer> list = new ArrayList<>();
		for (int i : level) {
			list.add(i);
		}
		return helper(map, list);
	}

	private Map<Integer, Integer> indexMap(int[] in) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return map;
	}

	private TreeNode helper(Map<Integer, Integer> map, List<Integer> list) {
		if (list.isEmpty()) {
			return null;
		}
		TreeNode root = new TreeNode(list.get(0));
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		for (int i : list) {
			if (map.get(i) < map.get(root.key)) {
				left.add(i);
			} else if (map.get(i) > map.get(root.key)) {
				right.add(i);
			}
		}
		root.left = helper(map, left);
		root.right = helper(map, right);
		return root;
	}

	// Time complexity is O(n^2) in the worst case, but O(n*log(n)) on average.
	// Space complexity is O(n^2) in the worst case, but O(n*log(n)) on average, or
	// O(n) if garbage collection is ideal, although not possible in practice.
}
