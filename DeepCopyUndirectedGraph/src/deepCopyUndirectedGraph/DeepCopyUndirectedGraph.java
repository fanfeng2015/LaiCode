package deepCopyUndirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// LeetCode #133 (Clone Graph).

// Make a deep copy of an undirected graph, there could be cycles in the original graph.

public class DeepCopyUndirectedGraph {

	// DFS
	public List<GraphNode> copy(List<GraphNode> graph) {
		if (graph == null) {
			return null;
		}
		HashMap<GraphNode, GraphNode> map = new HashMap<>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				map.put(node, new GraphNode(node.key));
				dfs(node, map);
			}
		}
		return new ArrayList<GraphNode>(map.values());
	}

	private void dfs(GraphNode node, HashMap<GraphNode, GraphNode> map) {
		GraphNode copy = map.get(node);
		for (GraphNode neighbor : node.neighbors) {
			if (!map.containsKey(neighbor)) {
				map.put(neighbor, new GraphNode(neighbor.key));
				dfs(neighbor, map);
			}
			copy.neighbors.add(map.get(neighbor));
		}
	}

	// Time complexity is O(|V|+|E|).
	// Space complexity is O(|V|+|E|), because of call-stack.
	
	// BFS
	public List<GraphNode> copy2(List<GraphNode> graph) {
		if (graph == null) {
			return null;
		}
		Queue<GraphNode> queue = new LinkedList<>();
		HashMap<GraphNode, GraphNode> map = new HashMap<>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				queue.offer(node);
				map.put(node, new GraphNode(node.key));
			}
			while (!queue.isEmpty()) {
				GraphNode cur = queue.poll();
				GraphNode copy = map.get(cur);
				for (GraphNode neighbor : cur.neighbors) {
					if (!map.containsKey(neighbor)) {
						queue.offer(neighbor);
						map.put(neighbor, new GraphNode(neighbor.key));
					}
					copy.neighbors.add(map.get(neighbor));
				}
			}
		}
		return new ArrayList<GraphNode>(map.values());
	}
	
	// Time complexity is O(|V|+|E|).
	// Space complexity is O(|V|+|E|).
}

