/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.me;

/**
 *
 * @author bracek
 */
public class AbstractPerson implements PersonInterface {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractPerson() {
        System.out.println("Constructor AbstractPerson");
    }
}
