package com.globallogic.att.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Miroslav Katrak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "president_title_uk", columnNames = {"title"})})
@SuppressWarnings("serial")
public class President implements Serializable {

    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @XmlElement
    @Column(length = 50, unique = true, nullable = false)
    private String title;

    @XmlElement
    @Column(length = 50, unique = true, nullable = false)
    private String firstName;
    @XmlElement
    @Column(length = 50, unique = true, nullable = false)
    private String lastName;


    public President() {
        this(null);
    }

    public President(Integer id) {
        super();
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Builder implements Serializable {

        public final President entity;

        private Builder() {
            super();
            entity = new President();
        }

        public Builder id(Integer id) {
            entity.setId(id);
            return this;
        }

        public Builder title(String title) {
            entity.setTitle(title);
            return this;
        }

        public Builder name(String title, String firstName, String lastName) {
            entity.setTitle(title);
            entity.setFirstName(firstName);
            entity.setLastName(lastName);
            return this;
        }


//        public Builder stores(List<Store> stores) {
//            entity.setStores(stores);
//            return this;
//        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
