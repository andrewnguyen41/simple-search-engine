package scrapping;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import java.io.FileWriter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class Crawler {
	public HashMap<String, HashMap<String, Integer>> pagesWordCountMap = new HashMap<>();
	
	public void extractWordFrequency(String filename) {
		try {
			File input = new File(filename);
			Document doc = Jsoup.parse(input, "UTF-8", "");

			Elements elements = doc.getAllElements();
			HashMap<String, Integer> wordCountMap = new HashMap<>();

			ArrayList<String> spellWords = new ArrayList<String>();
			
			for (Element element : elements) {
				String text = element.text();
				if (text.length() <= 0) {
					continue;
				}
				
				// Build spell check dictionary
		        spellWords.addAll(extractWordsFromText(text));
		        
				// Preprocess
				ArrayList<String> words = Preprocessor.extractWords(text);
				for (String word: words) {
					wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
				}
			}
			
			pagesWordCountMap.put(filename, wordCountMap);
            SpellChecker.addWords(spellWords);
			System.out.println("Crawl " + filename + "successfully");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> extractWordsFromText(String text) {
		text = text.toLowerCase();
		String regex = "\\b\\w+\\b";
		Pattern pattern = Pattern.compile(regex);
        // Create a Matcher object
        Matcher matcher = pattern.matcher(text);
		ArrayList<String> res = new ArrayList<String>();
        while (matcher.find()) {
            String word = matcher.group();
            res.add(word);
        }
        return res;
	}
	
	public void crawl() throws IOException {
		File directoryPath = new File("./html");
		File filesList[] = directoryPath.listFiles();
		for(File file : filesList) {
			String filePath = file.getAbsolutePath();
			this.extractWordFrequency(filePath);
	    }
		
		Gson gson = new Gson();
		String json = gson.toJson(pagesWordCountMap);
		FileWriter writer = new FileWriter("./json/pagesWordCount.json");
		writer.write(json);
		writer.close();
		
		String spellCheckJson = gson.toJson(SpellChecker.dic);
		FileWriter spellCheckWriter = new FileWriter("./json/spellCheckDictionary.json");
		spellCheckWriter.write(spellCheckJson);
		spellCheckWriter.close();
		
		System.out.println("Crawl Successfully!!");
	}
	
	public static void main(String[] args) throws IOException {
		Crawler scaper = new Crawler();
		scaper.crawl();
	}
}