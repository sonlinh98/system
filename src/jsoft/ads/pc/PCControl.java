package jsoft.ads.pc;

import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.PCObject;

public class PCControl {

	private PCModel pcm;
	public PCControl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		this.pcm = new PCModel(cp);
	}
	protected void finalize() {
		// dọn dẹp bộ nhớ
		this.pcm = null;
		System.gc();
	}

	public ConnectionPool getCP() {
		return this.pcm.getCP();
	}
	
	public void releaseConnection() {
		this.pcm.releaseConnection();
	}
	
	
	//-------------------------------------
	public boolean addPC(PCObject item) {
		return this.pcm.addPC(item);
	}
	
	public boolean editPC(PCObject item) {
		return this.pcm.editPC(item);
	}
	
	public boolean delPC(PCObject item) {
		return this.pcm.delPC(item);
	}

	//--------------------------------------
	
	public String viewPCs(PCObject similar, short page, byte total) {
		
		ArrayList<PCObject> items = this.pcm.getPCObjects(similar, page, total);
		
		return PCLibrary.viewPCs(items);
	}

}
