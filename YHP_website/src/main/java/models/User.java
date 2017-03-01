package models;

public class User extends Person
{
	
	private final Address address;
	
	private final UserRole role;
	
	/**
	 * Class Constructor.
	 */
	public User(final String forename, final String surname)
	{
		this(forename, surname, UserRole.getDefault());
	}
	
	/**
	 * Class Constructor.
	 *
	 * @param forename
	 * @param surname
	 * @param userRole
	 */
	public User(final String forename, final String surname, final UserRole userRole)
	{
		this(forename, surname, userRole, Address.NULL);
	}
	
	/**
	 * Class Constructor.
	 */
	public User(final String forename, final String surname, final UserRole userRole, final Address address)
	{
		super(forename, surname);
		this.address = address;
		this.role = userRole;
	}
	
	protected User(final Builder builder)
	{
		super(builder);
		this.address = builder.address;
		this.role = builder.role;
	}
	
	public Address getAddress()
	{
		return this.address;
	}
	
	public UserRole getRole()
	{
		return this.role;
	}
	
	public static class Builder extends Person.Builder
	{
		private Address address = Address.NULL;
		private UserRole role = UserRole.getDefault();
		
		public Builder(final String forename, final String surname)
		{
			super(forename, surname);
		}
		
		public Builder address(final Address value)
		{
			this.address = value;
			return this;
		}
		
		public Builder role(final UserRole value)
		{
			this.role = value;
			return this;
		}
		
		public User build()
		{
			return new User(this);
		}
		
		public User toUser()
		{
			return this.build();
		}
	}
}
