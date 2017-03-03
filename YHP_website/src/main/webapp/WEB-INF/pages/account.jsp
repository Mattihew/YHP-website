<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.UUID"%>
<%@page import="storage.UserCache"%>
<%@page import="models.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<%@ include file="./includes/bootstrapImports.jspf" %>
		<title>Account</title>
		<style>
			input.text[readonly]
			{
				border: none;
				background-color: white;
			}
		</style>
		<script language="javascript">
			function sendRequest()
			{
				var formData = {};
				var inputs = document.getElementById('userForm').getElementsByTagName('input');
				for (var i = 0; i < inputs.length; i++)
				{
					formData[inputs[i].name] = inputs[i].value;
				}
				var xhttp;
				if (window.XMLHttpRequest)
				{
					xhttp = new XMLHttpRequest();
				}
				else
				{
					xhttp = new ActiveXObject('Microsoft.XMLHTTP');
				}
				xhttp.open('POST', '', true);
				xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
				xhttp.onreadystatechange = function()
				{
					if (this.readyState == 4 && this.status == 200)
					{
						var response = JSON.parse(this.responseText);
						if (response.error !== null)
						{
							alert(response.error);
						}
					}
				}
				xhttp.send('user=' + JSON.stringify(formData));
			}
		
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<%@ include file="./includes/header.jspf" %><%
			final boolean isEditing = "edit".equals(request.getAttribute("mode"));
			final String userID = request.getParameter("user");
			final User editUser = userID==null ? null : 
				UserCache.getInstance().getUser(UUID.fromString(userID));
			%>
			<div class="col-sm-4 col-sm-offset-4">
				<form id="userForm" method="post">
					<div class="form-group">
						<label>Username: <input <%=editUser != null ? "value='" + editUser.getForename() + "'" : "" %>type="text" class="form-control<%= isEditing?"":" text" %>" name="username" title="Username" <%= isEditing?"":"readonly='readonly'"%>/></label>
					</div>
					<div class="form-group">
						<label>Password: <input type="password" class="form-control" name="password" title="Password"/></label>
					</div>
					<div class="form-group">
						<label>Confirm Password: <input type="password" class="form-control" name="password2" title="Confirm Password"/></label>
					</div>
					<div class="form-group">
						<label>Forename: <input type="text" class="form-control" name="forename" title="forename"/></label>
					</div>
					<div class="form-group">
						<label>Surname: <input type="text" class="form-control" name="surname" title="surname"/></label>
					</div>
					<fieldset>
						<legend>Address:</legend>
						<div class="form-group">
							<label>Address 1: <input type="text" class="form-control" name="address1" title="address1"/></label>
						</div>
						<div class="form-group">
							<label>Address 2: <input type="text" class="form-control" name="address2" title="address2"/></label>
						</div>
					</fieldset>
					<button type="button" class="btn btn-success" onclick="sendRequest();">Submit</button>
				</form>
			</div>
		</div>
	</body>
</html>