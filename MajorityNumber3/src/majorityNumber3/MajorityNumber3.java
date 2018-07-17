package majorityNumber3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.

// Assumption:
// 1. The given array is not null or empty.
// 2. K >= 2.

public class MajorityNumber3 {

	// Solution 1: Sort the array
	// O(n * log(n)) time, O(n) space, because of quick sort (for primitive types).

	// Solution 2: Hash map.
	// O(n) time, O(n) space.

	// Solution 3: Boyer-Moore Majority Vote Algorithm
	public List<Integer> majority(int[] array, int k) {
		List<Integer> result = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				map.put(array[i], map.get(array[i]) + 1);
			} else if (map.size() < k) {
				map.put(array[i], 1);
			} else { // Use iterator to avoid ConcurrentModificationException
				Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
					int count = entry.getValue();
					if (count == 1) {
						it.remove();
					} else {
						entry.setValue(count - 1);
					}
				}
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			map.put(entry.getKey(), 0);
		}
		for (int i = 0; i < array.length; i++) {
			Integer count = map.get(array[i]);
			if (count != null) {
				map.put(array[i], count + 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > array.length / k) {
				result.add(entry.getKey());
			}
		}
		return result;
	}

	// Time complexity is O(n * k).
	// Space complexity is O(k);
}
