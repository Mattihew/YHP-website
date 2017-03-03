package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import models.user.UserRole;

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
		if (UserRole.ADMIN.isUserinRole(request))
		{
			//request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/account.jsp").forward(request, response);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
	{
		final JSONObject jsonUser = new JSONObject(req.getParameter("user"));
		
		if (!jsonUser.get("password").equals(jsonUser.get("password2")))
		{
			final JSONObject response = new JSONObject();
			response.put("error", "password mismatch");
			resp.getWriter().write(response.toString());
		}
		
		//add user and edit logic here
	}

}
