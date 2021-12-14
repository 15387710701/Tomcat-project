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

public class PermissionCheck implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		
		String url = req.getRequestURI().toString();
		
		if(url.endsWith("login.html") || 
				url.endsWith("loginServlet.do") || 
				url.endsWith("login1.html") || 
				url.endsWith("LoginAjax.do") || 
				url.endsWith("register.html") ||
				url.endsWith("register1.html") ||
				url.endsWith("CheckUserName.do") || 
				url.endsWith("RegisterAjax.do") || 
				url.endsWith("CheckUserPass.do") || 
				url.endsWith("CheckVeriUserPass.do") || 
				url.endsWith("queryProvince.do")||
				url.endsWith("queryCity.do")||
				url.endsWith("queryUser.do")||
				url.endsWith("logoutServlet.do")||url.endsWith("VCode.do"))
		{
			arg2.doFilter(arg0, arg1);
			return;
		}
		if(req.getSession().getAttribute("currentUser")==null){
			//System.out.println("not login");
			resp.sendRedirect("login1.html");
		}
		else{
			arg2.doFilter(arg0, arg1);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
