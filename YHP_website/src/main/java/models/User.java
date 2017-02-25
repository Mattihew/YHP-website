package models;

public class User extends Person
{
	
	private Address address;
	
	/**
	 * Class Constructor.
	 */
	public User(String forename, String surname)
	{
		super(forename, surname);
	}
	
	/**
	 * Class Constructor.
	 */
	public User(String forename, String surname, Address address)
	{
		super(forename, surname);
		
		this.address = address;
	}
	
	public void addAddress()
	{
		//stub
	}
}
