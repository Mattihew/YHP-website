package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import models.user.User;
import models.user.UserRole;
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
		String userid = request.getParameter("user");
		if (userid == null)
		{
			userid = UserCache.getInstance().getUser(request).getUuid().toString();
		}
		else if (!UserRole.ADMIN.isUserinRole(request))
		{
			User user = UserCache.getInstance().getUser(request);
			if (!user.getUuid().toString().equals(userid))
			{
				//user trying to view other user.
				response.sendError(403, "Access Denied");
			}
		}
		request.getRequestDispatcher("/WEB-INF/pages/account.jsp?user=" + userid).forward(request, response);
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
		final JSONObject response = new JSONObject();
		try
		{
			UserCache.getInstance().putUser(new User.Builder(jsonUser).build());
		}
		catch (NoSuchAlgorithmException | SQLException e)
		{
			response.put("error", "an error happened");
			e.printStackTrace();
		}
		resp.getWriter().write(response.toString());
	}

}
