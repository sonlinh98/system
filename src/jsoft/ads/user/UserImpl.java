package jsoft.ads.user;

import java.sql.*;
import jsoft.objects.UserObject;
import jsoft.*;
import jsoft.ads.basic.*;
import jsoft.ads.sql.*;

public class UserImpl extends BasicIpml implements User {

	public UserImpl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		super(cp, "User");
	}

	@Override
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub

		if (this.isExisted(item)) {
			return false;
		}

		String sql = "INSERT INTO tbluser(";
		sql += "user_name, user_pass, user_fullname, ";
		sql += "user_birthday, user_mobilephone, user_homephone, ";
		sql += "user_officephone, user_email, user_address, ";
		sql += "user_jobarea, user_job, user_position, ";
		sql += "user_applyyear, user_permission, ";
		sql += "user_notes, user_roles, user_logined";
		sql += ") ";
		sql += "VALUE(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		// Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_birthday());
			pre.setString(5, item.getUser_mobilephone());
			pre.setString(6, item.getUser_homephone());
			pre.setString(7, item.getUser_officephone());
			pre.setString(8, item.getUser_email());
			pre.setString(9, item.getUser_address());
			pre.setString(10, item.getUser_jobarea());
			pre.setString(11, item.getUser_job());
			pre.setString(12, item.getUser_position());
			pre.setShort(13, item.getUser_applyyear());
			pre.setByte(14, item.getUser_permission());
			pre.setString(15, item.getUser_notes());
			pre.setString(16, item.getUser_roles());
			pre.setShort(17, item.getUser_logined());

			// Lấy kết quả
			return this.add(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	private boolean isExisted(UserObject item) {

		boolean flag = false;

		String sql = "SELECT user_id FROM tbluser WHERE user_name='" + item.getUser_name() + "' ";

		ResultSet rs = this.gets(sql);

		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return flag;

	}

	@Override
	public boolean editUser(UserObject item) {
		// TODO Auto-generated method stub

		String sql = "UPDATE tbluser SET ";
		sql += "user_pass = md5(?), user_fullname = ?, ";
		sql += "user_birthday = ?, user_mobilephone = ?, user_homephone = ?, ";
		sql += "user_officephone = ?, user_email = ?, user_address = ?, ";
		sql += "user_jobarea = ?, user_job = ?, user_position = ?, ";
		sql += "user_applyyear = ?, user_permission = ?, ";
		sql += "user_notes = ?, user_roles = ? ";

		sql += "WHERE user_id = ?";

		// Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị
			pre.setString(1, item.getUser_pass());
			pre.setString(2, item.getUser_fullname());
			pre.setString(3, item.getUser_birthday());
			pre.setString(4, item.getUser_mobilephone());
			pre.setString(5, item.getUser_homephone());
			pre.setString(6, item.getUser_officephone());
			pre.setString(7, item.getUser_email());
			pre.setString(8, item.getUser_address());
			pre.setString(9, item.getUser_jobarea());
			pre.setString(10, item.getUser_job());
			pre.setString(11, item.getUser_position());
			pre.setShort(12, item.getUser_applyyear());
			pre.setByte(13, item.getUser_permission());
			pre.setString(14, item.getUser_notes());
			pre.setString(15, item.getUser_roles());

			pre.setInt(16, item.getUser_id());

			// Lấy kết quả
			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub

		if (this.checkRoles(item)) {
			return false;
		}

		String sql = "DELETE FROM tbluser WHERE user_id = ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, item.getUser_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	private boolean checkRoles(UserObject item) {
		boolean flag = false;

		String sql = "SELECT article_id FROM tblarticle WHERE article_author_name='" + item.getUser_name() + "' ";

		ResultSet rs = this.gets(sql);

		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return flag;
	}

	@Override
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbluser WHERE user_id=?";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tbluser WHERE (user_name=?) AND (user_pass=md5(?))";

		return this.get(sql, username, userpass);
	}

	@Override
	public ResultSet getUsers(UserObject similar, int at, byte total) {
		// TODO Auto-generated method stub

		String conds = MakeConditions.createConditions(similar);
		
		String sql = "SELECT * FROM tbluser ";
		if (!conds.equalsIgnoreCase("")) {			
			sql += "WHERE "+conds + " ";
		}
		sql += "ORDER BY user_name ASC ";
		sql += "LIMIT " + at + ", " + total;
		return this.gets(sql);
	}

//	public static void main(String[] args) {
//		// Tạo bộ quản lý kết nối
//		ConnectionPool cp = new ConnectionPoolImpl();
//
//		// Tạo đối tượng thực thi chức năng mức độ Interface
//		User u = new UserImpl(cp);
//
//		// Tạo đối tượng lưu trữ thông tin
//		UserObject nUser = new UserObject();
//		nUser.setUser_id(20);
//		nUser.setUser_name("admin");
//		nUser.setUser_pass("12345677");
//		nUser.setUser_email("123@bac.com");
//		// Thông tin bắt buộc
//		nUser.setUser_address("Ha Noi");
//		nUser.setUser_fullname("Chu Trong Son");
//
//		// Thao tác
//		boolean result = u.delUser(nUser);
//
//		// Kiểm tra thao tác
//		if (!result) {
//			System.out.println("\n\n-------KHONG THANH CONG-------\n\n");
//		}
//
//		// Lấy danh sách kết quả
//		ResultSet rs = u.getUsers(null, 0, (byte) 15);
//
//		// Duyệt tập kết quả
//		if (rs != null) {
//			try {
//				String row;
//				while (rs.next()) {
//					row = "ID: " + rs.getInt("user_id");
//					row += "\tNAME: " + rs.getString("user_name");
//					row += "\tFULLNAME: " + rs.getString("user_fullname");
//					row += "\tADDRESS: " + rs.getString("user_address");
//					row += "\tEMAIL: " + rs.getString("user_email");
//					System.out.println(row);
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}

}
