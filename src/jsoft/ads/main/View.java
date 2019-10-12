package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet(name = "view", description = "tên chạy", urlPatterns = { "/view" })
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public View() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession(false).getAttribute("userLogined");

		if (user != null) {
			view(request, response);

		} else {
			response.sendRedirect("/sys/user/login");
		}

	}

	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		Cookie cookie = new Cookie("viewNumberRow", "15");
		response.addCookie(cookie);
		
		// Tải header
		RequestDispatcher h = request.getRequestDispatcher("/header");

		if (h != null) {
			h.include(request, response);
		}

		out.print("<div class=\"c10\">");

		out.print("<div class=\"view\">");

		out.print("<canvas id=\"thongke\" ></canvas>");
		out.print("</div>");
		out.print("</div>");


		// Tải menu
		RequestDispatcher f = request.getRequestDispatcher("/footer");

		if (f != null) {
			f.include(request, response);
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
