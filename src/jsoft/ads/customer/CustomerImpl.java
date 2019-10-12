package jsoft.ads.customer;

import java.sql.*;
import jsoft.*;
import jsoft.ads.basic.*;
import jsoft.objects.*;

public class CustomerImpl extends BasicIpml implements Customer {

	public CustomerImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public boolean editCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		
		
		
		return false;
	}

	@Override
	public boolean delCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public ResultSet getCustomer(short id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcustomer WHERE customer_id = ?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getCustomers(CustomerObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM tblcustomer ";
		sql += "";
		sql += "";
		sql += "";
		sql += "ORDER BY customer_fullname ASC ";
		sql += "LIMIT "+at+", "+total;
		
		return this.gets(sql);
	}

}
