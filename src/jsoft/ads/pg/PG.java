package jsoft.ads.pg;

import java.sql.*;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface PG extends ShareControl{

	public boolean addPG(PGObject item);
	public boolean editPG(PGObject item);
	public boolean delPG(PGObject item);

	
	public ResultSet getPG(byte id);
	public ResultSet getPGs(PGObject similar, short at, byte total);
}
