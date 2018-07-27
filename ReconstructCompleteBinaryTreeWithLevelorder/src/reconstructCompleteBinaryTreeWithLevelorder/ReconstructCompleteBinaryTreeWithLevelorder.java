package reconstructCompleteBinaryTreeWithLevelorder;

import java.util.ArrayList;
import java.util.List;

// How to reconstruct a complete binary tree from its level-order traversal sequence only.

// Assumption: The given level-order is not null.

public class ReconstructCompleteBinaryTreeWithLevelorder {

	public TreeNode construct(int[] level) {
		int n = level.length;
		List<TreeNode> list = new ArrayList<>();
		for (int i : level) {
			list.add(new TreeNode(i));
		}
		for (int i = 0; i < n / 2; i++) {
			TreeNode cur = list.get(i);
			cur.left = list.get(2 * i + 1);
			cur.right = (2 * i + 2 < n) ? list.get(2 * i + 2) : null;
		}
		return level.length == 0 ? null : list.get(0);
	}

	// Time complexity is O(n).
	// Space complexity is O(1).
}
