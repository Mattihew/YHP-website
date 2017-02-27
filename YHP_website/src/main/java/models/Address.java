package models;

public class Address
{
	private String building;
	private String street;
	private String city_town;
	private String area_code;
	private String country;

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
	 * @return the street
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * @return the city_town
	 */
	public String getCity_town()
	{
		return city_town;
	}

	/**
	 * @return the area_code
	 */
	public String getArea_code()
	{
		return area_code;
	}

	/**
	 * @return the country
	 */
	public String getCountry()
	{
		return country;
	}
}
