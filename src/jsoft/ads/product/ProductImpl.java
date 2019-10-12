package jsoft.ads.product;

import java.sql.ResultSet;

import jsoft.ConnectionPool;
import jsoft.ads.pc.PCImpl;
import jsoft.objects.ProductObject;

public class ProductImpl extends PCImpl implements Product {

	public ProductImpl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		super(cp, "Product");
	}
	
	@Override
	public boolean addProduct(ProductObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editProduct(ProductObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delProduct(ProductObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getProduct(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblproduct WHERE product_id=? ";
		sql+= "LEFT JOIN tblpc ON product_pc_id = pc_id ";
		sql += "LEFT JOIN tblpg ON pc_pg_id = pg_id ";
		sql += "LEFT JOIN tblps ON pc_ps_id = ps_id ";
		sql += "ORDER BY product_name ASC ";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getProducts(ProductObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM tblproduct ";
		
		sql += "";
		sql += "";
		sql += "";
		sql += "";
		sql += "";
		sql+= "LEFT JOIN tblpc ON product_pc_id = pc_id ";
		sql += "LEFT JOIN tblpg ON pc_pg_id = pg_id ";
		sql += "LEFT JOIN tblps ON pc_ps_id = ps_id ";
		sql += "ORDER BY product_name ASC ";
		sql += "LIMIT "+at+", "+total;
		
		return this.gets(sql);
	}

}
