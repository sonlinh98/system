package jsoft.ads.user;

import jsoft.objects.*;
import java.util.*;

public class UserLibrary {

	public UserLibrary() {
		// TODO Auto-generated constructor stub
	}

	public static String viewUsers(ArrayList<UserObject> items, UserObject user) {
		String tmp = "<table cellspacing=0 class=\"tblview\" >\n";

		tmp += "<tr>";
		tmp += "<th>STT</th>";
		tmp += "<th>Tên đăng nhập</th>";
		tmp += "<th>Tên đầy đủ</th>";
		tmp += "<th>Hộp thư</th>";
		tmp += "<th>Địa chỉ</th>";
		tmp += "<th>Điện thoại</th>";
		tmp += "<th>Đi động</th>";
		tmp += "<th colspan=2 >Thực hiện</th>";
		tmp += "<th>ID</th>";
		tmp += "</tr>";

		int NO = 0;

		for (UserObject item : items) {

			NO++;

			tmp += (NO % 2 == 0) ? "<tr class=\"EVEN\">" : "<tr>";
			tmp += "<td class=\"NO\" >" + NO + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getUser_name() + "</td>";
			tmp += "<td class=\"FULLNAME\" >" + item.getUser_fullname() + "</td>";
			tmp += "<td class=\"EMAIL\" >" + item.getUser_email() + "</td>";
			tmp += "<td class=\"ADDRESS\" >" + item.getUser_address() + "</td>";
			tmp += "<td class=\"PHONE\" >" + item.getUser_homephone() + "</td>";
			tmp += "<td class=\"MOBILE\" >" + item.getUser_mobilephone() + "</td>";
			tmp += "<td class=\"ED\" ><a href=\"/sys/user/ae?id=" + item.getUser_id() + "\"><img src=\"/sys/adimgs/icons8-edit-20.png\" /></a></td>";

			if (item.getUser_id() != user.getUser_id()) {
				tmp += "<td class=\"ED\" ><a href=\"javascript:confirmDel('/sys/user/del?id=" + item.getUser_id() + "'); void(0);\"><img src=\"/sys/adimgs/icons8-trash-can-20.png\" /></a></td>";
			} else {
				tmp += "<td class=\"ED\">---</td>";
			}

			tmp += "<td class=\"ID\" >" + item.getUser_id() + "</td>";

			tmp += "</tr>";
		}

		tmp += "";

		tmp += "</table>";

		return tmp;
	}

	public static String generaRolesOfPermis(String roleses, byte permis) {

		// Khai báo biến lưu trữ html
		String role = "";

		// Quyền thực thi không được phép sửa
		if (permis == 1 || permis == 4 || permis == 5 || permis == 0) {
			// Kiểm tra vai trò của quyền thực thi
			if (roleses == null) {
				role = generaRolesHtml("disabled", "");
			} else {
				role = generaRolesHtml("disabled", "checked");
			}
		}

		// Quyền thực thi được phép sửa
		else if (permis == 2 || permis == 3) {

			// Kiểm tra vai trò của quyền thực thi
			if (roleses==null) {
				role = generaRolesHtml("", "");
			}
			else if (roleses.equalsIgnoreCase("all")) {
				role = generaRolesHtml("", "checked");
			} else {

				// Những vai trò hiện tại của quyền thực thi
				String[] arrRoles = roleses.split("@");
				role = generaRolesHtml(arrRoles);
			}
		}

		return role;
	}

	private static String generaRolesHtml(String disabled, String checked) {

		// Danh sách vai trò
		String[] roles = new String[11];
		roles[0] = "Người sử dụng (User)";
		roles[1] = "Chuyên mục (Section)";
		roles[2] = "Thể loại (Category)";
		roles[3] = "Bài viết & Tin tức (Article)";
		roles[4] = "Hệ sản phẩm (Product System)";
		roles[5] = "Nhóm sản phẩm (Product Group)";
		roles[6] = "Loại sản phẩm (Product Category)";
		roles[7] = "Sản phẩm (Product)";
		roles[8] = "Hóa đơn (Oder)";
		roles[9] = "Khách hàng (Customer)";
		roles[10] = "Lịch sửa cập nhật (Log)";

		// Khai báo biến lưu trữ html
		String role = "";
		for (int i = 0; i < roles.length; i++) {
			if (i % 3 == 0) {
				role += "<div class=\"row roles\">";
			}

			role += "<div class=\"c4\">";
			role += "<input type=\"checkbox\" name='chks' value=\"" + i + "\" id=\"chk" + i + "\"" + disabled + "  "
					+ checked + "/>";
			role += "<label for=\"chk" + i + "\">Quản lý " + roles[i] + "</label>";
			role += "</div>";

			if (i % 3 == 2 || i == roles.length - 1) {
				role += "<div class=\"clr\"></div>";
				role += "</div>";
			}
		}
		return role;
	}

	private static String generaRolesHtml(String[] roleschecked) {

		// Danh sách vai trò
		String[] roles = new String[11];
		roles[0] = "Người sử dụng (User)";
		roles[1] = "Chuyên mục (Section)";
		roles[2] = "Thể loại (Category)";
		roles[3] = "Bài viết & Tin tức (Article)";
		roles[4] = "Hệ sản phẩm (Product System)";
		roles[5] = "Nhóm sản phẩm (Product Group)";
		roles[6] = "Loại sản phẩm (Product Category)";
		roles[7] = "Sản phẩm (Product)";
		roles[8] = "Hóa đơn (Oder)";
		roles[9] = "Khách hàng (Customer)";
		roles[10] = "Lịch sửa cập nhật (Log)";

		// Khai báo biến lưu trữ html
		String role = "";

		String checked = "";
		int j = 0;
		for (int i = 0; i < roles.length; i++) {
			if (i % 3 == 0) {
				role += "<div class=\"row roles\">";
			}


			if (j < roleschecked.length) {
			
				int temp = Integer.parseInt(roleschecked[j]);
				if (temp==i) {
					checked = "checked";
					j++;					
				}
			}

			role += "<div class=\"c4\">";
			role += "<input type=\"checkbox\" name='chks' value=\"" + i + "\" id=\"chk" + i + "\"  " + checked + "/>";
			role += "<label for=\"chk" + i + "\">Quản lý " + roles[i] + "</label>";
			role += "</div>";

			if (i % 3 == 2 || i == roles.length - 1) {
				role += "<div class=\"clr\"></div>";
				role += "</div>";
			}
			checked = "";
		}
		return role;
	}

}
