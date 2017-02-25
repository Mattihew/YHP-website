package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import storage.Database;

/**
 * Application Lifecycle Listener implementation class DatabaseCloser
 *
 */
public class DatabaseCloser implements ServletContextListener
{

	/**
	 * Default constructor.
	 */
	public DatabaseCloser()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(final ServletContextEvent sce)
	{
		Database.getInstance().dispose();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(final ServletContextEvent sce)
	{
		// TODO Auto-generated method stub
	}

}
