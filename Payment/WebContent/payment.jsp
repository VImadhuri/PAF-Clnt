<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
	<h1>Payments Management V10.1</h1>
	
	<form id="formItem" name="formItem">
		Product code:
		<input id="productCode" name="productCode" type="text"
		class="form-control form-control-sm">
		<br> Product name:
		<input id="productName" name="productName" type="text"
		class="form-control form-control-sm">
		<br> Payment price:
		<input id="paymentPrice" name="paymentPrice" type="text"
		class="form-control form-control-sm">
		<br> Payment description:
		<input id="paymentDesc" name="paymentDesc" type="text"
		class="form-control form-control-sm">
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
		class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave"
		name="hidPaymentIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div class="container" id="ItemsGrid">
				<fieldset>
					<legend><b>View Payment Details</b></legend>
					<form method="post" action="payments.jsp" class="table table-striped">
						<%
						    Payment paymentObj = new Payment();
							out.print(paymentObj.readItems());
						%>
					</form>
					<br>
				</fieldset>
			</div>
</div>
</div> </div> 

</body>
</html>