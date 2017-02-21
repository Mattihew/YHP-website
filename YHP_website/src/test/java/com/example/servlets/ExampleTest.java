package com.example.servlets;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class ExampleTest
{

	@Test
	public void testTest()
	{
		Assert.assertThat(ExampleServlet.test(), CoreMatchers.is("Test"));
	}

}
