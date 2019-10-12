package jsoft.ads.user;


import java.sql.ResultSet;

import jsoft.ShareControl;
import jsoft.objects.*;


public interface User extends ShareControl{
	// Các chức năng cập nhật
	public boolean addUser(UserObject item);
	public boolean editUser(UserObject item);
	public boolean delUser(UserObject item);
	
	
	// Các chức năng lấy thông tin dữ liệu
	public ResultSet getUser(int id);
	public ResultSet getUser(String username, String userpass);
	public ResultSet getUsers(UserObject similar, int at, byte total);// lọc
	
	
}
