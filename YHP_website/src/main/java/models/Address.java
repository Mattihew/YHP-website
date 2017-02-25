package models;

public class Address
{
	private String building;
	private String street;
	private String city_town;
	private String area_code;
	private String country;

	
	//TODO might end up using this but instantiating null values? Means more granularity when adding stuff for a user
	@SuppressWarnings("unused")
	private Address()
	{

	}

	/**
	 * Class Constructor.
	 */
	public Address(String building, String street, String city_town, String area_code, String country)
	{
		this.building = building;
		this.street = street;
		this.city_town = city_town;
		this.area_code = area_code;
		this.country = country;
	}

	/**
	 * @return the building
	 */
	public String getBuilding()
	{
		return building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building)
	{
		this.building = building;
	}

	/**
	 * @return the street
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}

	/**
	 * @return the city_town
	 */
	public String getCity_town()
	{
		return city_town;
	}

	/**
	 * @param city_town the city_town to set
	 */
	public void setCity_town(String city_town)
	{
		this.city_town = city_town;
	}

	/**
	 * @return the area_code
	 */
	public String getArea_code()
	{
		return area_code;
	}

	/**
	 * @param area_code the area_code to set
	 */
	public void setArea_code(String area_code)
	{
		this.area_code = area_code;
	}

	/**
	 * @return the country
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}
}
