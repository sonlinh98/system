package jsoft.ads.pg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.*;
import jsoft.objects.*;

public class PGModel {

	private PG pg;

	public PGModel(ConnectionPool cp) {
		pg = new PGImpl(cp, "PG");
	}
	
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.pg = null;
		System.gc();
	}

	public ConnectionPool getCP() {
		return this.pg.getCP();
	}
	
	public void releaseConnection() {
		this.pg.releaseConnection();
	}
	
	//------------------------------------------
	
	public boolean addPG(PGObject item) {
		return this.pg.addPG(item);
	}
	
	public boolean editPG(PGObject item) {
		return this.pg.editPG(item);
	}
	
	public boolean delPG(PGObject item) {
		return this.pg.delPG(item);
	}
	
	//------------------------------------------
	
	
	public PGObject getPGObject(byte id) {
		PGObject item = null;
		
		ResultSet rs = this.pg.getPG(id);
		
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new PGObject();
					
					item.setPg_id(rs.getByte("pg_id"));
					item.setPg_name(rs.getString("pg_name"));
					item.setPg_created_date(rs.getString("pg_created_date"));
					item.setPg_notes(rs.getString("pg_notes"));
					item.setPs_name(rs.getString("ps_name"));
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return item;
	}
	
	public ArrayList<PGObject> getPGObjects(PGObject similar, short page, byte total) {
		
		ArrayList<PGObject> items = new ArrayList<PGObject>();
		
		PGObject item = null;
		
		short at = (short) ((page -1)* total);
		
		
		ResultSet rs = this.pg.getPGs(similar, at, total);
		
		if (rs!=null) {
			try {
				while (rs.next()) {
					item = new PGObject();
					
					item.setPg_id(rs.getByte("pg_id"));
					item.setPg_name(rs.getString("pg_name"));
					item.setPg_created_date(rs.getString("pg_created_date"));
					item.setPg_notes(rs.getString("pg_notes"));
					item.setPs_name(rs.getString("ps_name"));
					
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
