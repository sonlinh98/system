package jsoft.ads.pg;

import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.PGObject;

public class PGControl {

	private PGModel pgm;
	
	public PGControl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		this.pgm = new PGModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.pgm.getCP();
	}
	
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.pgm = null;
		System.gc();
	}

	public void releaseConnection() {
		this.pgm.releaseConnection();
	}
	
	
	//-----------------------------------------
	public boolean addPG(PGObject item) {
		return this.pgm.addPG(item);
	}
	
	public boolean editPG(PGObject item) {
		return this.pgm.editPG(item);
	}
	public boolean delPG(PGObject item) {
		return this.pgm.delPG(item);
	}
	
	
	//-------------------------------------------
	public PGObject getPGObject(byte id) {
		return this.pgm.getPGObject(id);
	}
	
	
	public ArrayList<PGObject> getPGObjects(PGObject similar, short page, byte total) {
		return this.pgm.getPGObjects(null, page, total);
	}
	
	
	//------------------------------------------
	
	public String viewPGs(PGObject similar, short page, byte total) {
		
		ArrayList<PGObject> items = this.pgm.getPGObjects(similar, page, total);
		
		return PGLibrary.viewPGs(items);
	}
	
	
}
