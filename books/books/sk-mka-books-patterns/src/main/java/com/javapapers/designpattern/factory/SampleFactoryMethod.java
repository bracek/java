package com.javapapers.designpattern.factory;

//using the factory method pattern
public class SampleFactoryMethod {

	public static void main(String args[]) {

		// creating the factory
		PetFactory petFactory = new PetFactory();

		// factory instantiates an object
		Pet pet = petFactory.getPet("bark");

		// you don't know which object factory created
		System.out.println(pet.speak());
	}
}
