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

import models.Product;

public class ProductCache
{
	private static ProductCache INSTANCE;
	private final Database database;
	private final Set<Product> products = new HashSet<>();

	public static ProductCache getInstance()
	{
		if (null == ProductCache.INSTANCE)
		{
			ProductCache.INSTANCE = new ProductCache(Database.getInstance());
		}
		
		return ProductCache.INSTANCE;
	}

	private ProductCache(Database database)
	{
		this.database = database;
	}
	
	public Product getProduct(final UUID id)
	{
		return this.getProducts(Column.PRODUCT_ID, id.toString()).get(0);
	}
	
	public Product getProduct(final String productName)
	{
		return this.getProducts(Column.NAME, productName).get(0);
	}

	private List<Product> getProducts(final Column column, final Object columnValue)
	{
		QueryRunner queryRunner = this.database.getQueryRunner();
		List<Product> foundProducts = null;
		
		try
		{
			foundProducts = queryRunner.query("SELECT * FROM products WHERE " + column + "=?;",
					new ProductResultsSetHandler(), columnValue);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if (null == foundProducts || foundProducts.isEmpty())
		{
			return null;
		}
		
		this.products.addAll(foundProducts);
		return foundProducts;
	}
	
	public void putProduct(final Product product) throws SQLException
	{
		AsyncQueryRunner run = this.database.getAsyncQueryRunner();
		
		run.update("INSERT INTO products (product_id, name, description, type, price, quantity, image_url) VALUES (?,?,?,?,?,?,?)"
				+ "ON CONFLICT (product_id) DO UPDATE SET name = EXCLUDED.name, description = EXCLUDED.description, type = EXCLUDED.type, price = EXCLUDED.price, quantity = EXCLUDED.quantity, image_url = EXCLUDED.image_urll",
		product.getId(), product.getName(), product.getDescription(), product.getType(), product.getPricePennys(), product.getQuantity(), product.getImageURL());
	}
	
	private class ProductResultsSetHandler extends AbstractListHandler<Product>
	{
		private ResultSet rs;
		
		private String getValue(final Column column) throws SQLException
		{
			return this.rs.getString(column.toString());
		}

		@Override
		protected Product handleRow(final ResultSet rs) throws SQLException
		{
			this.rs = rs;
			
			return new Product(
					UUID.fromString(this.getValue(Column.PRODUCT_ID)),
					this.getValue(Column.NAME),
					this.getValue(Column.DESCRIPTION), 
					this.getValue(Column.TYPE),
					Integer.parseInt(this.getValue(Column.PRICE)),
					Integer.parseInt(this.getValue(Column.QUANTITY)),
					this.getValue(Column.IMAGE_URL));
		}
	}

	private enum Column
	{
		PRODUCT_ID, NAME, DESCRIPTION, TYPE, PRICE, QUANTITY, IMAGE_URL;

		@Override
		public String toString()
		{
			return this.name().toLowerCase();
		}
	}
}
