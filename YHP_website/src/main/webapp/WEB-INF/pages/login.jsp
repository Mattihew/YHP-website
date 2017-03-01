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
			<form id="loginForm" method="post" action="./j_security_check">
				<div class="form-group">
					<label for="j_username">Username:</label>
					<input type="text" class="form-control" id="j_username" name="j_username" placeholder="username" />
				</div>
				<div class="form-group">
					<label for="j_password">Password:</label>
					<input type="password" class="form-control" id="j_password" name="j_password" placeholder="password" />
				</div>
				<button type="submit" class="btn btn-success">Login</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</form>
		</div>
	</body>
</html>