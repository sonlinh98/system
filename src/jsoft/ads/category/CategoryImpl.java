package jsoft.ads.category;

import java.sql.*;

import jsoft.*;
import jsoft.ads.section.SectionImpl;
import jsoft.objects.*;

public class CategoryImpl extends SectionImpl implements Category {

	public CategoryImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO tblcategory (";
		sql += "category_name, category_section_id, category_notes, category_created_date, category_created_author_id, category_last_modified, ";
		sql += "category_manager_id, category_enable, category_delete, category_image, category_name_en, category_language";
		sql += ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, item.getCategory_name());
			pre.setInt(2, item.getCategory_section_id());
			pre.setString(3, item.getSection_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setShort(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setByte(12, item.getCategory_language());

			return this.add(pre);

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException ex1) {
				// TODO Auto-generated catch block
				ex1.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean editCategory(CategoryObject item) {
		// TODO Auto-generated method stub

		String sql = "UPDATE tblcategory SET ";
		sql += "category_name = ?, category_section_id = ?, category_notes = ?, ";
		sql += "category_manager_id = ?, category_enable = ?, ";
		sql += "category_delete = ?, category_image = ?, category_name_en = ?, category_language = ?";
		sql += " WHERE category_id = ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, item.getCategory_name());
			pre.setInt(2, item.getCategory_section_id());
			pre.setString(3, item.getSection_notes());

			pre.setInt(4, item.getCategory_manager_id());
			pre.setBoolean(5, item.isCategory_enable());
			pre.setBoolean(6, item.isCategory_delete());
			pre.setString(7, item.getCategory_image());
			pre.setString(8, item.getCategory_name_en());
			pre.setByte(9, item.getCategory_language());

			return this.edit(pre);

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException ex1) {
				// TODO Auto-generated catch block
				ex1.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean delCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		
		if (!isEmpty(item)) {
			return false;
		}
		
		String sql = "DELETE tblcategory WHERE category_id=?";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setShort(1, item.getCategory_id());
			
			this.del(pre);

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException ex1) {
				// TODO Auto-generated catch block
				ex1.printStackTrace();
			}
		}
		
		return false;
	}
	
	private boolean isEmpty(CategoryObject item) {
		boolean flag = true;
		
		String sql = "SELECT article_id FROM tblarticle WHERE article_category_id = ?";

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
	public ResultSet getCategory(short id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcategory ";
		sql += "LEFT JOIN tblsection on category_section_id = section_id ";
		sql += "WHERE category_id=?";
		return this.gets(sql);
	}

	@Override
	public ResultSet getCategories(CategoryObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcategory ";
		sql += "LEFT JOIN tblsection on category_section_id = section_id ";

		sql += "";

		sql += "ORDER BY category_name ASC ";
		sql += "LIMIT " + at + ", " + total;

		return this.gets(sql);
	}

}
