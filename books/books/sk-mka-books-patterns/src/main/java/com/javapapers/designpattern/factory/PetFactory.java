package com.javapapers.designpattern.factory;

//Factory method pattern implementation that instantiates objects based on logic
public class PetFactory {

	public Pet getPet(String petType) {
		Pet pet = null;

		// based on logic factory instantiates an object
		if ("bark".equals(petType))
			pet = new Dog();
		else if ("quack".equals(petType))
			pet = new Duck();
		return pet;
	}
}