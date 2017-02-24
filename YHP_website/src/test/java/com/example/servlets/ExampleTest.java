package com.example.servlets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class ExampleTest
{

	@Test
	public void testTest()
	{
		Integer number = null;
		try
		{
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/postgres");
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from test");
			if(rs.next())
			{
				number = rs.getInt(2);
			}
			con.close();
		} catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertThat(number, CoreMatchers.is(Integer.valueOf(1)));
	}

}
