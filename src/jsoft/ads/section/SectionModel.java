package jsoft.ads.section;

import jsoft.*;// Sử dụng ConnectionPool
import jsoft.objects.*;// Sử dụng đối tượng SectionObject
import java.sql.*;// Các giao tiếp làm việc với JDBC
import java.util.*;// Sử dụng ArrayList

public class SectionModel {

	private Section s;

	public SectionModel(ConnectionPool cp) {
		this.s = new SectionImpl(cp, "Section");
	}

	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.s = null;
		System.gc();
	}

	public ConnectionPool getCP() {
		return this.s.getCP();
	}

	public void releaseConnection() {
		this.s.releaseConnection();
	}

	// --------------------------------------------

	public boolean addSection(SectionObject item) {
		return this.s.addSection(item);
	}

	public boolean editSection(SectionObject item) {
		return this.s.editSection(item);
	}

	public boolean delSection(SectionObject item) {
		return this.s.delSection(item);
	}
	// --------------------------------------------

	public SectionObject getSectionObject(short id) {
		SectionObject item = null;

		ResultSet rs = this.s.getSection(id);

		if (rs != null) {
			try {
				if (rs.next()) {
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_enable(rs.getBoolean("section_enable"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<SectionObject> getSectionObjects(SectionObject similar, short page, byte total) {

		ArrayList<SectionObject> items = new ArrayList<SectionObject>();

		SectionObject item = null;

		short at = (short) ((page - 1) * total);

		ResultSet rs = this.s.getSections(similar, at, total);

		if (rs != null) {
			try {
				while (rs.next()) {
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_enable(rs.getBoolean("section_enable"));

					items.add(item);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return items;
	}

	public ArrayList<UserObject> getUsers(UserObject user) {
		ArrayList<UserObject> users = new ArrayList<UserObject>();

		ResultSet rs = this.s.getUsers(user);

		UserObject item = null;

		if (rs != null) {
			try {
				while (rs.next()) {

					item = new UserObject();
					
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));

					users.add(item);
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

		return users;
	}

}
