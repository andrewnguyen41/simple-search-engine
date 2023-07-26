package scrapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Suggester {
	public static HashMap<String, HashMap<String, Integer>> searchCache = new HashMap<String, HashMap<String, Integer>>();

	public static ArrayList<String> findSuggestions(String search) {
		ArrayList<String> result = new ArrayList<String>();
		for (Entry<String, HashMap<String, Integer>> set : searchCache.entrySet()) {
			String key = set.getKey();
			if (key.startsWith(search)) {
				result.add(key);
			}
		}
		return result;
	}
}
