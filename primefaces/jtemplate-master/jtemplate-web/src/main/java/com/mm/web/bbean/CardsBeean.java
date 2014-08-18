package com.mm.web.bbean;

import com.mm.model.domain.Card;
import com.mm.module.one.ICardService;
import org.primefaces.model.chart.*;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity Backed Bean
 *
 * @author Miroslav Katrak
 * @version 1.0.0
 */

@Named("cardsBean")
//@Scope("session")
@SessionScoped
public class CardsBeean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ICardService entityService;

    private int id;
    private Date date;
    private float slsp;
    private float autokarta;
    private List<Card> entityList = new ArrayList<Card>();

    public LineChartModel getChartModel() {
        final LineChartModel lineModel = new LineChartModel();

        final LineChartSeries slsp = new LineChartSeries();
        slsp.setFill(true);
        slsp.setLabel("SLSP");
        final LineChartSeries autokarta = new LineChartSeries();
        autokarta.setFill(true);
        autokarta.setLabel("AUTOKARTA");

        final List<Card> slspList = getEntityList();
        for (int i = 0; i < slspList.size(); i++) {
            final Card card = slspList.get(i);
            final String key = card.getDate().toString();
            slsp.set(key, card.getSlsp());
            autokarta.set(key, card.getAutokarta());
        }

        lineModel.addSeries(slsp);
        lineModel.addSeries(autokarta);
        lineModel.setTitle("Card  Chart");
        lineModel.setLegendPosition("ne");
        lineModel.setStacked(true);
        lineModel.setShowPointLabels(true);

        final Axis xAxis = new CategoryAxis("Date");
        lineModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
        yAxis.setMax(25);
        return lineModel;
    }

    public void removeItem(Card card) {
        entityService.deleteCard(card);
        entityList.remove(card);
    }


    public void addEntity() {
        try {
            final Card entity = new Card();
            entity.setId(getId());
            entity.setDate(getDate());
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
        this.setAutokarta(0);
        this.setSlsp(0);
    }

    public List<Card> getEntityList() {
        entityList.clear();
        entityList.addAll(getEntityService().getCards());
        return entityList;
    }

    public ICardService getEntityService() {
        return entityService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEntityService(ICardService entityService) {
        this.entityService = entityService;
    }

    public void setEntityList(List<Card> entityList) {
        this.entityList = entityList;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}