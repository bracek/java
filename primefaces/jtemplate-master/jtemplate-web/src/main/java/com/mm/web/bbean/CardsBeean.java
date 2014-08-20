package com.mm.web.bbean;

import com.mm.model.domain.Card;
import com.mm.module.one.ICardService;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.chart.*;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Card Backed Bean
 *
 * @author Miroslav Katrak
 * @version 1.0.0
 */

@Named("cardsBean")
@Scope("session")
@ManagedBean(name = "cardsBean")
public class CardsBeean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ICardService entityService;

    private int id;
    private Date date;
    private float slsp;
    private float autokarta;
    private List<Card> entityList = new ArrayList<Card>();
    private LineChartModel lineModel = new LineChartModel();
    private BarChartModel barModel = new BarChartModel();
    private ChartSeries slspChartSeries;
    private ChartSeries autokartaChartSeries;



    @PostConstruct
    public void init() {
        slspChartSeries = new ChartSeries();
        autokartaChartSeries = new ChartSeries();

        createLineModels();
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        slspChartSeries.setLabel("SLSP");
        autokartaChartSeries.setLabel("AUTOKARTA");

        barModel.setTitle("Bar Card Chart");
        barModel.setLegendPosition("ne");

        final Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Date");

        final Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
        yAxis.setMax(25);
    }


    private void createLineModels() {
        lineModel = initLinearModel();

//        slspChartSeries.setFill(true);
        slspChartSeries.setLabel("SLSP");
//        autokartaChartSeries.setFill(true);
        autokartaChartSeries.setLabel("AUTOKARTA");

        lineModel.setTitle("Line Card Chart");
        lineModel.setLegendPosition("ne");
        lineModel.setStacked(true);
        lineModel.setShowPointLabels(true);

        final Axis xAxis = new CategoryAxis("Date");
        lineModel.getAxes().put(AxisType.X, xAxis);

        final Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
        yAxis.setMax(25);
    }

    private LineChartModel initLinearModel() {
        final LineChartModel model = new LineChartModel();
        updateData(slspChartSeries, autokartaChartSeries);
        model.addSeries(slspChartSeries);
        model.addSeries(autokartaChartSeries);
        return model;
    }

    private BarChartModel initBarModel() {
        final BarChartModel model = new BarChartModel();
        //date were updated in linear model
//        updateData(slspChartSeries, autokartaChartSeries);
        model.addSeries(slspChartSeries);
        model.addSeries(autokartaChartSeries);
        return model;
    }


    private void updateData(ChartSeries slspChartSeries, ChartSeries autokartaChartSeries) {
        slspChartSeries.getData().clear();
        autokartaChartSeries.getData().clear();

        final List<Card> slspList = getEntityList();
        for (final Card card : slspList) {
            final String key = card.getDate().toString();
            slspChartSeries.set(key, card.getSlsp());
            autokartaChartSeries.set(key, card.getAutokarta());
        }
    }


    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            final Card card = getEntityList().get(event.getRowIndex());
            entityService.updateCard(card);

        }
    }

    public LineChartModel getLineModel() {
        updateData(slspChartSeries, autokartaChartSeries);
        return lineModel;
    }

    /**
     * data are already updated in line model
     * @return
     */
    public BarChartModel getBarModel() {
//        updateData(slspChartSeries, autokartaChartSeries);
        return barModel;
    }

    public void removeItem(Card card) {
        entityService.deleteCard(card);
        entityList.remove(card);
    }

    public void addCard() {
        try {
            final Card entity = new Card();
            entity.setId(getId());
            entity.setDate(getDate());
            if (getDate() == null)
                entity.setDate(new Date());

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