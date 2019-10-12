package jsoft.ads.pc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.PCObject;

public class PCModel {

	private PC pc;
	
	public PCModel(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		this.pc = new PCImpl(cp, "PC");
	}
	
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.pc = null;
		System.gc();
	}

	public ConnectionPool getCP() {
		return this.pc.getCP();
	}
	
	public void releaseConnection() {
		this.pc.releaseConnection();
	}
	
	//----------------------------
	
	public boolean addPC(PCObject item) {
		return this.pc.addPC(item);
	}
	
	public boolean editPC(PCObject item) {
		return this.pc.editPC(item);
	}
	
	public boolean delPC(PCObject item) {
		return this.pc.delPC(item);
	}
	//-----------------------------

	public PCObject getPCObject(int id) {
		PCObject item = null;
		
		ResultSet rs = this.pc.getPC(id);
		
		if (rs!=null) {
			try {
				if (rs.next()) {
					
					item = new PCObject();
					
					item.setPc_id(rs.getInt("pc_id"));
					item.setPc_name(rs.getString("pc_name"));
					item.setPc_created_date(rs.getString("pc_created_date"));
					
					item.setPg_name(rs.getString("pg_name"));
					item.setPs_name(rs.getString("ps_name"));
					item.setPc_notes(rs.getString("pc_notes"));
					
					
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return item;
	}
	
	public ArrayList<PCObject> getPCObjects(PCObject similar, short page, byte total) {
		
		ArrayList<PCObject> items = new ArrayList<PCObject>();
		
		PCObject item = null;
		
		short at = (short) ((page - 1)*total);
		
		ResultSet rs = this.pc.getPCs(similar, at, total);
		
		if (rs!=null) {
			try {
				while (rs.next()) {
					
					item = new PCObject();
					
					item.setPc_id(rs.getInt("pc_id"));
					item.setPc_name(rs.getString("pc_name"));
					item.setPc_created_date(rs.getString("pc_created_date"));
					
					item.setPg_name(rs.getString("pg_name"));
					item.setPs_name(rs.getString("ps_name"));
					item.setPc_notes(rs.getString("pc_notes"));
					
					items.add(item);
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return items;
	}
	
}
