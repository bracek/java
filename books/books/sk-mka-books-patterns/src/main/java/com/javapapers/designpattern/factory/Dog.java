package com.javapapers.designpattern.factory;

//sub class 1 that might get instantiated by a factory method pattern
public class Dog implements Pet {

	public String speak() {
		return "Bark bark...";
	}
}