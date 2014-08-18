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
    private float slsp;

    @Column(name = "autokarta")
    private float autokarta;


    public Card() {
    }

    public float getSlsp() {
        return slsp;
    }

    public void setSlsp(float slsp) {
        this.slsp = slsp;
    }

    public float getAutokarta() {
        return autokarta;
    }

    public void setAutokarta(float autokarta) {
        this.autokarta = autokarta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
