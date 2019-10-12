package jsoft.ads.account;

import java.sql.ResultSet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicIpml;
import jsoft.objects.AccountObject;

public class AccountImpl extends BasicIpml implements Account {

	public AccountImpl(ConnectionPool cp) {
		super(cp, "Account");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addAccount(AccountObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editAccount(AccountObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delAccount(AccountObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getAccount(short id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblaccount WHERE account_id=?";
		
		return this.get(sql, id);
	}

	@Override
	public ResultSet getAccounts(AccountObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblaccount ";
		sql += "";
		sql += "LIMIT "+at+", "+total;
		return this.gets(sql);
	}

}
