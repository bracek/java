package com.ixonos.skillnet.web.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.ixonos.skillnet.web.ws.common.WSReport;

/**
 * Created by IntelliJ IDEA. User: hustasl Date: Apr 7, 2009 Time: 10:47:06 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService(name = "ReportWebService")
public interface ReportService {

	@WebMethod(operationName = "getReportById")
	 WSReport getReportById(
			final @WebParam(name = "reportId", mode = WebParam.Mode.IN) Integer id);

}
