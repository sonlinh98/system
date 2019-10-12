package jsoft.ads.user;

import jsoft.*;// Sử dụng ConnectionPool
import jsoft.objects.*;// Sử dụng đối tượng UserObject
import java.sql.*;// Các giao tiếp làm việc với JDBC
import java.util.*;// Sử dụng ArrayList

public class UserModel {

	private User u;

	public UserModel(ConnectionPool cp) {
		this.u = new UserImpl(cp);
	}
	
	
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.u = null;
		System.gc();
	}

	
	public ConnectionPool getCP() {
		return this.u.getCP();
	}
	
	public void releaseConnection() {
		this.u.releaseConnection();
	}
	
	//-----------------------------------------------------
	public boolean addUser(UserObject item) {
		return u.addUser(item);
	}
	
	public boolean editUser(UserObject item) {
		return u.editUser(item);
	}
	
	public boolean delUser(UserObject item) {
		return u.delUser(item);
	}
	
	//-----------------------------------------------------
	
	
	public UserObject getUserObject(int id) {
		UserObject item = null;
		
		ResultSet rs = this.u.getUser(id);
		
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_email(rs.getString("user_email"));
					
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_roles(rs.getString("user_roles"));
					
					item.setUser_permission(rs.getByte("user_permission"));
					
					item.setUser_notes(rs.getString("user_notes"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	public UserObject getUserObject(String user_name, String user_pass) {
		UserObject item = null;
		
		ResultSet rs = this.u.getUser(user_name, user_pass);
		
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_email(rs.getString("user_email"));
					
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_roles(rs.getString("user_roles"));
					
					item.setUser_permission(rs.getByte("user_permission"));
					
					item.setUser_notes(rs.getString("user_notes"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	public ArrayList<UserObject> getUserObjects(UserObject similar, short page, byte total) {
		
		ArrayList<UserObject> items = new ArrayList<>();
		
		UserObject item = null;
		
		int at = (page - 1) * total;
		
		ResultSet rs = this.u.getUsers(similar, at, total);
		
		if (rs!=null) {
			try {
				while (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_email(rs.getString("user_email"));
					
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_roles(rs.getString("user_roles"));
					
					item.setUser_permission(rs.getByte("user_permission"));
					
					item.setUser_notes(rs.getString("user_notes"));
					
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
	
	
}
