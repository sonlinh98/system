package jsoft.ads.section;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class View
 */
@WebServlet(name = "sectionview", description = "tên chạy", urlPatterns = { "/section/view" })
public class SectionView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SectionView() {
		super();
		// TODO Auto-generated constrsctor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

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

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		// Tạo đối tượng thực thi chức năng
		SectionControl sc = new SectionControl(cp);

		short pageCurrent = 1;

		short pageParam = Utilities.getShortParam(request, "page");

		if (pageParam != -1) {
			pageCurrent = pageParam;
		}

		if (cp == null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}

		// Lấy cấu trúc html
		String view = sc.viewSections(null, pageCurrent, (byte)15);

		// Trả lại kết nối
		sc.releaseConnection();

		// Tải header
		RequestDispatcher h = request.getRequestDispatcher("/header");

		if (h != null) {
			h.include(request, response);
		}

		out.print("<div class=\"c10\">");
		out.print("<div class=\"view\">");
		out.print("<div class=\"c4 acenter\">");
		out.print("<a href=\"/sys/section/ae\" ><img src=\"/sys/adimgs/icons8-plus-math.png\" /></a>");
		out.print("</div>");

		out.print("<div class=\"c8 aleft p-t-10\" >");
		out.print("<form name=\"frmsearch\" action=\"\" method=\"\" > ");
		out.print("<input type=\"text\" name=\"txtKeyword\" />");
		out.print("<input type=\"submit\" value=\"Tìm kiếm\" />");
		out.print("</form>");
		out.print("</div>");
		out.print("<div class=\"clr\" ></div>");
		out.print("</div>");

		// ------------------------------------

		out.print("<div class=\"view m-t-20\">");
		out.print(view);
		out.print("");
		out.print("</div>");

		// ------------------------------
		out.print("<div class=\"view\">");
		out.print("<div class=\"c3 acenter\"></div>");
		out.print("<div class=\"c6 aleft p-t-10 p-b-10\">");
		out.print("");

		

		out.print("");
		out.print("<div class=\"wrapPageNumber\">");
		out.print("<div class=\"pageNumber m-l-20\">");
		out.print("");

		out.print(sc.viewListItemPage(pageCurrent));

		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("				</div>");
		out.print("<div class=\"c3 left p-t-10\"></div>");
		out.print("<div class=\"clr\"></div>");
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
