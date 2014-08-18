package com.mm.web.bbean;

import com.mm.model.domain.Card;
import com.mm.model.domain.Entity;
import com.mm.module.one.ICardService;
import com.mm.module.one.IEntityService;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity Backed Bean
 *
 * @author Miroslav Katrak
 * @version 1.0.0
 */

@Named("cardsBean")
@Scope("session")
public class CardsBeean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ICardService entityService;

    private int id;
    private String attribute;
    private String slsp;
    private String autokarta;
    private List<Card> entityList;


    public void addEntity() {
        try {
            Card entity = new Card();
            entity.setId(getId());
            entity.setAutokarta(getAutokarta());
            entity.setSlsp(getSlsp());
            getEntityService().addCard(entity);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Message: "));

        } catch (DataAccessException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: "));
        }

    }


    public void reset() {
        this.setId(0);
        this.setAttribute("");
    }

    public List<Card> getEntityList() {
        entityList = new ArrayList<Card>();
        entityList.addAll(getEntityService().getCards());
        return entityList;
    }

//    public IEntityService getEntityService() {
//        return entityService;
//    }
//
//    public void setEntityService(IEntityService entityService) {
//        this.entityService = entityService;
//    }

//    public void setEntityList(List<Entity> entityList) {
//        this.entityList = entityList;
//    }

    public ICardService getEntityService() {
        return entityService;
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

    public void setEntityService(ICardService entityService) {
        this.entityService = entityService;
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

    public void setEntityList(List<Card> entityList) {
        this.entityList = entityList;
    }
}