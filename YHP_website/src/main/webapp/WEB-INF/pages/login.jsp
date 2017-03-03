<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<%@ include file="./includes/bootstrapImports.jspf" %>
		<title>Login</title>
	</head>
	<body>
		<div class="container-fluid">
			<%@ include file="./includes/header.jspf" %>
			<h1>Login</h1>
			<div class="col-sm-6 col-sm-offset-3">
				<div class="row">
					<form id="loginForm" method="post" action="./j_security_check">
						<div class="col-sm-12">
							<div class="form-group">
								<label for="j_username">Username:</label>
								<input type="text" class="form-control" id="j_username" name="j_username" placeholder="username" />
							</div>
							<div class="form-group">
								<label for="j_password">Password:</label>
								<input type="password" class="form-control" id="j_password" name="j_password" placeholder="password" />
							</div>
						</div>
						<div class="col-xs-6">
							<button type="submit" class="btn btn-success btn-block">Login</button>
						</div>
						<div class="col-xs-6">
							<button type="reset" class="btn btn-danger btn-block">Clear</button>
						</div>
					</form>
				</div>
				<% if (request.getParameter("j_username") != null) {%>
				<div class="row" style="margin-top:1em;">
					<div class="col-sm-12">
						<div class="alert alert-danger alert-dismissable fade in">
		  					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		  					<strong>Error!</strong> Username or Password is incorrect.
						</div>
					</div>
				</div>
				<%} %>
			</div>
		</div>
	</body>
</html>