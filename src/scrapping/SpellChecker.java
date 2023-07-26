package scrapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class SpellChecker {
	static Set<String> dic = new HashSet<String> (); 
	
	static void addWords(ArrayList<String> texts) {
		dic.addAll(texts);
	}
	
	static String spellCheckString(String text) throws IOException {
		text = text.toLowerCase();
		ArrayList<String> words = Crawler.extractWordsFromText(text);
		ArrayList<String> correctWords = new ArrayList<String>();
		Boolean needCorrect = false; 
		for (String word: words) {
			if (dic.contains(word)) {
				correctWords.add(word);
				continue;
			}
			needCorrect = true;
			correctWords.add(findNearestString(word));
		}
		
		return needCorrect ? String.join(" ", correctWords) : "";
	}
	
	public static String findNearestString(String word) {
        int minDistance = Integer.MAX_VALUE;
        String nearestString = "";

        for (String str : dic) {
            int distance = calculateLevenshteinDistance(str, word);
            if (distance < minDistance) {
                minDistance = distance;
                nearestString = str;
            }
        }

        return nearestString;
    }
	
	public static int calculateLevenshteinDistance(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }
}
