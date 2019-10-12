package jsoft.ads.account;


import java.sql.ResultSet;

import jsoft.objects.*;

public interface Account {
	// Các chức năng cập nhật
	public boolean addAccount(AccountObject item);
	public boolean editAccount(AccountObject item);
	public boolean delAccount(AccountObject item);
	
	// Các chức năng lấy dữ liệu
	public ResultSet getAccount(short id);
	public ResultSet getAccounts(AccountObject similar, short at, byte total);
}
