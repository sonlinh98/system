package jsoft.ads.category;

import java.util.ArrayList;

import jsoft.*;
import jsoft.objects.*;

public class CategoryControl {

	private CategoryModel cm;
	
	public CategoryControl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		cm = new CategoryModel(cp);
	}

	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.cm = null;
		System.gc();
	}
	
	public ConnectionPool getCP() {
		return this.cm.getCP();
	}
	
	public void releaseConnection() {
		this.cm.releaseConnection();
	}
	
	//-----------------------------------
	
	public boolean addCategory(CategoryObject item) {
		return cm.addCategory(item);
	}

	public boolean editCategory(CategoryObject item) {
		return cm.editCategory(item);
	}

	public boolean delCategory(CategoryObject item) {
		return cm.delCategory(item);
	}
	
	//----------------------------------

	public CategoryObject getCategoryObject(short id) {
		return this.cm.getCategoryObject(id);
	}

	// ----------------------------------------------------

	public String viewCategories(CategoryObject similar, short page, byte total) {
		// Lấy danh sách đối tượng
		ArrayList<CategoryObject> items = this.cm.getCategoryObjects(similar, page, total);

		// Trả về cấu trúc trình bày
		return CategoryLibrary.viewCategories(items);
	}
	
	
	public static void main(String[] args) {
		CategoryControl cc = new CategoryControl(null);
		
		String view = cc.viewCategories(null, (short)1, (byte)10);
		
		cc.releaseConnection();
		
		System.out.println(view);
		
	}
}
