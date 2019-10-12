package jsoft.ads.basic;

import java.sql.*;
import jsoft.*;

public class BasicIpml implements Basic {

	// Bộ quản lý kết nối của riêng basic
	private ConnectionPool cp;

	// Kết nối mà basic sử dụng làm việc với cơ sở dữ liệu, được lấy từ cp ở trên.
	protected Connection con;

	// Đối tượng làm việc với Basic
	private String objectName;

	public BasicIpml(ConnectionPool cp, String objectName) {// Những tham số truyền vào phải là những tham số không phụ thuộc vào nhau. vì con được lấy từ cp nên không cần truyền vào.
															
		// Xác định đối tượng làm việc với basic.
		this.objectName = objectName;

		
		// Xác định đối tượng quản lý kết nối cho Basic.
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		// Xin kết nối từ bộ quản lý kết nối.
		try {
			this.con = this.cp.getConnection(this.objectName);

			// Kiểm tra trạng thái của kết nối
			if (this.con.getAutoCommit()) {
				// Hủy chế độ thực thi tự động của kết nối
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean exe(PreparedStatement pre) {

		if (pre != null) {
			// Thực thi cập nhật vào hệ thống
			try {
				int results = pre.executeUpdate();

				// Kiểm tra kết quả cập nhật
				if (results == 0) {
					this.con.rollback();
					return false;
				}

				// Xác nhận thực thi câu lệnh mysql.
				this.con.commit();
				return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// Quay lại trạng thái an toàn của kết nối
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				pre = null;
				System.gc();// gom rác.
			}
		}

		return false;
	}

	@Override
	public boolean add(PreparedStatement pre) {
		// TODO Auto-generated method stub
		// 3 Phương thức thêm sửa xóa tách ra để thống kê số lần thêm sửa xóa. Nếu xây dựng chức năng thống kê
		return this.exe(pre);
	}

	@Override
	public boolean edit(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean del(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public ResultSet get(String sql, int value) {
		// TODO Auto-generated method stub

		// Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			// Náº¿u tá»“n táº¡i tham sá»‘	
			if (value>0) {
				pre.setInt(1, value);
			}
			
			return pre.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// Quay lại trạng thái an toàn của kết nối.
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			System.gc();
		}

		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setString(1, name);
			pre.setString(2, pass);

			return pre.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			System.gc();
		}
		
		return null;
	}

	@Override
	public ResultSet gets(String sql) {
		// TODO Auto-generated method stub
		
		return this.get(sql, 0);
		
	}

	@Override
	public ResultSet[] gets(String[] sqls) {
		// TODO Auto-generated method stub
		
		ResultSet[] tmp = new ResultSet[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			tmp[i] = this.get(sqls[i], 0);
		}
		
		return tmp;
	}

	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
