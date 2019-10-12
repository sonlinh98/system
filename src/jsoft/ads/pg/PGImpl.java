package jsoft.ads.pg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.*;
import jsoft.ads.ps.*;
import jsoft.objects.*;

public class PGImpl extends PSImpl implements PG {

	public PGImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addPG(PGObject item) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO tblpg(";
		sql += "pg_name, pg_ps_id, pg_manager_id, ";
		sql += "pg_notes, pg_delete, pg_deleted_date, pg_deleted_author, ";
		sql += "pg_modified_date, pg_created_date, pg_enable, pg_name_en, ";
		sql += "pg_created_author_id, pg_language) ";
		sql +="VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//Số param hiện tại là: 13
		
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setString(1, item.getPg_name());
			pre.setByte(2, item.getPg_ps_id());
			pre.setInt(3, item.getPg_manager_id());
			
			pre.setString(4, item.getPg_notes());
			pre.setBoolean(5, item.isPg_delete());
			pre.setString(6, item.getPg_deleted_date());
			pre.setString(7, item.getPg_deleted_author());
			
			pre.setString(8, item.getPg_modified_date());
			pre.setString(9, item.getPg_created_date());
			pre.setBoolean(10, item.isPg_enable());
			pre.setString(11, item.getPg_name_en());
			
			pre.setInt(12, item.getPg_created_author_id());
			pre.setByte(13, item.getPg_language());
			
			return this.add(pre);
			
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

	@Override
	public boolean editPG(PGObject item) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE tblpg SET ";
		sql += "pg_name = ?, pg_ps_id = ?, pg_notes = ?, ";
		sql += "pg_delete = ?, pg_deleted_date = ?, pg_deleted_author = ?, pg_modified_date = ?, ";
		sql += "pg_enable = ?, pg_name_en = ?, pg_created_author_id = ?, ";
		sql += "pg_language = ? ";
		sql += "WHERE pg_id = ?";// Số param hiện tại là: 12
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setString(1, item.getPg_name());
			pre.setByte(2, item.getPg_ps_id());
			
			pre.setString(3, item.getPg_notes());
			pre.setBoolean(4, item.isPg_delete());
			pre.setString(5, item.getPg_deleted_date());
			pre.setString(6, item.getPg_deleted_author());
			
			pre.setString(7, item.getPg_modified_date());

			pre.setBoolean(8, item.isPg_enable());
			pre.setString(9, item.getPg_name_en());
			
			pre.setInt(10, item.getPg_created_author_id());
			pre.setByte(11, item.getPg_language());
			
			pre.setByte(12, item.getPg_id());
			
			return this.edit(pre);
			
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

	@Override
	public boolean delPG(PGObject item) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM tblpg WHERE pg_id=?";
		
		if (!isEmpty(item)) {
			return false;
		}
		
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setByte(1, item.getPg_id());
			
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
	
	private boolean isEmpty(PGObject item) {
		boolean flag = true;
		
		String sql = "SELECT pc_id FROM tblpc WHERE pc_pg_id=?";
		
		ResultSet rs = this.get(sql, item.getPg_id());
		
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
	public ResultSet getPG(byte id) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM tblpg WHERE pg_id=?";
		
		return this.get(sql, id);
	}

	@Override
	public ResultSet getPGs(PGObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM tblpg ";
		sql += "LEFT JOIN tblps ON pg_ps_id = ps_id ";
		sql += "";
		sql += "ORDER BY pg_name ASC ";
		sql += "LIMIT "+at+", "+total;
		
		return this.gets(sql);
	}

	
	public static void main(String[] args) {
		PG pg = new PGImpl(null, "PG");
		
		PGObject pgo = new PGObject();
		
		pgo.setPg_name("PG Test 1");
		
		pgo.setPg_ps_id((byte)3);
		
		pgo.setPg_id((byte)5);
		
		if (!pg.delPG(pgo)) {
			System.out.println("---------------KHONG THANH CONG------------");
		}
		
		ResultSet rs = pg.getPGs(null, (short)0, (byte)10);
		
		if (rs!=null) {
			try {
				String row;
				while (rs.next()) {
					row = "PG_ID: "+rs.getByte("pg_id");
					row += "\tPG_NAME: "+rs.getString("pg_name");
					row += "\tPG_PS_ID: "+rs.getByte("pg_ps_id");
					System.out.println(row);
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
}
