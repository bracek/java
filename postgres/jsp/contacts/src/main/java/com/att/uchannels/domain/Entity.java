package com.att.uchannels.domain;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */

import javax.persistence.*;


@javax.persistence.Entity(name = "Entity")
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_id_seq")
    @SequenceGenerator(name = "entity_id_seq", sequenceName = "entity_id_seq", allocationSize = 1)
    @Column(name = "ENTITY_ID")
    private int id;

    @Column(name = "attribute")
    private String attribute;

    public Entity() {
    }

    public Entity(int id, String attribute) {
        super();
        this.id = id;
        this.attribute = attribute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
