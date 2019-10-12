package jsoft.ads.section;


import java.sql.*;
import jsoft.*;
import jsoft.objects.*;

public interface Section extends ShareControl{
	// Các chức năng cập nhật
	public boolean addSection(SectionObject item);
	public boolean editSection(SectionObject item);
	public boolean delSection(SectionObject item);
	
	// Các chức năng lấy dữ liệu
	public ResultSet getSection(short id);
	public ResultSet getSections(SectionObject similar, short at, byte total);
	
	public ResultSet getUsers(UserObject user);
}
