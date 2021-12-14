package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class RegisterAjax
 */
@WebServlet("/RegisterAjax.do")
public class RegisterAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String chrname = request.getParameter("chrname");
		String provinceCode = request.getParameter("provinceCode");
		String cityCode = request.getParameter("cityCode");
		
		UserDao dao = new UserDao();
		
		String info = "";
		//System.out.println("uuuu"+username);
		//System.out.println("ffff"+password);
		//System.out.println(chrname);
		/*System.out.println(username);
		System.out.println(password);
		System.out.println(chrname);
		System.out.println(provinceCode);
		System.out.println(cityCode);*/
		if(dao.insert(username, chrname, password,provinceCode,cityCode)){
			info = "0";
		}else{
			info = "1";
		}

		out.print(info);
		out.flush();
		out.close();
	}

}
