package jsoft.ads.customer;


import java.sql.*;
import jsoft.objects.*;

public interface Customer {
	// Các chức năng cập nhật
	public boolean addCustomer(CustomerObject item);
	public boolean editCustomer(CustomerObject item);
	public boolean delCustomer(CustomerObject item);
	
	// Các chức năng lấy dữ liệu
	public ResultSet getCustomer(short id);
	public ResultSet getCustomers(CustomerObject similar, short at, byte total);
}
