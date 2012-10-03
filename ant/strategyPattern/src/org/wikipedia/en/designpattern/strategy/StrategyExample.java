package org.wikipedia.en.designpattern.strategy;

/**
 * @author bracek
 */
class StrategyExample {

    public static void main(String[] args) {

        Context context;

        // Three contexts following different strategies
        context = new Context(new ConcreteStrategyAdd());
        int resultA = context.executeStrategy(3, 4);
        System.out.println("resultA: " + resultA);

        context = new Context(new ConcreteStrategySubtract());
        int resultB = context.executeStrategy(3, 4);
        System.out.println("resultB: " + resultB);

        context = new Context(new ConcreteStrategyMultiply());
        int resultC = context.executeStrategy(3, 4);
        System.out.println("resultC: " + resultC);

    }

}

