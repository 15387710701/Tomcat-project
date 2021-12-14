package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.User;
import dao.UserDao;

public class LoginServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uName = request.getParameter("username");
		String uPass = request.getParameter("password");
		String code = request.getParameter("code");
		String saveCode = (String)request.getSession().getAttribute("code");
		if(!code.equalsIgnoreCase(saveCode)){
			response.sendRedirect("login.html");
			return;
		}
		/*String saveanswer = (String)request.getSession().getAttribute("answer");
		if(!answer.equalsIgnoreCase(saveanswer)){
			response.sendRedirect("login.html");
			return;
		}*/
		
		UserDao userdao = new UserDao();
		User user = userdao.login(uName, uPass);
		//System.out.println(user.getUsername());
		//System.out.println(user.getPassword());
		if(user!=null){
			request.getSession().setAttribute("currentUser", user);
			request.getRequestDispatcher("main.jsp").forward(request,response);
		}
		else{
			//System.out.println("µÇÂ¼Ê§°Ü");
			response.sendRedirect("login.html");
		}
			

		//System.out.println(uName+","+uPass);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
