package filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.FilterBase;
import org.apache.juli.logging.Log;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends FilterBase
{

    /**
     * Default constructor.
     */
    public LoginFilter()
    {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(final FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException
	{
		if (request instanceof HttpServletRequest && Boolean.parseBoolean(request.getParameter("auth")))
		{
			final HttpServletRequest httpRequest = (HttpServletRequest) request;
			if (response instanceof HttpServletResponse && httpRequest.getUserPrincipal() == null)
			{
				httpRequest.authenticate((HttpServletResponse) response);
				return;
			}
		}
		chain.doFilter(request, response);
	}


	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected Log getLogger()
	{
		return null;
	}
}
