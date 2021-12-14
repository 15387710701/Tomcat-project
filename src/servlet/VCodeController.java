package servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.CreateImage;

public class VCodeController extends HttpServlet {
	public static String answer="";
	/**
	 * Constructor of the object.
	 */
	public VCodeController() {
		super();
	}

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
		
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
		String code = createRandomString();
		request.getSession().setAttribute("code", answer);
		//request.getSession().setAttribute("code", code);
		//request.getSession().setAttribute("answer",answer);
		response.setContentType("img/jpeg");
		BufferedImage image = CreateImage.create(code);
		ServletOutputStream out = response.getOutputStream();
		//System.out.println("1111");
		ImageIO.write(image,"JPEG",out);
		out.flush();
		out.close();
		
	}
	public String createRandomString(){
		int ianswer=0;
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int num1 = random.nextInt(10);
		sb.append(Integer.toString(num1));
		
		String operator = "+-*/";
		int index = random.nextInt(operator.length());
		char doopera = operator.charAt(index);
		sb.append(doopera);
		
		int num2 = random.nextInt(10);
		while(doopera=='/' && num2==0){
			num2 = random.nextInt(10);
		}
		sb.append(Integer.toString(num2));
		sb.append("=");
		
		switch(doopera){
		case '+':ianswer = num1+num2;break;
		case '-':ianswer = num1-num2;break;
		case '*':ianswer = num1*num2;break;
		case '/':ianswer = num1/num2;break;
		}
		answer = (Integer.toString(ianswer));
		//System.out.println(answer);
		/*String code = "";
		String s = "123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<4;i++){
			Random random = new Random();
			int index = random.nextInt(s.length());
			sb.append(s.charAt(index));
		}*/
		
		return sb.toString();
	}
	public String getanswer(){
		return answer;
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

		doGet(request,response);
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
