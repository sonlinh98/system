package jsoft.ads.user;

import jsoft.*;
import jsoft.objects.*;
import java.util.*;

public class UserControl {

	private UserModel um;
	
	public UserControl(ConnectionPool cp){
		this.um = new UserModel(cp);
	}
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.um = null;
		System.gc();
	}

	public ConnectionPool getCP() {
		return this.um.getCP();
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}
	
	//-----------------------------------------------------
	public boolean addUser(UserObject item) {
		return um.addUser(item);
	}
	
	public boolean editUser(UserObject item) {
		return um.editUser(item);
	}
	
	public boolean delUser(UserObject item) {
		return um.delUser(item);
	}
	
	//-----------------------------------------------------
	
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	
	public UserObject getUserObject(String username, String userpass) {
		return this.um.getUserObject(username, userpass);
	}
	
	//----------------------------------------------------
	
	public String viewUsers(UserObject similar, short page, byte total, UserObject user) {
		// Lấy danh sách đối tượng
		ArrayList<UserObject> items = this.um.getUserObjects(similar, page, total);
		
		// Trả về cấu trúc trình bày
		return UserLibrary.viewUsers(items, user);
	}
	
	
//	public static void main(String[] args) {
//		ConnectionPool cp = new ConnectionPoolImpl();
//		
//		UserControl uc = new UserControl(cp);
//		
//		String view = uc.viewUsers(null, (short)1, (byte)15);
//		
//		// Trả về kết nối
//		uc.releaseConnection();
//		
//		System.out.println(view);
//	}
	
	
	
	
	
	
	
	
	
	
}
