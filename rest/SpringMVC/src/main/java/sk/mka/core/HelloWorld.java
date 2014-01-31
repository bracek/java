package sk.mka.core;

/**
 * Created by katrami on 1/31/14.
 */

/**
 * Spring bean
 */
public class HelloWorld {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Spring 3 : Hello ! " + name);
    }
}