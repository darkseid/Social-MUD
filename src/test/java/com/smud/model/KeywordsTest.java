package com.smud.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class KeywordsTest {

	private Keywords keywords;
	
	@Before
	public void setUp() {
		keywords = new Keywords();
	}
	
	@Test
	public void testMatchFirstWordKeyword() throws Exception {
		List<String> words = new ArrayList<String>();
		words.add("fido");
		words.add("bestial");
		keywords.setWords(words);
		
		Assert.assertTrue(keywords.match("fido"));
	}

	@Test
	public void testMatchSecondWordKeyword() throws Exception {
		List<String> words = new ArrayList<String>();
		words.add("fido");
		words.add("bestial");
		keywords.setWords(words);
		
		Assert.assertTrue(keywords.match("fido"));
	}
	
	@Test
	public void testMatchAllWordsKeyword() throws Exception {
		List<String> words = new ArrayList<String>();
		words.add("fido");
		words.add("bestial");
		keywords.setWords(words);
		
		Assert.assertTrue(keywords.match("fido,bestial"));
	}
	
	@Test
	public void testDontMatchAnyKeyword() throws Exception {
		List<String> words = new ArrayList<String>();
		words.add("fido");
		words.add("bestial");
		keywords.setWords(words);
		
		Assert.assertFalse(keywords.match("banana"));
	}
	
	@Test
	public void testDontMatchOneKeyword() throws Exception {
		List<String> words = new ArrayList<String>();
		words.add("fido");
		words.add("bestial");
		keywords.setWords(words);
		
		Assert.assertFalse(keywords.match("fido,bestial,banana"));
	}
}
