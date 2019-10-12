package jsoft.ads.section;

import java.sql.*;
import jsoft.*;
import jsoft.ads.basic.*;
import jsoft.objects.*;

/*
 * Danh mục
 */
public class SectionImpl extends BasicIpml implements Section {

	public SectionImpl(ConnectionPool cp, String objectName) {
		// TODO Auto-generated constructor stub
		super(cp, objectName);
	}

	@Override
	public boolean addSection(SectionObject item) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO tblsection ( ";
		sql += "section_name, section_notes, section_created_date, section_manager_id, ";
		sql += "section_enable, section_delete, ";
		sql += "section_last_modified, section_created_author_id, section_name_en, section_language";
		sql += ")";
		sql += "VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_id());
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			pre.setByte(10, item.getSection_language());

			// Trả về kết quả
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

	@Override
	public boolean editSection(SectionObject item) {
		// TODO Auto-generated method stub

		String sql = "UPDATE tblsection SET ";
		sql += "section_name=?, section_notes=?, section_manager_id=?, ";
		sql += "section_enable=?, section_delete=?, ";
		sql += "section_last_modified=?, section_name_en=?, section_language=? ";
		sql += "WHERE section_id=?";

		// Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());

			pre.setInt(3, item.getSection_id());
			pre.setBoolean(4, item.isSection_enable());
			pre.setBoolean(5, item.isSection_delete());
			pre.setString(6, item.getSection_last_modified());

			pre.setString(7, item.getSection_name_en());
			pre.setByte(8, item.getSection_language());

			pre.setInt(9, item.getSection_id());

			// Trả về kết quả.
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

	@Override
	public boolean delSection(SectionObject item) {
		// TODO Auto-generated method stub

		if (!this.isEmpty(item)) {
			return false;
		}

		String sql = "DELETE FROM tblsection WHERE section_id=?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setShort(1, item.getSection_id());

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

	private boolean isEmpty(SectionObject item) {
		boolean flag = true;

		String sql = "SELECT category_id FROM tblcategory WHERE category_section_id = ?";

		ResultSet rs = this.get(sql, item.getSection_id());

		if (rs != null) {
			try {
				if (rs.next()) {
					flag = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public ResultSet getSection(short id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblsection WHERE section_id=?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getSections(SectionObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblsection ";
		sql += "";
		sql += "ORDER BY section_name ASC ";
		sql += "LIMIT " + at + ", " + total;
		return this.gets(sql);
	}

	public static void main(String[] args) {
		// Tạo đối tượng thực thi chức năng.
//		Section s = new SectionImpl(null, "Section");

	}

	@Override
	public ResultSet getUsers(UserObject user) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT user_id, user_name, user_fullname FROM tbluser ";
		sql += "WHERE user_permission<=?";
		
		return this.get(sql, user.getUser_permission());
	}

}
