package jsoft.ads.category;

import jsoft.*;// Sử dụng ConnectionPool
import jsoft.objects.*;// Sử dụng đối tượng CatagoryObject
import java.sql.*;// Các giao tiếp làm việc với JDBC
import java.util.*;// Sử dụng ArrayList

public class CategoryModel {

	private Category c;

	public CategoryModel(ConnectionPool cp) {
		c = new CategoryImpl(cp, "Category");
	}
	
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.c = null;
		System.gc();
	}
	
	public ConnectionPool getCP() {
		return this.c.getCP();
	}
	
	public void releaseConnection() {
		this.c.releaseConnection();
	}
	
	//--------------------------------------------
	
	public boolean addCategory(CategoryObject item) {
		return this.c.addCategory(item);
	}
	
	
	public boolean editCategory(CategoryObject item) {
		return this.c.editCategory(item);
	}
	
	public boolean delCategory(CategoryObject item) {
		return this.c.delCategory(item);
	}
	
	//---------------------------------------------
	
	
	public CategoryObject getCategoryObject(short id) {
		CategoryObject item = null;
		
		ResultSet rs = this.c.getCategory(id);
		
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new CategoryObject();
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_image(rs.getString("category_image"));
					
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		
		return item;
	}
	
	public ArrayList<CategoryObject> getCategoryObjects(CategoryObject similar, short page, byte total) {
		
		ArrayList<CategoryObject> items = new ArrayList<CategoryObject>();
		
		CategoryObject item = null;
		
		short at = (short) ((page-1)*total);
		
		ResultSet rs = this.c.getCategories(similar, at, total);
		
		if (rs!=null) {
			try {
				while (rs.next()) {
					item = new CategoryObject();
					
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_image(rs.getString("category_image"));
					
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					
					items.add(item);
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		
		return items;
	}
	
}
