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
	
	public static Database getInstance()
	{
		if (Database.INSTATANCE == null)
		{
			try
			{
				Database.INSTATANCE = new Database();
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
		}
		return Database.INSTATANCE;
	}
	
	/**
	 * Class Constructor.
	 * @throws NamingException
	 * @throws SQLException
	 */
	private Database() throws NamingException
	{
		super();
		Context init = new InitialContext();
		this.ds = (DataSource) init.lookup("java:/comp/env/jdbc/YHP");
	}
	
	public boolean execute(final String sql)
	{
		try (final Connection con = this.ds.getConnection())
		{
			Statement stat = con.createStatement();
			return stat.execute(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet executeQuery(final String sql)
	{
		ResultSet results = null;
		try (final Connection con = this.ds.getConnection())
		{
			Statement stat = con.createStatement();
			results = stat.executeQuery(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return results;
	}
}