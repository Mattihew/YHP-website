package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage.Database;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * Class Constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet()
	{
		super();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
	{
		final String action = request.getParameter("action");
		if (action != null)
		{
			switch (action)
			{
				case "dropUsers":
					Database.getInstance().execute("DROP TABLE users");
					break;
				case "createUsers":
					Database.getInstance().execute("CREATE TABLE public.users(id integer NOT NULL, name character varying(20))");
					break;
				default:
					break;
			}
		}
		request.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
	}
}
