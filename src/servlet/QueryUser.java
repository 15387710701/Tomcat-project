package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import vo.User;
import dao.UserDao;

public class QueryUser extends HttpServlet {

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
		//1.取请求数据
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String chrname = request.getParameter("chrname");
		String province = request.getParameter("province");
		String page = request.getParameter("page");
		String currentPage = request.getParameter("currentPage");
		//2.调用dao做处理
		UserDao dao = new UserDao();
		ArrayList<User> list = dao.queryUser(username, chrname, province, page, currentPage);
		int total = dao.total(username, chrname, province);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("result", list);
		//3.将结果转换json字符串
		String jsonStr = (new Gson()).toJson(map) ;
		//4.输出json字符串 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
		out.flush();
		out.close();
	}

}
