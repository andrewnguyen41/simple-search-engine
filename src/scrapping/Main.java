package scrapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static HashMap<String, HashMap<String, Integer>> crawl() throws IOException {
		Crawler scraper = new Crawler();
		scraper.crawl();
		return scraper.pagesWordCountMap;
	}
	
	public static void main(String[] args) throws IOException {
		HashMap<String, HashMap<String, Integer>> wordCountHashMap = new HashMap<String, HashMap<String, Integer>>();
		try (Scanner scanner = new Scanner(System.in)) {
			int userInput = 0;
			
			while (userInput != 4) {
				System.out.println("\n1. Crawl \n2. Search \n3. Suggestion \n4. Exit");
			    System.out.print("Enter a number: ");
			    userInput = scanner.nextInt();
			    scanner.nextLine();
			    
			    switch(userInput) {
			    case 1:
			    {
			      wordCountHashMap = crawl();
			      break;
			    }
			    case 2: {
			    	System.out.print("Enter search: ");
			    	String searchText = scanner.nextLine();
			    				    	
			    	// Spell Check
			    	String spellCheckedString = SpellChecker.spellCheckString(searchText);
			    	if (!spellCheckedString.isEmpty()) {
			    		System.out.println("Did you mean: " + spellCheckedString);
			    		System.out.print("y/n: ");
			    		String correctOption = scanner.nextLine();
			    		if (correctOption.equals("y")) {
			    			searchText = spellCheckedString;
			    		}
			    	}
			    	
			    	// Word extraction
					ArrayList<String> searchWords = Preprocessor.extractWords(searchText);
					System.out.println("Preprocessed words: " + searchWords);
					
					HashMap<String, Integer> searchResult = Searcher.search(searchWords, wordCountHashMap);

					Suggester.searchCache.put(searchText.toLowerCase(), searchResult);
					
					int i = 1;
					for (Map.Entry<String, Integer> en : searchResult.entrySet()) {
						System.out.println(i + ".[Score: " + en.getValue() + "] " + en.getKey());
						i++;
			        }
			      break;
			    }
			      
			    case 3:
			    {
			    	System.out.print("Enter a keyword: ");
			    	String keyword = scanner.nextLine();
			        ArrayList<String> suggestions = Suggester.findSuggestions(keyword);
			        Integer i = 1;
			        for (String suggestion : suggestions) {
			        	System.out.println(i + ". " + suggestion);
			        	i++;
			        }
			        System.out.print("Choose suggestion: ");
			    	Integer option = scanner.nextInt();
			    	scanner.nextLine();
			    	String key = suggestions.get(option - 1);
			    	HashMap<String, Integer> suggestionValues = Suggester.searchCache.get(key);
			    	i = 1;
					for (Map.Entry<String, Integer> en : suggestionValues.entrySet()) {
						System.out.println(i + ".[Score: " + en.getValue() + "] " + en.getKey());
						i++;
			        }
			        break;
			    }
			    case 4:
			    	break;
			    default:
			  }
			}
		}
	}
}
