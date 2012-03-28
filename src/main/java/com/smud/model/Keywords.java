package com.smud.model;

import java.util.ArrayList;
import java.util.List;

public class Keywords {

	private static final String KEYWORD_SEPARATOR = ",";
	
	private List<String> words = new ArrayList<String>();
	
	public boolean match(String input) {
		if (!input.contains(KEYWORD_SEPARATOR)) {
			return words.contains(input);
		} else {
			String[] inputWords = input.split(KEYWORD_SEPARATOR);
			if (inputWords.length == 0) {
				return false;
			}
			boolean match = true;
			for (String inputWord : inputWords) {
				if (!words.contains(inputWord)) {
					match = false;
				}
			}
			return match;
		}
	}
	
	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}
}
