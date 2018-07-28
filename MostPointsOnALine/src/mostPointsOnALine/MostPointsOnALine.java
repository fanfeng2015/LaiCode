package mostPointsOnALine;

import java.util.HashMap;
import java.util.Map;

// LeetCode #149 (Max Points on a Line).

// Given an array of 2D coordinates of points (all the coordinates are integers), 
// find the largest number of points that can be crossed by a single line in 2D space. 

// Assumption: The given array is not null and it has at least 2 points

public class MostPointsOnALine {

	public int most(Point[] points) {
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			Point cur = points[i];
			int same = 1; // same point as cur
			int sameX = 0; // vertical line
			int differentX = 0; // non-vertical line
			Map<Double, Integer> countMap = new HashMap<>();
			for (int j = 0; j < points.length; j++) {
				if (i == j) {
					continue;
				}
				Point next = points[j];
				if (next.x == cur.x && next.y == cur.y) {
					same++;
				} else if (next.x == cur.x) {
					sameX++;
				} else {
					// can have problem with precision: use BigDecimal
					double slope = ((double) (next.y - cur.y)) / ((double) (next.x - cur.x));
					Integer count = countMap.get(slope);
					countMap.put(slope, (count == null) ? 1 : count + 1);
					differentX = Math.max(differentX, countMap.get(slope));
				}
			}
			differentX = Math.max(differentX, sameX) + same;
			result = Math.max(result, differentX);
		}
		return result;
	}

	// Time complexity is O(n^2).
	// Space complexity is O(n^2).
}
