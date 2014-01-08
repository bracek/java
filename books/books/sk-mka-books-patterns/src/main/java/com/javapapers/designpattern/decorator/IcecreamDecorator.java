package com.javapapers.designpattern.decorator;

abstract class IcecreamDecorator implements Icecream {
	 
	  protected Icecream specialIcecream;
	 
	  public IcecreamDecorator(Icecream specialIcecream) {
	    this.specialIcecream = specialIcecream;
	  }
	 
	  public String makeIcecream() {
	    return specialIcecream.makeIcecream();
	  }
	}