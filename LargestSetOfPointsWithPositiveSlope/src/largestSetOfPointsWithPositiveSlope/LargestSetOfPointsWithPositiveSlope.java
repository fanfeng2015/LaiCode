package largestSetOfPointsWithPositiveSlope;

import java.util.Arrays;
import java.util.Comparator;

// Given an array of 2D coordinates of points (all the coordinates are integers), find
// the largest number of points that can form a set such that any pair of points in the
// set can form a line with positive slope. Return the size of such maximal set.

public class LargestSetOfPointsWithPositiveSlope {

	public int largest(Point[] points) {
		if (points == null || points.length <= 1) {
			return 0;
		}
		// sort by x coordinate
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if (p1.x == p2.x) {
					return 0;
				}
				return p1.x < p2.x ? -1 : 1;
			}
		});
		// run longest ascending subsequence on y coordinate
		int result = 1;
		int[] longest = new int[points.length + 1];
		longest[1] = points[0].y;
		for (int i = 0; i < points.length; i++) {
			int index = find(longest, 1, result, points[i].y);
			if (index == result) {
				longest[++result] = points[i].y;
			} else {
				longest[index + 1] = points[i].y;
			}
		}
		return result == 1 ? 0 : result;
	}

	// find largest smaller value
	private int find(int[] longest, int left, int right, int target) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (longest[mid] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	// Time complexity is O(n*log(n)).
	// Space complexity is O(n).
}
