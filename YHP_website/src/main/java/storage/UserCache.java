package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UserRole;

public class UserCache
{
	private final Database database;
	
	public UserCache(final Database database)
	{
		this.database = database;
	}
	
	public User getUser(final String username)
	{
		ResultSet users = this.database.executeQuery("SELECT * FROM users WHERE user_name='" + username + "';");
		User user = null;
		try
		{
			if (users.next())
			{
				ResultSet roles = this.database.executeQuery("SELECT role_name FROM user_roles WHERE user_name='" + username + "'");
				final List<String> rolenames = new ArrayList<>();
				while (roles.next())
				{
					rolenames.add(roles.getString(1));
				}
				
				final User.Builder userBuilder = new User.Builder(users.getString(1), users.getString(1));
				userBuilder.role(UserRole.fromDatabaseValues(rolenames));
				user = userBuilder.build();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.database.close();
		}
		return user;
	}
}