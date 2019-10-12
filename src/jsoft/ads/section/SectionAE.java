package jsoft.ads.section;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class SectionAE
 */
@WebServlet(name = "sectionae", urlPatterns = { "/section/ae" })
public class SectionAE extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SectionAE() {
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

		// Kiểm tra phiên làm việc
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

		// Xác định kiểu trả về cho trình khách
		response.setContentType(CONTENT_TYPE);

		String btnUpdate = "Thêm mới";

		String name = "";
		String nameEnglish = "";
		String notes = "";

		// Tìm id để sửa nếu có
		short id = Utilities.getShortParam(request, "id");

		boolean isEdit = false;

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		// Tạo đối tượng thực thi chức năng
		SectionControl sc = new SectionControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}

		String viewManagerOptions = sc.viewManagerOptions(user);
		
		if (id > 0) {

			// Lấy đối tượng để sửa
			SectionObject so = sc.getSectionObject(id);

			// Bóc tách thông tin để sửa
			if (so != null) {

				name = so.getSection_name();
				nameEnglish = so.getSection_name_en();
				notes = so.getSection_notes();

				btnUpdate = "Cập nhật";

			}

		}

		// Lấy đối tượng xuất nội dung trả về
		PrintWriter out = response.getWriter();

		// Lấy nội dung header
		RequestDispatcher header = request.getRequestDispatcher("/header");
		if (header != null) {
			header.include(request, response);
		}

		out.print("<div class=\"c10\">");

		out.print("<div class=\"view\">");
		out.print("<div class=\"c4 acenter\">");
		out.print("<a href=\"/sys/section/ae\" ><img src=\"/sys/adimgs/addAll.png\" /></a>");
		out.print("</div>");

		out.print("<div class=\"c8 aleft p-t-10\" >");
		out.print("<form name=\"frmsearch\" action=\"\" method=\"\" > ");
		out.print("<input type=\"text\" name=\"txtKeyword\" />");
		out.print("<input type=\"submit\" value=\"Tìm kiếm\" />");
		out.print("</form>");
		out.print("</div>");
		out.print("<div class=\"clr\" ></div>");
		out.print("</div>");

		// ----------------------------
		out.print("<div class=\"view m-t-20\">");
		out.print("<div class=\"sectionview\">");
		out.print("<form name=\"frmsection\">");
		out.print("<div class=\"row heading\">");
		out.print("Thông tin chuyên mục");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c4 labels\">");
		out.print("<span> Ngày cập nhật</span>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print("<input type=\"date\" name=\"txtSectionDateUpdate\" />");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");

		out.print("<div class=\"row\">");
		out.print("<div class=\"c4 labels\">");
		out.print("<span> Tên chuyên mục (*)</span>");
		out.print("</div>");
		out.print("<div class=\"c6 fields\">");
		out.print("<input type=\"text\" name=\"txtSectionName\" value=\"" + name + "\" />");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");

		out.print("<div class=\"row\">");
		out.print("<div class=\"c4 labels\">");
		out.print("<span> Tên chuyên mục tiếng anh</span>");
		out.print("</div>");
		out.print("<div class=\"c6 fields\">");
		out.print("<input type=\"text\" name=\"txtSectionNameEnglish\" value=\"" + nameEnglish + "\" />");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c4 labels\">");
		out.print("<span> Quản lý</span>");
		out.print("</div>");
		out.print("<div class=\"c4 fields\">");
		out.print(viewManagerOptions);
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c4 labels\">");
		out.print("<span> Mô tả</span>");
		out.print("</div>");
		out.print("<div class=\"c8 fields\">");
		out.print("<textarea name=\"txtSectionNotes\" rows=\"10\">" + notes + "</textarea>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c5 labels\">");
		out.print("<span>");
		out.print("<input type=\"checkbox\" id=\"chkEnable\" name=\"chkEnable\" />");
		out.print("</span>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print("<label for=\"chkEnable\">Hiển thị trên site hay không?</label>");
		out.print("</div>");
		out.print("<div class=\"c1 labels\">");
		out.print("<span>");
		out.print("<input type=\"checkbox\" id=\"chkDelete\" name=\"chkDelete\" />");
		out.print("</span>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print("<label for=\"chkDelete\">Đánh dấu xóa.</label>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row buttons\">");
		out.print("<input type=\"button\" value=\"" + btnUpdate + "\" name=\"btnAddSection\" />");
		out.print("<input type=\"button\" value=\"Thoát\" name=\"btnExit\" />");
		out.print("</div>");

		if (isEdit) {
			out.print("<input type=\"hidden\" value=\"" + id + "\" />");
		}

		out.print("</form>");
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");

		// Lấy footer của website
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Tìm id để cập nhật
		short id = Utilities.getShortParam(request, "id");

		// Lấy thông tin bắt buộc
		String name = request.getParameter("name");

		if (name != null) {
			if (!name.equalsIgnoreCase("")) {
				// Lấy thông tin không bắt buộc
				String nameEnglish = request.getParameter("name");
				String notes = request.getParameter("name");

				// Tạo đối tượng lưu trữ thông tin
				SectionObject sectionObject = new SectionObject();

				sectionObject.setSection_name(name);
				sectionObject.setSection_name_en(nameEnglish);
				sectionObject.setSection_notes(notes);

				// Tìm bộ quản lý kết nối
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

				// Tạo đối tượng thực thi chức năng
				SectionControl sc = new SectionControl(cp);

				if (cp == null) {
					getServletContext().setAttribute("CPool", sc.getCP());
				}

				boolean result;

				if (id > 0) {
					// Cập nhật dữ liệu
					sectionObject.setSection_id(id);
					result = sc.editSection(sectionObject);

				} else {
					// Thêm mới
					result = sc.addSection(sectionObject);
				}

				// Trả về kết nối
				sc.releaseConnection();

				if (result) {
					response.sendRedirect("/sys/section/view");
				} else {
					response.sendRedirect("/sys/section/ae?err=notOk");
				}

			} else {
				response.sendRedirect("/sys/section/ae?err=value");
			}

		} else {
			response.sendRedirect("/sys/section/ae?err=param");
		}

	}

}
