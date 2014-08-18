package com.mm.model.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity(name = "Card")
public class Card {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "slsp")
    private String slsp;

    @Column(name = "autokarta")
    private String autokarta;


    public Card() {
    }

    public Card(String slsp) {
        this.slsp = slsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlsp() {
        return slsp;
    }

    public void setSlsp(String slsp) {
        this.slsp = slsp;
    }

    public String getAutokarta() {
        return autokarta;
    }

    public void setAutokarta(String autokarta) {
        this.autokarta = autokarta;
    }

}
