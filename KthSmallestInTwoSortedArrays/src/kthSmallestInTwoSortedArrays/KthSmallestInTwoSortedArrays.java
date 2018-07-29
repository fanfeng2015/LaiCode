package kthSmallestInTwoSortedArrays;

// Given two sorted arrays of integers, find the Kth smallest number.

// Assumptions:
// 1. The two given arrays are not null and at least one of them is not empty.
// 2. K >= 1, K <= total lengths of the two sorted arrays.

public class KthSmallestInTwoSortedArrays {

	// Solution 1: O(n) time complexity with two pointers.

	// Solution 2: Binary search
	public int kth(int[] a, int[] b, int k) {
		return kth(a, 0, b, 0, k);
	}

	private int kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
		if (aLeft >= a.length) {
			return b[bLeft + k - 1];
		}
		if (bLeft >= b.length) {
			return a[aLeft + k - 1];
		}
		if (k == 1) {
			return Math.min(a[aLeft], b[bLeft]);
		}
		// compare the k/2-th element in subarray of a, and k/2-th element in subarray
		// of b
		int aMid = aLeft + k / 2 - 1;
		int bMid = bLeft + k / 2 - 1;
		int aVal = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
		int bVal = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];
		if (aVal <= bVal) {
			return kth(a, aMid + 1, b, bLeft, k - k / 2);
		} else {
			return kth(a, aLeft, b, bMid + 1, k - k / 2);
		}
	}

	// Time complexity is O(log(k)).
	// Space complexity is O(log(k)), because of call-stack.
}
