package com.ixonos.skillnet.web.ws.common;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Apr 7, 2009
 * Time: 10:51:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class WSReport {

    private List<String> stringRecords = null;
    private List<Double> doubleRecords = null;

    public List<String> getStringRecords() {
        return stringRecords;
    }

    public void setStringRecords(final List<String> stringRecords) {
        this.stringRecords = stringRecords;
    }

    public List<Double> getDoubleRecords() {
        return doubleRecords;
    }

    public void setDoubleRecords(final List<Double> doubleRecords) {
        this.doubleRecords = doubleRecords;
    }
    
}
