package bipartiteGraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// LeetCode #785 (Is Graph Bipartite?).

// Determine if an undirected graph is bipartite. A bipartite graph is one in which
// the nodes can be divided into two groups such that no nodes have direct edges to
// other nodes in the same group.

public class BipartiteGraph {

	// BFS (with queue)
	public boolean isBipartite(List<GraphNode> graph) {
		Map<GraphNode, Integer> visited = new HashMap<>();
		for (GraphNode node : graph) {
			if (!validate(node, visited)) {
				return false;
			}
		}
		return true;
	}

	public boolean validate(GraphNode node, Map<GraphNode, Integer> visited) {
		if (visited.containsKey(node)) {
			return true;
		}
		visited.put(node, 0);
		LinkedList<GraphNode> queue = new LinkedList<>();
		queue.offerLast(node);
		while (!queue.isEmpty()) {
			GraphNode cur = queue.pollFirst();
			for (GraphNode neighbor : cur.neighbors) {
				if (!visited.containsKey(neighbor)) {
					queue.offerLast(neighbor);
					visited.put(neighbor, 1 - visited.get(cur));
				} 
				else if (visited.get(neighbor) != 1 - visited.get(cur)) {
					return false;
				}
			}
		}
		return true;
	}

	// Time complexity is O(m + n).
	// Space complexity is O(n).
	
	// DFS solution is possible with a different problem definition, see discussions 
	// about LeetCode #785 for more details.
}
