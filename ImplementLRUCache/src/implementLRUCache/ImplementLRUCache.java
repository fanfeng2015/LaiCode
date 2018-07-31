package implementLRUCache;

import java.util.HashMap;
import java.util.Map;

// LeetCode #146 (LRU Cache).

// Implement a least recently used cache. It should provide set(), get() operations.

// If not exists, return null.

public class ImplementLRUCache<K, V> {

	private static class Node<K, V> {
		Node<K, V> prev;
		Node<K, V> next;
		K key;
		V value;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		void update(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private final int CAPACITY;
	private Node<K, V> head;
	private Node<K, V> tail;
	private Map<K, Node<K, V>> map;

	public ImplementLRUCache(int capacity) {
		this.CAPACITY = capacity;
		this.map = new HashMap<K, Node<K, V>>();
	}

	public V get(K key) {
		Node<K, V> node = map.get(key);
		if (node == null) {
			return null;
		}
		remove(node);
		append(node);
		return node.value;
	}

	public void set(K key, V value) {
		Node<K, V> node = null;
		if (map.containsKey(key)) {
			node = map.get(key);
			node.value = value;
			remove(node);
		} else if (map.size() < CAPACITY) {
			node = new Node<K, V>(key, value);
		} else {
			node = tail;
			remove(node);
			node.update(key, value);
		}
		append(node);
	}

	// removes node from doubly linked list
	private Node<K, V> remove(Node<K, V> node) {
		map.remove(node.key);
		// remove node
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		// might need to update head or tail
		if (node == head) {
			head = head.next;
		}
		if (node == tail) {
			tail = tail.prev;
		}
		node.next = node.prev = null;
		return node;
	}

	// appends node at the beginning of doubly linked list
	private Node<K, V> append(Node<K, V> node) {
		map.put(node.key, node);
		if (head == null) {
			head = tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		return node;
	}

}
