package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import vo.User;

/**
 * Servlet implementation class LoginAjax
 */
@WebServlet("/LoginAjax.do")
public class LoginAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String uName = request.getParameter("username");
		String uPass = request.getParameter("password");
		String code = request.getParameter("code");
		String saveCode = (String)request.getSession().getAttribute("code");
		if(!code.equalsIgnoreCase(saveCode)){
			out.print("1");
			out.flush();
			out.close();
			return;
		}
		UserDao dao = new UserDao();
		User user = dao.login(uName, uPass);
		if(user != null){
			request.getSession().setAttribute("currentUser", user);
			out.print("0");
			out.flush();
			out.close();
		}
		else{
			out.print("2");
			out.flush();
			out.close();
		}
	}

}
