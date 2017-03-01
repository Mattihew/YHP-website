package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import models.User;
import models.UserRole;

public class UserCache
{
	private static UserCache INSTANCE;
	
	private final Database database;
	
	private final Map<UUID, User> users = new HashMap<>();
	
	public UserCache getInstance()
	{
		if (UserCache.INSTANCE == null)
		{
			UserCache.INSTANCE = new UserCache(Database.getInstance());
		}
		return UserCache.INSTANCE;
	}
	
	public UserCache(final Database database)
	{
		this.database = database;
	}
	
	
	public User getUser(final String username)
	{
		QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
		List<User> users = null;
		try
		{
			users = run.query(
					"SELECT * FROM users WHERE user_name='" + username + "';",
					new UserResultsSetHandler());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if (users == null || users.isEmpty())
		{
			return null;
		}
		return users.get(0);
	}
	
	public boolean putUser(final User user)
	{
		//TODO
		return false;
	}
	
	private class UserResultsSetHandler extends AbstractListHandler<User>
	{
		@Override
		protected User handleRow(final ResultSet rs) throws SQLException
		{
			final User.Builder userBuilder =
					new User.Builder(rs.getString("forename"), rs.getString("surname"));
			final QueryRunner roleRunner = new QueryRunner(Database.getInstance().getDataSource());
			final List<String> roles = roleRunner.query(
					"SELECT role_name FROM user_roles WHERE user_name='" + rs.getString("user_name") + "'",
					new ColumnListHandler<String>());
			userBuilder.id(UUID.fromString(rs.getString("user_id")));
			userBuilder.role(UserRole.fromDatabaseValues(roles));
			// userBuilder.address(value); TODO add address getter.
			return userBuilder.build();
		}
	}
}
