package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Products
 */
public class ProductsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	/**
	 * Class Constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
    public ProductsServlet()
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
		request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
	}

}
