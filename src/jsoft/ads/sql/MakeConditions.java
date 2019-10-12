package jsoft.ads.sql;

import jsoft.objects.*;

// Chịu trách nhiệm tạo ra điều kiện cho mệnh đề where
public class MakeConditions {

	public MakeConditions() {
		// TODO Auto-generated constructor stub
	}

	public static String createConditions(UserObject similar) {
		String conds = "";

		if (similar != null) {
			// Lấy ra quyền thực thi của đối tượng đăng nhập
			byte permis = similar.getUser_permission();
			conds = "(user_permission<=" + permis + ")";
		}

		// Lấy từ khóa
		String key = similar.getUser_name();

		if (key != null && !key.equalsIgnoreCase("")) {
			conds += " AND (";
			conds += "(user_name LIKE '%" + key + "%') OR ";
			conds += "(user_fullname LIKE '%" + key + "%') OR ";
			conds += "(user_address LIKE '%" + key + "%') OR ";
			conds += "(user_email LIKE '%" + key + "%') OR ";
			conds += "(user_homephone LIKE '%" + key + "%') OR ";
			conds += "(user_mobilephone LIKE '%" + key + "%') OR ";
			conds += "(user_notes LIKE '%" + key + "%')";
			conds += ")";
		}

		return conds;
	}

}
