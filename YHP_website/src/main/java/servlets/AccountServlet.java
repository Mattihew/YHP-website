package servlets;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage.UserCache;

/**
 * Servlet implementation class AccountServlet
 */
public class AccountServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * Class Constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet()
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
		final Principal user = request.getUserPrincipal();
		final UserCache userCache = UserCache.getInstance();
		
		if (null == user)
		{
			request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
			System.out.println("new user");
		}
		else if (request.isUserInRole("admin"))
		{
			request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
			System.out.println("admin");
		}
		
		//if user id == parameter id
		//request.getRequestDispatcher("/").forward(request, response);
		
		//request.getRequestDispatcher("/WEB-INF/pages/account.jsp").forward(request, response);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
	{
		super.doPost(req, resp);
		
		//add user and edit logic here
	}

}
