package storage;

import java.sql.Connection;
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
	private static Database instance;
	
	private final Connection con;
	
	public static Database getInstance()
	{
		if (Database.instance == null)
		{
			try
			{
				Database.instance = new Database();
			}
			catch (NamingException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		return Database.instance;
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
		DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/YHP");
		this.con = ds.getConnection();
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
	
	public void dispose()
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
