package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsoft.ConnectionPool;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet(name = "userlogin", urlPatterns = { "/user/login" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Đây là phương thức thường cung cấp các cấu trúc html về trình khách Được
	 *      gọi: 1 - Thông qua URL, URI 2 - Thông qua sự kiện form: method="get"
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// request - Lưu trữ dữ liệu và các yêu cầu xử lý từ trình khách
		// response - Lưu trữ các trả lời gửi về trình khách

		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tìm tham số báo lỗi nếu có
		String error = request.getParameter("err");

		String mesage = "";

		if (error != null) {
			if (error.equalsIgnoreCase("param")) {
				mesage = "Tham số lấy giá trị không chính xác";
			} else if (error.equalsIgnoreCase("value")) {
				mesage = "Không tồn tại giá trị";
			} else if (error.equalsIgnoreCase("notok")) {
				mesage = "Đăng nhập không thành công";
			} else {
				mesage = "Không thành công";
			}
		}

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		out.print("<html>");
		out.print("<head>");
		out.print("<title>Đăng nhập</title>");
		out.print("<link href=\"/sys/adcss/login.css\" rel=\"stylesheet\" type=\"text/css\"/>");
		out.print("<script language=\"javascript\" src=\"/sys/adjs/login.js\"></script>");
		out.print("</head>");
		out.print("<body>");
		out.print("<!-- class -> . thừa kế -->");
		out.print("<!-- id -> # duy nhất và ko thừa kế -->");
		out.print("<div class=\"loginview\" id=\"loginview\">");

		if (!mesage.equalsIgnoreCase("")) {
			out.print("<div class=\"row heading\">");
			out.print("<div class=\"title\"><h1 style=\"color: red;\">" + mesage + "</h1></div>");
			out.print("</div>");
		}

		out.print("<form name=\"frmLogin\" method=\"\" action=\"\">");
		out.print("<div class=\"row heading\">");
		out.print("<div class=\"title\">Đăng nhập</div>");
		out.print("</div>");
		out.print("<div class=\"row elements\">");
		out.print("<div class=\"c40 labels\"><span>Tên đăng nhập</span></div>");
		out.print("<div class=\"c60 fields\"><input type=\"text\" name=\"txtAccName\"/></div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row elements\">");
		out.print("<div class=\"c40 labels\"><span>Mật khẩu</span></div>");
		out.print("<div class=\"c60 fields\"><input type=\"password\" name=\"txtAccPass\"/></div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row elements\">");
		out.print("<div class=\"c40 labels\">");
		out.print("<span>");
		out.print("<input type=\"checkbox\" id=\"chkSave\" name=\"chkAccSave\"/>");
		out.print("</span>");
		out.print("</div>");
		out.print("<div class=\"c60 fields\">");
		out.print("<label for=\"chkSave\">");
		out.print("Bạn có muốn lưu thông tin đăng nhập");
		out.print("</label>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"accounts\">");
		out.print("<a href=\"#\">Tạo tài khoản</a> &nbsp;&nbsp;|&nbsp;&nbsp;");
		out.print("<a href=\"#\">Quên mật khẩu</a> &nbsp;&nbsp;|&nbsp;&nbsp;");
		out.print("<a href=\"#\">Đăng nhập với Google</a>");
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"row buttons\">");
		out.print("<input type=\"button\" name=\"\" value=\"Đăng nhập\" onclick=\"login(this.form)\"/>");
		out.print("<input type=\"button\" value=\"Thoát\" name=\"\"/>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row aright\">");
		out.print("<a href=\"#\"> English</a>");
		out.print("</div>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");

		// Đóng luồng xuất
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Đây là phương thức được sử dụng để xử lý các thành phần dữ liệu do
	 *      doPOST truyền cho Được gọi: 1 - Thông qua sự kiện form: method="post"
	 * 
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Lấy thông tin đăng nhập trên giao diện
		String username = request.getParameter("txtAccName");
		String userpass = request.getParameter("txtAccPass");

		// Kiểm tra tham số
		if (username != null && userpass != null) {
			// Kiểm tra giá trị
			username = username.trim();
			userpass = userpass.trim();

			if (!username.equalsIgnoreCase("") && !userpass.equalsIgnoreCase("")) {
				// Tham chiếu ngữ cảnh ứng dụng
				ServletContext application = getServletConfig().getServletContext();

				// Tìm bộ quản lý kết nối trong ngữ cảnh
				ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");

				// Tạo đối tượng thực thi chức năng
				UserControl uc = new UserControl(cp);

				if (cp == null) {
					// Hỏi xin tham chiếu bộ quản lý kêt nối
					application.setAttribute("CPool", uc.getCP());
				}

				// Thực hiện đăng nhập
				UserObject user = uc.getUserObject(username, userpass);

				// Trả lại kết nối
				uc.releaseConnection();

				// Kiểm tra đăng nhập
				if (user != null) {
					// Tham chiếu phiên làm việc của từng trình khách
					HttpSession session = request.getSession(true);// tạo phiên lm việc mới

					// Đưa thông tin đăng nhập vào phiên
					session.setAttribute("userLogined", user);

					// Trở về giao diện chính
					response.sendRedirect("/sys/view");

				} else {
					response.sendRedirect("/sys/user/login?err=notok");
				}

			} else {
				response.sendRedirect("/sys/user/login?err=value");
			}

		} else {
			// Lỗi tham số
			response.sendRedirect("/sys/user/login?err=param");

		}

	}

}
