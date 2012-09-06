/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.me;

/**
 *
 * @author bracek
 */
public class RunnPerson {

    public static void main(String[] args) {

        Person a = new Person();
        a.setName("Peto");
        System.out.println("a.getName() = " + a.getName());
        System.out.println("xxxx");

        AbstractPerson b = new Person();
        b.setName("jozo");
        System.out.println("b.getName() = " + b.getName());


    }
}
