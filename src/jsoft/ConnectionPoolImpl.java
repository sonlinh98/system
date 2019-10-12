package jsoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

import java.sql.SQLException;

public class ConnectionPoolImpl implements ConnectionPool {

	// Trình điều khiển làm việc với mysql
	private String driver;

	// Đường dẫn thực thi của mysql
	private String url;

	// Tài khoản làm việc với CSDL mysql
	private String userName;
	private String userPass;

	// Khai báo đối tượng lưu trữ kết nối.
	private Stack<Connection> pool;

	public ConnectionPoolImpl() {
		// Xác định trình điều khiển
		this.driver = "com.mysql.jdbc.Driver";

		// Nạp trình điều khiển
		this.loadDriver();

		// Xác định đường dẫn thực thi mysql
		this.url = "jdbc:mysql://localhost:3306/jp1901_data";

		// Xác định tài khoản làm việc
		this.userName = "son1";
		this.userPass = "123";

		// Khởi tạo bộ nhớ đối tượng lưu trữ kết nối.
		this.pool = new Stack<>();
	}

	private void loadDriver() {
		try {
			Class.forName(this.driver).newInstance();
		} catch (InstantiationException e) {// Lỗi khởi tạo
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {// Lỗi truy cập bất hợp pháp
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		// Kiểm tra
		if(this.pool.isEmpty()) {
			// Khởi tạo kết nối mới
			System.out.println(objectName+"Have created a new Connection.");
			return DriverManager.getConnection(this.url, this.userName, this.userPass);
		}else {
			// Lấy kết nối được lưu trữ trong bộ quản lý kết nối
			System.out.println(objectName+"Have popped the Connection.");			
			return (Connection)this.pool.pop();
		}
		
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub

		// Yêu cầu đối tượng phải trả lại kết nối
		System.out.println(objectName+"have pushed the Connection.");
		this.pool.push(con);
	}

}
