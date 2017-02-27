package models;

public class User extends Person
{
	
	private Address address;
	
	/**
	 * Class Constructor.
	 */
	public User(String forename, String surname)
	{
		this(forename, surname, null);
	}
	
	/**
	 * Class Constructor.
	 */
	public User(String forename, String surname, Address address)
	{
		super(forename, surname);
		
		this.address = address;
	}
	
	public Address getAddress()
	{
		return this.address;
	}
}
