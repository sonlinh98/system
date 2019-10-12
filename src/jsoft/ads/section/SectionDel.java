package jsoft.ads.section;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.*;
import jsoft.library.*;
import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet(name = "sectiondel", description = "tên chạy", urlPatterns = { "/section/del" })
public class SectionDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SectionDel() {
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
		short id = Utilities.getShortParam(request, "id");

		if (id > 0) {

			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

			// Tạo đối tượng thực thi chức năng
			SectionControl sc = new SectionControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", sc.getCP());
			}

			// Lấy thông tin người quản lý
			SectionObject sectionInfo = sc.getSectionObject(id);

			if (sectionInfo != null) {
				if (sectionInfo.getSection_manager_id() == user.getUser_id()) {
					// Tạo đối tượng để xóa
					SectionObject dSection = new SectionObject();

					// Truyền id
					dSection.setSection_id(id);

					// Thực hiện xóa
					boolean result = sc.delSection(dSection);

					// Kiểm tra kết quả
					if (result) {
						response.sendRedirect("/sys/user/view");
					} else {
						response.sendRedirect("/sys/user/view?err=notok");
					}
				} else {
					response.sendRedirect("/sys/user/view?err=notpermis");
				}
			} else {
				response.sendRedirect("/sys/user/view?err=notexist");
			}

			// Trả lại kết nối
			sc.releaseConnection();

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
