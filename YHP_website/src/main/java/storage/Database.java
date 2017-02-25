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
public class Database
{
	private static Database INSTATANCE;
	
	private final DataSource ds;
	
	private Connection con;
	
	public static Database getInstance()
	{
		if (Database.INSTATANCE == null)
		{
			try
			{
				Database.INSTATANCE = new Database();
			}
			catch (NamingException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				if (Database.INSTATANCE.con.isClosed())
				{
					Database.INSTATANCE.con = Database.INSTATANCE.ds.getConnection();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return Database.INSTATANCE;
	}
	
	public static void dispose()
	{
		if (Database.INSTATANCE != null)
		{
			Database.INSTATANCE.close();
		}
	}
	
	/**
	 * Class Constructor.
	 * @throws NamingException
	 * @throws SQLException
	 */
	private Database() throws NamingException, SQLException
	{
		super();
		Context init = new InitialContext();
		this.ds = (DataSource) init.lookup("java:/comp/env/jdbc/YHP");
		this.con = this.ds.getConnection();
	}
	
	public boolean execute(final String sql)
	{
		try
		{
			Statement stat = this.con.createStatement();
			return stat.execute(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet executeQuery(final String sql) throws SQLException
	{
		Statement stat = this.con.createStatement();
		return stat.executeQuery(sql);
	}
	
	public void close()
	{
		try
		{
			this.con.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
