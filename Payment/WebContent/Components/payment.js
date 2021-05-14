//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidOrderIDSave").val("");
	$("#ORDER")[0].reset();
});



$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
		{
		url : "PaymentsAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
		onItemSaveComplete(response.responseText, status);
		window.location.reload(true);
		}
	});
});

function onItemSaveComplete(response, status)
{
var resultSet = JSON.parse(response);

if (resultSet.status.trim() == "success")
{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
}

else if (status == "error")
{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
} else
{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
}
	$("#hidPaymentIDSave").val("");
	$("#formItem")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
	url : "PaymentsAPI",
	type : "DELETE",
	data : "paymentID=" + $(this).data("paymentId"),
	dataType : "text",
	complete : function(response, status)
{
	onItemDeleteComplete(response.responseText, status);
}
});
});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
	
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}






