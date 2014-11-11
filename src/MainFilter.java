

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class MainFilter
 */
@WebFilter("/*")
public class MainFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MainFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		// pass the request along the filter chain
		//chain.doFilter(request, response);
		String path = req.getRequestURI();
		System.out.println(path);

		if( path.contains("/user")){
			if( path.contains("/post")){
				if( path.contains("/comment")){
					req.getRequestDispatcher("/CommentServlet").forward(request, response);
					return;
				}
				req.getRequestDispatcher("/PostServlet").forward(request, response);
				return;
			}
			req.getRequestDispatcher("/UserServlet").forward(request, response);
			return;
		}
		if( path.contains("/login")){
			req.getRequestDispatcher("/LoginServlet").forward(request, response);
			return;
		}
		if( path.contains("/signin")){
			req.getRequestDispatcher("/SignInServlet").forward(request, response);
			return;
		}
		
		if (path.startsWith("/stylesheet")) {
		    // Just let container's default servlet do its job.
		    chain.doFilter(request, response);
		    return;
		}
		
		if( path.contains("/")){
			
			req.getRequestDispatcher("/welcome.html").forward(request, response);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
