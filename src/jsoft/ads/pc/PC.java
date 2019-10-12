package jsoft.ads.pc;

import java.sql.*;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface PC extends ShareControl{
	public boolean addPC(PCObject item);
	public boolean editPC(PCObject item);
	public boolean delPC(PCObject item);

	public ResultSet getPC(int id);
	public ResultSet getPCs(PCObject similar, short at, byte total);
	
}
