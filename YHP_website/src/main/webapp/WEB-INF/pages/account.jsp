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
			<%!
			private String getUserAttribute(boolean isEditing, String attributeName, String attributeValue)
			{
				final String formType = isEditing? "":" text";
				final String readOnly = isEditing? "":" readonly='readonly'";
				
				//Ternary operator didn't seem to like attributeValue == null ? attributeValue : attributeName;
				if (null == attributeValue)
				{
					attributeValue = attributeName; 
				}
				
				return String.format("<label>%s: <input value='%s' type='text' class='form-control%s' name='%s' title='%s'%s", 
						attributeName, attributeValue, formType, attributeName.toLowerCase(), attributeName, readOnly);
			}
			%>
			<div class="col-sm-4 col-sm-offset-4">
				<form id="userForm" method="post">
					<div class="form-group">
						<%=getUserAttribute(isEditing, "Username", editUser==null?null:editUser.getUsername())%>
					</div>
					<%	if(isEditing)
						{
							%><div class="form-group">
								<%=getUserAttribute(isEditing, "Password", "User password here")%>
							</div>
							<div class="form-group">
								<%=getUserAttribute(isEditing, "Confirm Password", "User password here")%>
							</div><%
						}%>
					<div class="form-group">
						<%=getUserAttribute(isEditing, "Forename", editUser==null?null:editUser.getForename())%>
					</div>
					<div class="form-group">
						<%=getUserAttribute(isEditing, "Surname", editUser==null?null:editUser.getSurname())%>
					</div>
					<fieldset>
						<legend>Address:</legend>
						<div class="form-group">
							<%=getUserAttribute(isEditing, "Address 1", editUser==null?null:editUser.getAddress().getBuilding())%>
						</div>
						<div class="form-group">
							<%=getUserAttribute(isEditing, "Address 2", editUser==null?null:editUser.getAddress().getStreet())%>
						</div>
						<div class="form-group">
							<%=getUserAttribute(isEditing, "Address 3", editUser==null?null:editUser.getAddress().getCity_town())%>
						</div>
						<div class="form-group">
							<%=getUserAttribute(isEditing, "Address 4", editUser==null?null:editUser.getAddress().getArea_code())%>
						</div>
						<div class="form-group">
							<%=getUserAttribute(isEditing, "Address 5", editUser==null?null:editUser.getAddress().getCountry())%>
						</div>
					</fieldset>
					<button type="button" class="btn btn-success" onclick="sendRequest();">Submit</button>
				</form>
			</div>
		</div>
	</body>
</html>