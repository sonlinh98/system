package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.library.*;
import jsoft.*;
import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet(name = "userae", description = "tên chạy", urlPatterns = { "/user/ae" })
public class UserAE extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAE() {
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

		// Tìm id để sửa nếu có
		int id = Utilities.getIntParam(request, "id");

		String name = "", email = "";
		String fullname = "", address = "";
		String phone = "", mobile = "";
		String readonly = "";
		String birthday = "";
		String office = "";
		byte permis = 0;
		String lblSave = "Thêm mới";
		String roles = "";

		boolean isEdit = false;

		if (id > 0) {
			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

			// Tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			// Lấy đối tượng để chỉnh sửa
			UserObject user = uc.getUserObject(id);

			// Trả lại kết nối
			uc.releaseConnection();

			// Bóc tách thông tin để sửa chữa.
			if (user != null) {
				name = user.getUser_name();
				email = user.getUser_email();

				fullname = user.getUser_fullname();
				address = user.getUser_address();

				phone = user.getUser_homephone();
				mobile = user.getUser_mobilephone();

				birthday = user.getUser_birthday();
				office = user.getUser_officephone();

				permis = user.getUser_permission();

				roles = user.getUser_roles();

				readonly = "readonly";
				lblSave = "Cập nhật";

				isEdit = true;
			}
		}

		// Tải header
		RequestDispatcher h = request.getRequestDispatcher("/header");

		if (h != null) {
			h.include(request, response);
		}

		out.print("<div class=\"c10\">");
		out.print("<div class=\"view\">");
		out.print("<div class=\"c4 acenter\">");
		out.print("<a href=\"#\" ><img src=\"/sys/adimgs/icons8-plus-math.png\" /></a>");
		out.print("</div>");

		out.print("<div class=\"c8 aleft p-t-10\" >");
		out.print("<form name=\"frmsearch\" action=\"\" method=\"\" > ");
		out.print("<input type=\"text\" name=\"txtKeyword\" />");
		out.print("<input type=\"submit\" value=\"Tìm kiếm\" />");
		out.print("</form>");
		out.print("</div>");
		out.print("<div class=\"clr\" ></div>");
		out.print("</div>");

		// ----------------------------------------------------------------
		out.print("<div class=\"view m-t-20 m-b-40\">");

		out.print("<form name=\"frmUser\" action=\"\" method=\"\">");
		out.print("<div class=\"row heading\">");
		out.print("Thông tin tài khoản");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Tên tài khoản</span>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print("<input type=\"text\" name=\"txtAccName\" value=\"" + name + "\" " + readonly
				+ " placeholder=\"Nhập tên tài khoản (*)\" />");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Mật khẩu</span>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print("<input type=\"password\" name=\"txtAccPass\" placeholder=\"Nhập mật khẩu (*)\"/>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print("<input type=\"password\" name=\"txtAccPassConfirm\" placeholder=\"Nhập lại mật khẩu (*)\"/>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Hộp thư</span>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print(
				"<input type=\"text\" name=\"txtAccEmail\" value=\"" + email + "\" placeholder=\"Nhập hộp thư [*]\"/>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Quyền thực thi</span>");
		out.print("</div>");
		out.print("<div class=\"c3 fields\">");
		out.print("<script language=\"javascript\">");
		out.print("this.generatePermis(" + permis + ");");
		out.print("</script>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Vai trò</span>");
		out.print("</div>");
		out.print("<div class=\"c9 fields\">");
		// out.print("<script language=\"javascript\">");

		out.print(UserLibrary.generaRolesOfPermis(roles, permis));

		// out.print("</script>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("				");
		out.print("<div class=\"row heading\">");
		out.print("Thông tin cá nhân");
		out.print("</div>");
		out.print("				");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Tên đầy đủ</span>");
		out.print("</div>	");
		out.print("<div class=\"c3 fields\">");
		out.print("<input type=\"text\" name=\"txtFullname\" value=\"" + fullname + "\"/>");
		out.print("</div>");
		out.print("<div class=\"c2 labels\">");
		out.print("<span>Bí danh</span>");
		out.print("</div>	");
		out.print("<div class=\"c3 fields\">");
		out.print("<input type=\"text\" name=\"txtAlias\" />");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Ngày sinh</span>");
		out.print("</div>	");
		out.print("<div class=\"c3 fields\">");
		out.print("<input type=\"date\" name=\"txtBirthday\" value=\"" + birthday + "\"/>");
		out.print("</div>");
		out.print("<div class=\"c2 labels\">");
		out.print("<span>Giới tính</span>");
		out.print("</div>	");
		out.print("<div class=\"c3 fields\">");
		out.print("<select name=\"slcGender\">");
		out.print("<option value=\"0\">Nam</option>");
		out.print("<option value=\"1\">Nữ</option>");
		out.print("<option value=\"2\">Nam-->Nữ</option>");
		out.print("<option value=\"3\">Nữ-->Nam</option>");
		out.print("<option value=\"4\">Nam--</option>");
		out.print("<option value=\"5\">Nữ--</option>");
		out.print("<option value=\"6\" selected>---</option>");
		out.print("</select>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Địa chỉ</span>");
		out.print("</div>	");
		out.print("<div class=\"c6 fields\">");
		out.print("<input type=\"text\" name=\"txtAddress\" value=\"" + address + "\"/>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Điện thoại</span>");
		out.print("</div>	");
		out.print("<div class=\"c2 fields\">");
		out.print("<input type=\"text\" name=\"txtPhone\" value=\"" + phone + "\"/>");
		out.print("</div>");
		out.print("<div class=\"c1 labels\">");
		out.print("<span>Đi động</span>");
		out.print("</div>	");
		out.print("<div class=\"c2 fields\">");
		out.print("<input type=\"text\" name=\"txtPhone1\" value=\"" + mobile + "\"/>");
		out.print("</div>");
		out.print("<div class=\"c1 labels\">");
		out.print("<span>Văn phòng</span>");
		out.print("</div>	");
		out.print("<div class=\"c2 fields\">");
		out.print("<input type=\"text\" name=\"txtOffice\" value=\"" + office + "\"/>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Nghề nghiệp</span>");
		out.print("</div>	");
		out.print("<div class=\"c6 fields\">");
		out.print("<input type=\"text\" name=\"txtJobArea\"/>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Chi tiết</span>");
		out.print("</div>	");
		out.print("<div class=\"c8 fields\">");
		out.print("<textarea name=\"txtDetails\" id=\"txtDetails\"></textarea>");
		out.print("</div>");
		out.print("<script type=\"text/javascript\">");
		out.print("var editor = CKEDITOR.replace('txtDetails');");
		out.print("</script>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row heading\">");
		out.print("Thông tin cơ quan");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Cơ quan</span>");
		out.print("</div>");
		out.print("<div class=\"c6 fields\">");
		out.print("<input type=\"text\" name=\"txtDeparment\"/>");
		out.print("</div>	");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row\">");
		out.print("<div class=\"c3 labels\">");
		out.print("<span>Bản đồ</span>");
		out.print("</div>");
		out.print("<div class=\"c8 fields\">");
		out.print(
				"<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.4558164767463!2d105.73295331498707!3d21.05444938598446!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345457e292d5bf%3A0x20ac91c94d74439a!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBDw7RuZyBuZ2hp4buHcCBIw6AgTuG7mWk!5e0!3m2!1svi!2s!4v1555818974561!5m2!1svi!2s\" height=\"300\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");
		out.print("</div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		out.print("<div class=\"row buttons\">");
		out.print("<input type=\"button\" value=\"" + lblSave + "\" onclick=\"saveUser(this.form)\"/>");
		out.print("<input type=\"button\" value=\"Nhập lại\"/>");
		out.print("<input type=\"button\" value=\"Trở về\"/>");
		out.print("<input type=\"button\" value=\"Thoát\"/>");
		out.print("</div>");

		if (isEdit) {
			// Truyền id để thực hiện cập nhật sau khi sửa cho doPost
			// id được truyền theo cơ chế biến form ẩn
			out.print("<input type=\"hidden\" name=\"idForPost\" value=\"" + id + "\" />");
		}

		out.print("</form>");

		out.print("");
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

		// Xác định tập ký tự cần lấy
		request.setCharacterEncoding("UTF-8");

		// Tìm id để cập nhật nếu có
		int id = Utilities.getIntParam(request, "idForPost");

		// Lấy thông tin tài khoản (bắt buộc)
		String name = request.getParameter("txtAccName");
		String pass = request.getParameter("txtAccPass");
		String email = request.getParameter("txtAccEmail");

		if (name != null && pass != null && email != null) {
			if (!name.equalsIgnoreCase("") && !pass.equalsIgnoreCase("") && !email.equalsIgnoreCase("")) {

				// Lấy tiếp thông tin (không bắt buộc)
				String fullname = request.getParameter("txtFullname");
				String address = request.getParameter("txtAddress");
				String phone = request.getParameter("txtPhone");
				String mobile = request.getParameter("txtPhone1");
				String office = request.getParameter("txtOffice");
				String birthday = request.getParameter("txtBirthday");

//				String alias = request.getParameter("txtAlias");
//
//				String jobArea = request.getParameter("txtJobArea");
//				String details = request.getParameter("txtDetails");
//				String deparment = request.getParameter("txtDeparment");

				
				// TODO hoàn thành permis và roles
				String permiss = request.getParameter("slcAccPermis");
				String[] roleses = request.getParameterValues("chks");

				// Tạo đối tượng lưu trữ thông tin
				UserObject nUser = new UserObject();

				nUser.setUser_name(name);
				nUser.setUser_pass(pass);
				nUser.setUser_email(email);

				nUser.setUser_fullname(Utilities.encode(fullname));
				nUser.setUser_birthday(birthday);
				nUser.setUser_address(Utilities.encode(address));
				nUser.setUser_homephone(phone);
				nUser.setUser_mobilephone(mobile);
				nUser.setUser_officephone(office);

				//nUser.setUser_jobarea(jobArea);

				//nUser.setUser_notes(Utilities.encode(notes));

				if (permiss != null && !permiss.equalsIgnoreCase("")) {
					byte permis = Byte.parseByte(permiss);

					if (permis > 0 && permis <= 5) {
						nUser.setUser_permission(permis);
					}
				}

				String roles = "";
				if (roleses != null) {
					if (roleses.length == 11) {
						roles = "all";
					} else if (roleses.length > 0) {
						int i = 0;
						while (i < roleses.length - 1) {
							roles += roleses[i] + "@";
							i++;
						}
						roles += roleses[i];
					}
				}

				nUser.setUser_roles(roles);

				// Tìm bộ quản lý kết nối
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				UserControl uc = new UserControl(cp);
				if (cp == null) {
					getServletContext().setAttribute("CPool", uc.getCP());
				}

				// Thực hiện cập nhật
				boolean result;

				if (id > 0) {
					// Cập nhật
					nUser.setUser_id(id);
					result = uc.editUser(nUser);
				} else {
					// Thêm mới
					result = uc.addUser(nUser);
				}

				// Trả về kết nối
				uc.releaseConnection();

				if (result) {
					response.sendRedirect("/sys/user/view");
				} else {
					response.sendRedirect("/sys/user/ae?err=notok");
				}

			} else {
				response.sendRedirect("/sys/user/ae?err=value");
			}
		} else {
			response.sendRedirect("/sys/user/ae?err=param");
		}

	}

}
