package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.objects.UserObject;

/**
 * Servlet implementation class header
 */
@WebServlet("/header")
public class header extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public header() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		// Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		out.print("<html>");
		out.print("<head>");
		out.print("<title> Quản trị hệ thống</title>");
		out.print("<link href=\"/sys/adcss/layout.css\" rel=\"stylesheet\" type=\"text/css\"/>");
		out.print("<script language=\"\" src=\"/sys/adjs/user.js\"> </script>");
		out.print("<script src=\"https://cdn.jsdelivr.net/npm/chart.js@2.8.0\"></script>");
		out.print("<script language=\"\" src=\"/sys/ckeditor/ckeditor.js\"> </script>");
		
		out.print("</head>");
		out.print("<body>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c4\">");
		out.print("<div class=\"logo\"><img src=\"/sys/adimgs/Logo.png\" /></div>");
		out.print("</div>");
		out.print("<div class=\"c6\">");
		out.print("<div class=\"acc\">");
		out.print("<img src=\"/sys/adimgs/Admin.png\" />");
		out.print("<span>Quyền quản trị</span>");
		out.print("Tên đăng nhập:");
		out.print("<a href=\"#\">"+user.getUser_name()+" ("+user.getUser_fullname()+")</a>");
		out.print("&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;");
		out.print("<a href=\"#\">Đổi mật khẩu</a>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"c2\">");
		out.print("<div class=\"exit\">");
		out.print("<a href=\"/sys/user/logout\">");
		out.print("<img src=\"/sys/adimgs/logout1.png\"/>");
		out.print("</a>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");

		// Tải menu
		RequestDispatcher m = request.getRequestDispatcher("/menu");

		if (m != null) {
			m.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
