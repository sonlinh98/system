package jsoft.ads.pc;

import java.sql.*;
import jsoft.*;
import jsoft.ads.pg.*;
import jsoft.objects.*;

public class PCImpl extends PGImpl implements PC {

	public PCImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addPC(PCObject item) {
		// TODO Auto-generated method stub

//		String sql = "INSERT INTO tblpc(";
//		sql += "pc_name, pc_pg_id, pc_ps_id, ";
//		sql += "pc_manager_id, pc_notes, pc_delete, pc_deleted_date, ";
//		sql += "pc_deleted_author, pc_modified_date, pc_created_date, pc_image, ";
//		sql += "pc_enable, pc_name_en, pc_created_author_id, pc_language) ";
//		sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";// Số param hiện tại là: 15
//		
//		try {
//			PreparedStatement pre = this.con.prepareStatement(sql);
//			
//			pre.setString(1, item.getPc_name());
//			pre.setString(2, item.getPc_pg_id());
//			pre.setString(3, item.getPc_ps_id());
//			
//			pre.setString(4, item.getPc_manager_id());
//			pre.setString(5, item.getPc_notes());
//			pre.setString(6, item.isPc_delete());
//			pre.setString(7, item.getPc_name());
//			
//			pre.setString(8, item.getPc_name());
//			pre.setString(9, item.getPc_name());
//			pre.setString(10, item.getPc_name());
//			pre.setString(11, item.getPc_name());
//			
//			pre.setString(12, item.getPc_name());
//			pre.setString(13, item.getPc_name());
//			pre.setString(14, item.getPc_name());
//			pre.setString(15, item.getPc_name());
			
			
//		} catch (SQLException ex) {
//			// TODO Auto-generated catch block
//			ex.printStackTrace();
//			try {
//				this.con.rollback();
//			} catch (SQLException ex1) {
//				// TODO Auto-generated catch block
//				ex1.printStackTrace();
//			}
//		}
		
		return false;
	}

	@Override
	public boolean editPC(PCObject item) {
		// TODO Auto-generated method stub

//		String sql = "UPDATE tblpc SET ";
//		sql += "pc_name = ?, pc_pg_id = ?, pc_ps_id = ?, pc_manager_id = ?, ";
//		sql += "pc_notes = ?, pc_delete = ?, pc_deleted_date = ?, pc_deleted_author = ?, ";
//		sql += "pc_modified_date = ?, pc_created_date = ?, pc_image = ?, pc_enable = ?, ";
//		sql += "WHERE pc_id = ?";// Số param hiện tại là: 16

		return false;
	}

	@Override
	public boolean delPC(PCObject item) {
		// TODO Auto-generated method stub
		
		if (!isEmpty(item)) {
			return false;
		}

		String sql = "DELETE FROM tblpc WHERE pc_id=?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, item.getPc_id());

			return this.del(pre);

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException ex1) {
				// TODO Auto-generated catch block
				ex1.printStackTrace();
			}
		}

		return false;
	}

	private boolean isEmpty(PCObject item) {
		boolean flag = true;

		String sql = "SELECT product_id FROM tblproduct WHERE product_pc_id=?";

		ResultSet rs = this.get(sql, item.getPc_id());
		
		if (rs!=null) {
			try {
				if (rs.next()) {
				
					flag = false;
					
				}
				
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public ResultSet getPC(int id) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblpc WHERE pc_id=?";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getPCs(PCObject similar, short at, byte total) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblpc ";
		sql += "LEFT JOIN tblpg ON pc_pg_id = pg_id ";
		sql += "LEFT JOIN tblps ON pc_ps_id = ps_id ";
		sql += "";
		sql += "ORDER BY pc_name ASC ";
		sql += "LIMIT " + at + ", " + total;

		return this.gets(sql);
	}

}
