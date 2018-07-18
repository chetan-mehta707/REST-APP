package com.moneytap.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.moneytap.data.BitcoinDetails;
import com.moneytap.model.BitcoinPrice;

@Path("/btc")
public class BitcoinDetailService {

	@GET
	@Path("/mean/{duration}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMean(@PathParam("duration") int duration) {
		BitcoinDetails btcDetails = new BitcoinDetails();
		BitcoinPrice btcObj = btcDetails.getMeanPrice(duration);
		return Response.status(200).entity(btcObj).build();
	}

	@GET
	@Path("/median/{duration}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMedian(@PathParam("duration") int duration) {
		BitcoinDetails btcDetails = new BitcoinDetails();
		BitcoinPrice btcObj = btcDetails.getMedianPrice(duration);
		return Response.status(200).entity(btcObj).build();
	}


}
