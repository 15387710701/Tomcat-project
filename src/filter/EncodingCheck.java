package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingCheck implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/*HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		
		String url = req.getRequestURI().toString();
		
		if(url.endsWith("login.html") ||  url.endsWith("logoutServlet.do") || url.endsWith("main.jsp") || url.endsWith("VCode.do"))
		{
			arg2.doFilter(arg0, arg1);
			return;
		}*/
		arg0.setCharacterEncoding("utf-8");
		//resp.setContentType("text/html;charset=utf-8");
		arg2.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
