package com.mkyong.hashing;

public class Bird {

	private static int counter;

	public static int getCounter() {
		return counter;
	}

	public Bird() {
		super();
		counter++;
	}
	
	public static void setCounter(int counter) {
		Bird.counter = counter;
	}

}
