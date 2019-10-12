package jsoft.ads.section;

import jsoft.*;
import jsoft.objects.*;
import java.util.*;

public class SectionControl {

	private SectionModel sm;

	public SectionControl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		this.sm = new SectionModel(cp);
	}

	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.sm = null;
		System.gc();
	}
	public ConnectionPool getCP() {
		return this.sm.getCP();
	}
	
	public void releaseConnection() {
		this.sm.releaseConnection();
	}

	// -----------------------------------------------------
	public boolean addSection(SectionObject item) {
		return sm.addSection(item);
	}

	public boolean editSection(SectionObject item) {
		return sm.editSection(item);
	}

	public boolean delSection(SectionObject item) {
		return sm.delSection(item);
	}

	// -----------------------------------------------------

	public SectionObject getSectionObject(short id) {
		return this.sm.getSectionObject(id);
	}


	// ----------------------------------------------------

	public String viewSections(SectionObject similar, short page, byte total) {
		// Lấy danh sách đối tượng
		ArrayList<SectionObject> items = this.sm.getSectionObjects(similar, page, total);

		// Trả về cấu trúc trình bày
		return SectionLibrary.viewSections(items);
	}

	
	public String viewManagerOptions(UserObject user) {
		
		ArrayList<UserObject> users = this.sm.getUsers(user);
		
		return SectionLibrary.viewManagerOptions(users);
		
	}
	
	public String viewItemOnPage(byte numberItem) {
		return SectionLibrary.viewItemOnPage(numberItem);
	}
	
	public String viewListItemPage(short pageCurrent, short total_record) {
		return SectionLibrary.showListButtonPages(pageCurrent, total_record);
	}
	
//	public static void main(String[] args) {
//		ConnectionPool cp = new ConnectionPoolImpl();
//
//		SectionControl uc = new SectionControl(cp);
//
//		String view = uc.viewSections(null, (short) 1, (byte) 15);
//		
//		uc.releaseConnection();
//		
//		System.out.println(view);
//	}

}
