package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.City;
import vo.Province;

import com.google.gson.Gson;

import dao.UserDao;

public class QueryCity extends HttpServlet {

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


		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String provinceCode = request.getParameter("provinceCode");
		UserDao dao = new UserDao();
		
		ArrayList<City> list = dao.queryCity(provinceCode);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		out.print(jsonStr);
		
		
		out.flush();
		out.close();
	}

}
