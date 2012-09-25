package com.javapapers.designpattern.abstractfactory;

public class Wonderland {
	public Wonderland(final AnimalFactory factory) {
		Animal animal = factory.createAnimal();
		animal.breathe();
	}
}