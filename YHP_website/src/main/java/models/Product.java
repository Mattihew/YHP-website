package models;

import java.util.UUID;

public class Product
{
	private final UUID id;
	
	private final String name;
	
	private final String desc;
	
	private final String type;
	
	private final int price;
	
	private final int quantity;
	
	private final String imageURL;
	/**
	 * Class Constructor.
	 */
	public Product(final UUID id, final String name, final String desc, final String type, final int price, final int quantity, final String imageURL)
	{
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.imageURL = imageURL;
	}
	
	public UUID getId()
	{
		return this.id;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * @return the desc
	 */
	public String getDescription()
	{
		return this.desc;
	}
	/**
	 * @return the type
	 */
	public String getType()
	{
		return this.type;
	}
	/**
	 * @return the price
	 */
	public int getPricePennys()
	{
		return this.price;
	}
	
	public String getPriceString()
	{
		String result = Integer.toString(this.price);
		if (result.length() < 3)
		{
			result = result + "p";
		}
		else
		{
			result = "£" + result.substring(0, result.length() - 2) + "." + result.substring(result.length() - 2);
		}
		return result;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity()
	{
		return this.quantity;
	}
	/**
	 * @return the imageURL
	 */
	public String getImageURL()
	{
		return this.imageURL;
	}
	
}
