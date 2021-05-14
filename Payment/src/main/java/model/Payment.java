package model;

import java.sql.*;

public class Payment {

	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	con =
	DriverManager.getConnection(
	"jdbc:mysql://127.0.0.1:3306/test", "root", "");
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	return con;
	}
	
	public String readItems()
	{
		String output = "";
		
		try
		{

			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for reading.";}
			
			// Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" +
			 "<th>Payment Price</th>" +
			 "<th>Payment Description</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
			 
			 String query = "select * from items";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			// iterate through the rows in the result set
			 while(rs.next())
			 {
					 String paymentID = Integer.toString(rs.getInt("PaymentID"));
					 String productCode = rs.getString("productCode");
					 String productName = rs.getString("productName");
					 String paymentPrice = Double.toString(rs.getDouble("paymentPrice"));
				     String paymentDesc = rs.getString("paymentDesc");

				  // Add into the html table
				     output += "<tr><td>" + productCode + "</td>";
				     output += "<td>" + productName + "</td>";
				     output += "<td>" + paymentPrice + "</td>";
				     output += "<td>" + paymentDesc + "</td>";
				     
				  //buttons
				     output += "<td><input name ='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				    		 + "<td><form method='post' action='payments.jsp'>"
				    		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				    		 + "<input name='paymentID' type='hidden' value='" + paymentID + "'>" + "</form></td></td>";
			 }
			 
			 con.close();
			 
			 //complete the html table
			 output += "</table>";			
			 
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	
	}
	
	public String insertItem(String code, String name, String price, String desc)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	// create a prepared statement
	 String query = " insert into items" + "(`paymentID`,`productCode','productName`,`paymentPrice`,`paymentDesc`)" + " values (?, ?, ?, ?, ?)";
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, name);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(5, desc);
	// execute the statement
	
	 preparedStmt.execute();
	 con.close();
	 
	 String newItems = readItems();
	 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
	 
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	}
	
	public String updateItem(String ID, String code, String name, String price, String desc)
	{
		String output = "";
		try {
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for updating.";}
			
			//create a prepared statement
			String query = "UPDATE items SET productCode=?,productName=?,paymentPrice=?,paymentDesc=? WHERE paymentID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//blinding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +
			newItems + "\"}";
			}
		catch (Exception e)
		{

				output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
				System.err.println(e.getMessage());
			
		}
		return output;
	}
	

	public String deleteitems(String paymentID) 
	{
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for deleting.";}
			
			//create a prepared statement
			String query = "delete from items where paymentID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//blinding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			
			}
		catch (Exception e)
		{

			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
			
		}
		return output;
			
		}

	
	
	
}
