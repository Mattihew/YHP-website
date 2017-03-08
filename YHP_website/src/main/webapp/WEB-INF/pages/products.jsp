<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="storage.ProductCache"%>
<%@page import="models.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<%@ include file="./includes/bootstrapImports.jspf" %>
		<title>Yeovil Healthcare Products</title>
		<style>
			.panel-footer.row {
				margin: 0px;
			}
		</style>
		<script src="./js/xhttp.js"></script>
		<script>
			function addToOrder(id)
			{
				xhttp.sendRequest("productid=" + id, responseHandler, '/AddProductToOrder');
			}
			function responseHandler(responseText)
			{
				alert(responseText);
			}
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<%@ include file="./includes/header.jspf" %>
			<% final boolean grid = "grid".equals(request.getParameter("view")); %>
			<h1>Products</h1>
			<%for (Product product : ProductCache.getInstance().getProducts()) { %>
			<div class="col-sm-4">
		      <div class="panel panel-default">
		        <div class="panel-heading"><%=product.getName()%></div>
		        <div class="panel-body">
		        	<img src="<%=product.getImageURL()%>" class="img-responsive" style="width:100%" alt="Image"></img>
		        	<div class="col-md-6"><%=product.getDescription()%></div>
		        </div>
		        <div class="panel-footer row">
			        <div class="col-md-6"><%=product.getPriceString()%></div>
			        <div class="col-md-6">
			        	<button type="button" class="btn btn-success btn-block" onclick="addToOrder('<%=product.getId() %>');">Add to cart<span class="glyphicon glyphicon-shopping-cart" /></button>
			        </div>
		        </div>
		      </div>
		    </div>
			<% } %>
		</div>
	</body>
</html>