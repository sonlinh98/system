package jsoft.ads.category;


import java.sql.*;
import jsoft.*;
import jsoft.objects.*;

public interface Category extends ShareControl{
	// Các chức năng cập nhật
	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);
	
	// Các chức năng lấy dữ liệu
	public ResultSet getCategory(short id);
	public ResultSet getCategories(CategoryObject similar, short at, byte total);
}
