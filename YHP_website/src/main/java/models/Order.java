package models;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import models.user.User;

/**
 * @author Matt Rayner
 */
public class Order
{
	private final UUID id;
	private final Map<Product, Integer> products;
	private final User user;
	private final Address address;
	
	/**
	 * Class Constructor.
	 *
	 * @param builder
	 */
	private Order(final Builder builder)
	{
		this(builder.id, builder.products, builder.user, builder.address);
	}
	
	/**
	 * Class Constructor.
	 *
	 * @param id
	 * @param products
	 * @param user
	 * @param address
	 */
	public Order(final UUID id, final Map<Product, Integer> products, final User user, final Address address)
	{
		super();
		this.id = (null != id ? id : UUID.randomUUID());
		this.products = new LinkedHashMap<>(products);
		this.user = user;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public UUID getId()
	{
		return this.id;
	}

	/**
	 * @return the products
	 */
	public Map<Product, Integer> getProducts()
	{
		return Collections.unmodifiableMap(this.products);
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return this.user;
	}

	/**
	 * @return the address
	 */
	public Address getAddress()
	{
		return this.address;
	}
	
	/**
	 * @author Matt Rayner
	 */
	public static class Builder
	{
		private UUID id;
		private final Map<Product, Integer> products = new LinkedHashMap<>();
		private User user;
		private Address address;
		
		/**
		 * Class Constructor.
		 */
		public Builder()
		{
			super();
		}
		
		/**
		 * Class Constructor.
		 *
		 * @param order
		 */
		public Builder(final Order order)
		{
			this.id = order.id;
			this.products.putAll(order.products);
			this.user = order.user;
			this.address = order.address;
		}
		
		/**
		 * @param value the id
		 * @return this Builder
		 */
		public Builder id(final UUID value)
		{
			this.id = value;
			return this;
		}
		
		/**
		 * @param product
		 * @param quantity
		 * @return this Builder
		 */
		public Builder addProduct(final Product product, final Integer quantity)
		{
			this.products.put(product, quantity);
			return this;
		}
		
		/**
		 * @param value the User
		 * @return this Builder
		 */
		public Builder user(final User value)
		{
			this.user = value;
			return this;
		}
		
		/**
		 * @param value the Address
		 * @return this Builder
		 */
		public Builder address(final Address value)
		{
			this.address = value;
			return this;
		}
		
		/**
		 * @return the Order
		 */
		public Order build()
		{
			return new Order(this);
		}
	}
}
