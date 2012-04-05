package com.smud.util;

public class StringUtils {

	public static String capitalizeFirstLetter(String word) {
		if (word == null || word.length() == 0) {
			return word;
		}
		return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
	}
}
