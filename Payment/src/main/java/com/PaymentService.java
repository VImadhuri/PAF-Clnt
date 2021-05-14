package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Payment;


@Path("/Payments")

public class PaymentService {
	Payment paymentobj = new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return paymentobj.readItems();
	}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)

public String insertItem(@FormParam("productCode") String productCode,
						  @FormParam("productName") String productName,
						  @FormParam("paymentPrice") String paymentPrice,
						  @FormParam("paymentDesc") String paymentDesc)
{

	String output = paymentobj.insertItem(productCode, productName, paymentPrice, paymentDesc);
	return output;
	}




@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)

public String updateItem(String itemData)
{
	//Convert the input string to a JOSN object
	JsonObject paymentobject = new JsonParser().parse(itemData).getAsJsonObject();
	
	//Read values
	String paymentID = paymentobject.get("paymentID").getAsString();
	String productCode = paymentobject.get("productCode").getAsString();
	String productName = paymentobject.get("productName").getAsString();
	String paymentPrice = paymentobject.get("paymentPrice").getAsString();
	String paymentDesc = paymentobject.get("paymentDesc").getAsString();
	
	String output = paymentobj.updateItem(paymentID, productCode, productName, paymentPrice, paymentDesc);
	return output;


	}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)

public String deleteitems(String itemData)
{
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	
	String paymentID = doc.select("paymentID").text();
	String output = paymentobj.deleteitems(paymentID);
	return output;
}

	
	

}
