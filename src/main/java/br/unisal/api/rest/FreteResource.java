package br.unisal.api.rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import br.unisal.api.impl.CityDistanceCalculate;
import br.unisal.api.model.City;
import com.google.gson.JsonArray;

import br.unisal.api.model.Frete;
import br.unisal.api.service.FreteService;
import br.unisal.api.util.MessageCodeHTTP;

@Path("/frete")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")

@Component
public class FreteResource {

	@Context
	HttpServletRequest request;

	@Autowired
	private FreteService freteService;
	
	// @RolesAllowed({"user", "admin"})
	@GET
	public Response get() {				
		return Response.ok(freteService.getFrete()).build();
	}
	
	// @RolesAllowed({"admin"})
	@POST
	public Response post(Frete f) {
		double distance = 0;
		
		CityDistanceCalculate citysDistance = new CityDistanceCalculate();	
		distance = citysDistance.calculateDistanceInKilometers(f.getCidadeA(), f.getCidadeB());
		
		f.setDistancia(distance);
		f.calculaValorFrete();		

		try {
			freteService.save(f);
						
		} catch (ConstraintViolationException e) {
			return Response.ok(MessageCodeHTTP.CONSTRAINT_VIOLATION_RSP).build();
		}
		
		return Response.ok(f).build();
	}	
	

}
