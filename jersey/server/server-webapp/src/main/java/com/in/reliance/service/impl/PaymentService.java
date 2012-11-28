package com.in.reliance.service.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in.reliance.service.IPaymentService;
import com.in.reliance.transaction.TransactionBo;

@Component
@Path("/payment")
public class PaymentService implements IPaymentService {

	@Autowired
	TransactionBo transactionBo;

	@Override
	@GET
	@Path("/do")
	public Response doAction() {
		final String result = transactionBo.save();
		return Response.status(200).entity(result).build();
	}
}