package jsoft.ads.ps;

import jsoft.*;
import jsoft.objects.*;
import java.sql.*;
import java.util.*;

public class PSModel {

	private PS ps;

	public PSModel(ConnectionPool cp) {
		ps = new PSImpl(cp, "PS");
	}
	
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.ps = null;
		System.gc();
	}

	public ConnectionPool getCP() {
		return this.ps.getCP();
	}
	
	public void releaseConnection() {
		this.ps.releaseConnection();
	}

	// -------------------------------------------

	public boolean addPS(PSObject item) {
		return this.ps.addPS(item);
	}

	public boolean editPS(PSObject item) {
		return this.ps.editPS(item);
	}

	public boolean delPS(PSObject item) {
		return this.ps.delPS(item);
	}

	// -------------------------------------------

	public PSObject getPSObject(byte id) {
		PSObject item = null;

		ResultSet rs = this.ps.getPS(id);

		if (rs != null) {
			try {
				if (rs.next()) {
					item = new PSObject();
					item.setPs_id(rs.getByte("ps_id"));
					item.setPs_name(rs.getString("ps_name"));
					item.setPs_notes(rs.getString("ps_notes"));
					item.setPs_created_date(rs.getString("ps_created_date"));
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<PSObject> getPSObjects(PSObject similar, short page, byte total) {
		
		ArrayList<PSObject> items = new ArrayList<PSObject>();
		
		PSObject item = null;
		
		short at = (short) ((page-1)*total);

		ResultSet rs = this.ps.getPSs(similar, at, total);

		if (rs != null) {
			try {
				while (rs.next()) {
					item = new PSObject();
					item.setPs_id(rs.getByte("ps_id"));
					item.setPs_name(rs.getString("ps_name"));
					item.setPs_notes(rs.getString("ps_notes"));
					
					item.setPs_created_date(rs.getString("ps_created_date"));
					
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
