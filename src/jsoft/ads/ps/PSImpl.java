package jsoft.ads.ps;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicIpml;
import jsoft.objects.PSObject;

public class PSImpl extends BasicIpml implements PS {
	
	public PSImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addPS(PSObject item) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO tblps(";
		sql += "ps_name, ps_manager_id, ps_notes, ";
		sql += "ps_delete, ps_created_date, ps_deleted_date, ps_modified_date, ";
		sql += "ps_deleted_author, ps_table, ps_enable, ps_name_en, ";
		sql += "ps_created_author_id, ps_language) ";
		sql +="VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//Số param hiện tại là: 13
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setString(1, item.getPs_name());
			pre.setInt(2, item.getPs_manager_id());
			pre.setString(3, item.getPs_notes());
			
			pre.setBoolean(4, item.isPs_delete());
			pre.setString(5, item.getPs_created_date());
			pre.setString(6, item.getPs_deleted_date());
			pre.setString(7, item.getPs_modified_date());
			
			pre.setString(8, item.getPs_deleted_author());
			pre.setString(9, item.getPs_table());
			pre.setBoolean(10, item.isPs_enable());
			pre.setString(11, item.getPs_name_en());
			
			pre.setInt(12, item.getPs_created_author_id());
			pre.setByte(13, item.getPs_language());
			
			
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
	public boolean editPS(PSObject item) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE tblps SET ";
		sql += "ps_name = ?, ps_manager_id = ?, ps_notes = ?, ps_delete = ?, ";
		sql += "ps_deleted_date = ?, ps_modified_date = ?, ps_deleted_author = ?, ";
		sql += "ps_table = ?, ps_enable = ?, ps_name_en = ?, ps_created_author_id = ?, ";
		sql += "ps_language = ? ";
		sql += "WHERE ps_id = ?";// Số param hiện tại là: 13

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setString(1, item.getPs_name());
			pre.setInt(2, item.getPs_manager_id());
			pre.setString(3, item.getPs_notes());
			
			pre.setBoolean(4, item.isPs_delete());

			pre.setString(5, item.getPs_deleted_date());
			pre.setString(6, item.getPs_modified_date());
			
			pre.setString(7, item.getPs_deleted_author());
			pre.setString(8, item.getPs_table());
			pre.setBoolean(9, item.isPs_enable());
			pre.setString(10, item.getPs_name_en());
			
			pre.setInt(11, item.getPs_created_author_id());
			pre.setByte(12, item.getPs_language());
			
			pre.setByte(13, item.getPs_id());
			
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
	public boolean delPS(PSObject item) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM tblps WHERE ps_id = ?";
		
		if (!isEmpty(item)) {
			return false;
		}
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setByte(1, item.getPs_id());
			
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
	private boolean isEmpty(PSObject item) {
		boolean flag = true;
		
		String sql = "SELECT pg_id FROM tblpg WHERE pg_ps_id = ?";
		
		ResultSet rs = this.get(sql, item.getPs_id());
		
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
	public ResultSet getPS(byte id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblps WHERE ps_id=?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getPSs(PSObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblps ";
		sql += "";
		sql += "";
		sql += "ORDER BY ps_name ASC ";
		sql += "LIMIT "+at+", "+total;	
		
		return this.gets(sql);
	}

	
	public static void main(String[] args) {
		// Test chức năng
		PS ps = new PSImpl(null, "PS");
		
		PSObject pso = new PSObject();
		
		pso.setPs_name("test ps");
		
		
		pso.setPs_created_date("08-08-2019");
		
		pso.setPs_id((byte)8);
		
		if (!ps.delPS(pso)) {
			System.out.println("------------------KHONG THANH CONG--------------");
		}
		
		ResultSet rs = ps.getPSs(null, (short)0, (byte)10);
		
		if (rs!=null) {
			try {
				String result;
				while (rs.next()) {
					result = "PS_ID: "+rs.getString("ps_id");
					result += "\tPS_NAME: "+rs.getString("ps_name");
					result += "\tPS_CREATE_DATE: "+rs.getString("ps_created_date");
					System.out.println(result);
				}
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

	}
}
