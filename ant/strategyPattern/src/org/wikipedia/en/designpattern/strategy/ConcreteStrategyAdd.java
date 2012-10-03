package org.wikipedia.en.designpattern.strategy;

/**
 * @author bracek
 */

// Implements the algorithm using the strategy interface
class ConcreteStrategyAdd implements Strategy {

    public int execute(int a, int b) {
        System.out.println("Called ConcreteStrategyA's execute()");
        return a + b;  // Do an addition with a and b
    }

}

