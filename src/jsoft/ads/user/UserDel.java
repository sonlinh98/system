package jsoft.ads.user;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.*;
import jsoft.objects.*;
import jsoft.library.*;

/**
 * Servlet implementation class View
 */
@WebServlet(name = "userdel", description = "tên chạy", urlPatterns = { "/user/del" })
public class UserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDel() {
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

		// Tìm id để xóa nếu có
		int id = Utilities.getIntParam(request, "id");

		if (id > 0 && id!=user.getUser_id()) {

			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

			// Tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			// Tạo đối tượng để xóa
			UserObject dUser = new UserObject();

			// Truyền id
			dUser.setUser_id(id);

			// Thực hiện xóa
			boolean result = uc.delUser(dUser);

			// Trả lại kết nối
			uc.releaseConnection();

			// Kiểm tra kết quả
			if (result) {
				response.sendRedirect("/sys/user/view");
			} else {
				response.sendRedirect("/sys/user/view?err=notok");
			}

		} else {
			response.sendRedirect("/sys/user/view?err=id");

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
