/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.geomutvary;

/**
 *
 * @author bracek
 */
public interface Porovnatelna {
    // tento (objekt pomenovaný jeVacsiNez) a
    // druhý musia byť inštanciou rovnakej triedy
    // vracia 1, 0, -1 ak tento je väčší, rovný 
    // alebo menší než druhý
    public int jeVacsiNez(Porovnatelna druhy);
}
