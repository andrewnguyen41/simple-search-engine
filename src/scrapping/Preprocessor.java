package scrapping;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class Preprocessor {
	static ArrayList<String> extractWords(String text) throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		
		if (text.length() <= 0) {
			return result;
		}

//        System.out.println("before: " + text);
		//EnglishPossessiveFilter, LowerCaseFilter, StopFilter, PorterStemFilter
        Analyzer analyzer = new EnglishAnalyzer(Version.LUCENE_36);
		TokenStream tokenStream = analyzer.tokenStream("", new StringReader(text));
		tokenStream.reset();

//        ArrayList<String> list = new ArrayList();
		while (tokenStream.incrementToken()) {
			String word = tokenStream.getAttribute(CharTermAttribute.class).toString();
			if (word.length() > 0) {
//				wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
//				list.add(word);
				result.add(word);
			}
		}
		
		return result;
	}
}
