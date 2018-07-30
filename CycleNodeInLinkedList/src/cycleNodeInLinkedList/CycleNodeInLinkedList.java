package cycleNodeInLinkedList;

// LeetCode #142 (Linked List Cycle II).

// Check if a given linked list has a cycle. Return the node where the cycle starts. 
// Return null if there is no cycle.

public class CycleNodeInLinkedList {

	public ListNode cycleNode(ListNode head) {
		ListNode fast = meetingPoint(head);
		if (fast == null) { // no cycle
			return null;
		}
		ListNode slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	private ListNode meetingPoint(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return slow;
			}
		}
		return null;
	}

	// Time complexity is O(n).
	// Space complexity is O(1).
}
