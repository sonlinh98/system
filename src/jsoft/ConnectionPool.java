package jsoft;

import java.sql.*;


/*
 * Kỹ thuật quản lý kết nối. Tái sử dụng và xử lý kết nối 
 * 
 * 
 */

public interface ConnectionPool {
	// Cung cấp kết nối cho đối tượng làm việc
	public Connection getConnection(String objectName) throws SQLException;
	
	// Yêu cầu đối tượng trả lại kết nối
	public void releaseConnection(Connection con, String objectName) throws SQLException;
	
}
