package jsoft.ads.ps;

import java.sql.*;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface PS extends ShareControl{

	public boolean addPS(PSObject item);
	public boolean editPS(PSObject item);
	public boolean delPS(PSObject item);

	public ResultSet getPS(byte id);
	public ResultSet getPSs(PSObject similar, short at, byte total);
}
