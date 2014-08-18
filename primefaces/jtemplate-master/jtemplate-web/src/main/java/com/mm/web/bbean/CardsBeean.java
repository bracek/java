package com.mm.web.bbean;

import com.mm.model.domain.Card;
import com.mm.module.one.ICardService;
import org.primefaces.model.chart.*;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private float slsp;
    private float autokarta;
    private List<Card> entityList;


    //    public ChartModel getPieModel() {
//    LineChartModel chartModel = new LineChartModel();
//
//        Map<String, Number> data = new HashMap<String, Number>();
//        List<Card> entityList1 = getEntityList();
//        for (int i = 0; i < entityList1.size(); i++) {
//            Card card = entityList1.get(i);
//            String key = String.valueOf(card.getId());
//            data.put(key, card.getAutokarta());
//        }
//        chartModel.setData(data);
//
//        return chartModel;
//    }
//    public PieChartModel getPieModel() {
//        PieChartModel pieModel = new PieChartModel();
//
//        Map<String, Number> data = new HashMap<String, Number>();
//        List<Card> entityList1 = getEntityList();
//        for (int i = 0; i < entityList1.size(); i++) {
//            Card card = entityList1.get(i);
//            String key = String.valueOf(card.getId());
//            data.put(key, card.getAutokarta());
//        }
//        pieModel.setData(data);
//
//        return pieModel;
//    }

    public LineChartModel getChartModel() {
        LineChartModel lineModel = new LineChartModel();

        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);

        lineModel.addSeries(boys);
        lineModel.addSeries(girls);

        lineModel.setTitle("Area Chart");
        lineModel.setLegendPosition("ne");
        lineModel.setStacked(true);
        lineModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Years");
        lineModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(300);

        return lineModel;
    }


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
        this.setAutokarta(0);
        this.setSlsp(0);
    }

    public List<Card> getEntityList() {
        entityList = new ArrayList<Card>();
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
}