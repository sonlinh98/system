package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.*;
import jsoft.library.Utilities;
import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet(name = "userview", description = "tên chạy", urlPatterns = { "/user/view" })
public class UserView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserView() {
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
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		if (user != null) {
			view(request, response, user);

		} else {
			response.sendRedirect("/sys/user/login");
		}

	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		// Tạo đối tượng thực thi chức năng
		UserControl uc = new UserControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}

		// Tìm từ khóa nếu có
		String key = request.getParameter("txtKeyword");

		String saveKey = (key != null) ? key.trim() : "";

		// Tạo đối tượng bộ lọc
		UserObject similar = new UserObject();

		// Truyền quyền thực thi vào đối tượng bộ lọc
		similar.setUser_permission(user.getUser_permission());

		// Truyền từ khóa tìm kiếm vào name
		similar.setUser_name(Utilities.encode(saveKey));

		// Lấy cấu trúc html
		String view = uc.viewUsers(similar, (short) 1, (byte) 20, user);

		// Trả lại kết nối
		uc.releaseConnection();

		// Tải header
		RequestDispatcher h = request.getRequestDispatcher("/header");

		if (h != null) {
			h.include(request, response);
		}

		out.print("<div class=\"c10\">");
		out.print("<div class=\"view\">");
		out.print("<div class=\"c4 acenter\">");
		out.print("<a href=\"/sys/user/ae\" ><img src=\"/sys/adimgs/icons8-plus-math.png\" /></a>");
		out.print("</div>");

		out.print("<div class=\"c8 aleft p-t-10\" >");
		out.print("<form name=\"frmsearch\" action=\"/sys/user/view\" method=\"POST\" > ");
		out.print("<input type=\"text\" name=\"txtKeyword\" value=\"" + saveKey + "\" />");
		out.print("<input type=\"submit\" value=\"Tìm kiếm\" />");
		out.print("</form>");
		out.print("</div>");
		out.print("<div class=\"clr\" ></div>");
		out.print("</div>");

		// -------------------------
		out.print("<div class=\"view m-t-20\">");
		out.print(view);
		out.print("</div>");

		// -----------------------------------
		out.print("<div class=\"view\">");
		out.print("<div class=\"c3 acenter\"></div>");
		out.print("<div class=\"c6 aleft p-t-10 p-b-10\">");
		out.print("");
		out.print("<div class=\"wrapPageNumber\">");
		out.print("<span>Hiển thị </span>");
		out.print("<select type=\"text\" value=\"5\">");
		out.print("<option value=\"1\">5</option>");
		out.print("<option value=\"2\">10</option>");
		out.print("<option value=\"3\">15</option>");
		out.print("<option value=\"4\">20</option>");
		out.print("</select>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"wrapPageNumber\">");
		out.print("<div class=\"pageNumber m-l-20\">");
		out.print("");
		out.print("<a href=\"#\"><img src=\"/sys/adimgs/icons8-prev-16.png\" /></a>");
		out.print("");
		out.print("<a href=\"#\"> 1</a>");
		out.print("<a href=\"#\" class=\"active\"> 2</a>");
		out.print("<a href=\"#\"> 3</a>");
		out.print("<a href=\"#\"> 4</a>");
		out.print("<a href=\"#\"> 5</a>");
		out.print("");
		out.print("<a href=\"#\"><img src=\"/sys/adimgs/icons8-end-16.png\"></a>");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("				</div>");
		out.print("<div class=\"c3 left p-t-10\"></div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");

		// --------------------------
		out.print("<div class=\"view m-t-20\">");
		out.print("<canvas id=\"myChart\"></canvas>");
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
