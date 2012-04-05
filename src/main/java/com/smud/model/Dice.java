package com.smud.model;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

public class Dice {

	@Autowired
	private Random random;
	
	public int roll(int diceFaces) {
		return random.nextInt(diceFaces) + 1;
	}
	
	public int[] roll(int numberOfDices, int diceFaces) {
		int[] result = new int[numberOfDices];
		for (int i = 0; i < numberOfDices; i++) {
			result[i] = roll(diceFaces);
		}
		return result;
	}
	
	public void setRandom(Random random) {
		this.random = random;
	}
	
}
