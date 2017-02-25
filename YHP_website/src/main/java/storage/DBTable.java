package storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Matt Rayner
 */
public abstract class DBTable
{
	/**
	 * Class Constructor.
	 */
	public DBTable()
	{
		super();
	}
	
	public void getData()
	{
		try
		{
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/YHP");
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from test");
			while(rs.next())
			{
				System.out.println(rs.getInt(2));
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
	}
}
