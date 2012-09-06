/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.generictypes;

/**
 *
 * @author bracek
 */
/**
 * Generická verzia triedy Krabica. 
 */
/**
 * Táto verzia používa generickú metódu.
 */
public class Krabica<T> {

    private T t;

    public void pridaj(T t) {
        this.t = t;
    }

    public T vrat() {
        return t;
    }

    public <U> void prezri(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Krabica<Integer> integerKrabica = new Krabica<Integer>();
        integerKrabica.pridaj(new Integer(10));
        integerKrabica.prezri("dajaký text");
    }
}
