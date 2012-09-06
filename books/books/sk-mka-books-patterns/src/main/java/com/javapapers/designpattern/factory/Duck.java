package com.javapapers.designpattern.factory;

//sub class 2 that might get instantiated by a factory method pattern
public class Duck implements Pet {
	public String speak() {
		return "Quack quack...";
	}
}