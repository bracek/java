package com.ixonos.skillnet.web.ws.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.ixonos.skillnet.web.ws.ReportService;
import com.ixonos.skillnet.web.ws.common.WSReport;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Apr 7, 2009
 * Time: 10:55:36 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.ixonos.skillnet.web.ws.ReportService")
public class ReportServiceImpl implements ReportService {

    @Override
    public WSReport getReportById(Integer reportId) {
        WSReport wsReport = new WSReport();

        List<Double> doubleList = new ArrayList<Double>();
        doubleList.add(1.0);
        doubleList.add(2.0);
        doubleList.add(3.0);

        wsReport.setDoubleRecords(doubleList);

        List<String> stringList = new ArrayList<String>();
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        wsReport.setStringRecords(stringList);

        return wsReport;  //To change body of implemented methods use File | Settings | File Templates.
    }
    
}
