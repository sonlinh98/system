package jsoft.ads.ps;

import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.PSObject;

public class PSControl {

	private PSModel psm;
	public PSControl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		this.psm = new PSModel(cp);
	}
	
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.psm = null;
		System.gc();
	}

	public ConnectionPool getCP() {
		return this.psm.getCP();
	}
	
	public void releaseConnection() {
		this.psm.releaseConnection();
	}
	
	//--------------------------------
	
	public boolean addPS(PSObject item) {
		return this.psm.addPS(item);
	}
	
	public boolean editPS(PSObject item) {
		return this.psm.editPS(item);
	}
	
	public boolean delPS(PSObject item) {
		return this.psm.delPS(item);
	}
	
	//--------------------------------

	public PSObject getPSObject(byte id) {
		return this.psm.getPSObject(id);
	}
	
	public ArrayList<PSObject> getPSObjects(PSObject similar, short page, byte total){
		return this.psm.getPSObjects(similar, page, total);
	}
	
	//--------------------------------
	
	public String viewPS(PSObject similar, short page, byte total) {
		
		ArrayList<PSObject> items = this.psm.getPSObjects(similar, page, total);
		
		return PSLibrary.viewPSs(items);
	}
}
