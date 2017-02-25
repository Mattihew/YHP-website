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
		
		this.address = null;
	}
	
	/**
	 * Class Constructor.
	 */
	public User(String forename, String surname, Address address)
	{
		super(forename, surname);
		
		this.address = address;
	}
	
	public void addAddress(String building, String street, String city_town, String area_code, String country)
	{
		this.address = new Address(building, street, city_town, area_code, country);
	}
	
	public Address getAddress()
	{
		return this.address;
	}
}
