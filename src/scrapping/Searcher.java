package scrapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.Comparator;

public class Searcher {
	static HashMap<String, Integer> search(ArrayList<String> terms, HashMap<String, HashMap<String, Integer>> pagesWordCount) {
		HashMap<String, Integer> result = new HashMap<String, Integer>(); 
		
		for (Entry<String, HashMap<String, Integer>> set :
			pagesWordCount.entrySet()) {
			
			int pageScore = 0;
			HashMap<String, Integer> wordCounts = set.getValue();
			
			for (Entry<String, Integer> s : wordCounts.entrySet() ) {
			   if (terms.contains(s.getKey())) {
				   pageScore += s.getValue();
			   }
			}
			
			if (pageScore > 0) {
				result.put(set.getKey(), pageScore);
			}
			
//			for (String word: wordCounts.keys()) {
//				
//			}
//           // Printing all elements of a Map
//           System.out.println(set.getKey() + " = "
//                              + set.getValue());
       }
		
		HashMap<String, Integer> sortedMap = sortByValue(result);
		return sortedMap;
	}
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
            	return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        
        if (list.size() > 10) {
            list = list.subList(0, 10);
        }
         
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
