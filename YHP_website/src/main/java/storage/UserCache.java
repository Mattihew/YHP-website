package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import models.user.Digest;
import models.user.User;
import models.user.UserRole;

public class UserCache
{
	private static UserCache INSTANCE;
	
	private final Database database;
	
	private final Set<User> users = new HashSet<>();
	
	public static UserCache getInstance()
	{
		if (UserCache.INSTANCE == null)
		{
			UserCache.INSTANCE = new UserCache(Database.getInstance());
		}
		return UserCache.INSTANCE;
	}
	
	private UserCache(final Database database)
	{
		this.database = database;
	}
	
	public User getUser (final UUID id)
	{
		return this.getUser("user_id", id);
	}
	
	public User getUser (final String username)
	{
		return this.getUser("user_name", username);
	}
	
	private User getUser(final String columnName, final Object columnValue)
	{
		QueryRunner run = this.database.getQueryRunner();
		List<User> foundUsers = null;
		try
		{
			foundUsers = run.query("SELECT * FROM users WHERE ?=?;",
					new UserResultsSetHandler(), columnName, columnValue);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if (foundUsers == null || foundUsers.isEmpty())
		{
			return null;
		}
		this.users.addAll(foundUsers);
		return foundUsers.get(0);
	}
	
	public void putUser(final User user) throws SQLException
	{
		AsyncQueryRunner run = this.database.getAsyncQueryRunner();
		run.update("INSERT INTO users (user_id, user_name, user_pass, forename, surname, address_id) VALUES (?,?,?,?,?,?)"
		+ "ON CONFLICT (user_id) DO UPDATE SET user_name = EXCLUDED.user_name, user_pass=EXCLUDED.user_pass, forename=EXCLUDED.forename, surname=EXCLUDED.surname, address_id=EXCLUDED.address_id;",
				user.getUuid(), user.getUsername(), user.getDigest(), user.getForename(), user.getSurname(), user.getAddress().getId());
		for (final String rolename : user.getRole().toDatabaseValues())
		{
			run.update("INSERT INTO user_roles (user_name, role_name) VALUES (?,?) ON CONFLICT (user_name, role_name) DO NOTHING", user.getUsername(), rolename);
		}
	}
	
	public boolean isUsernameTaken(final String username)
	{
		return this.getUser(username) == null;
	}
	
	private class UserResultsSetHandler extends AbstractListHandler<User>
	{
		@Override
		protected User handleRow(final ResultSet rs) throws SQLException
		{
			final User.Builder userBuilder = new User.Builder(rs.getString("forename"), rs.getString("surname"));
			final QueryRunner roleRunner = UserCache.this.database.getQueryRunner();
			userBuilder.username(rs.getString("user_name"));
			userBuilder.digest(new Digest(rs.getString("user_pass")));
			userBuilder.id(UUID.fromString(rs.getString("user_id")));
			final List<String> roles = roleRunner.query(
					"SELECT role_name FROM user_roles WHERE user_name=?;",
					new ColumnListHandler<String>(), rs.getString("user_name"));
			userBuilder.role(UserRole.fromDatabaseValues(roles));
			// userBuilder.address(value); TODO add address getter.
			return userBuilder.build();
		}
	}
}
