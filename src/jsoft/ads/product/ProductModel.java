package jsoft.ads.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.ProductObject;

public class ProductModel {

	private Product p;
	
	public ProductModel(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		this.p = new ProductImpl(cp);
	}
	
	public void finalize() {
		this.p = null;
		System.gc();
	}
	
	public ConnectionPool getCP() {
		return this.p.getCP();
	}
	
	public void releaseConnection() {
		this.p.releaseConnection();
	}
	
	//----------------------------------
	
	public boolean addProduct(ProductObject item) {
		return this.p.addProduct(item);
	}
	
	public boolean editProduct(ProductObject item) {
		return this.p.editProduct(item);
	}
	public boolean delProduct(ProductObject item) {
		return this.p.delProduct(item);
	}
	
	
	//------------------------------------
	
	public ProductObject getProduct(int id) {
		ProductObject item = null;
		
		ResultSet rs = this.p.getProduct(id);
		
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_total(rs.getShort("product_total"));
					
					item.setProduct_sold(rs.getShort("product_sold"));
					item.setProduct_notes(rs.getString("product_notes"));
					item.setProduct_image(rs.getString("product_image"));
					
					item.setPc_name(rs.getString("pc_name"));
					item.setPg_name(rs.getString("pg_name"));
					item.setPs_name(rs.getString("ps_name"));
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		
		return item;
	}
	
	public ArrayList<ProductObject> getProducts(ProductObject similar, short page, byte total) {
		
		ArrayList<ProductObject> items = new ArrayList<ProductObject>();
		
		ProductObject item = null;
		
		short at = (short) ((page - 1)*total);
		
		ResultSet rs = this.p.getProducts(similar, at, total);
		
		if (rs!=null) {
			try {
				while (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_total(rs.getShort("product_total"));
					
					item.setProduct_sold(rs.getShort("product_sold"));
					item.setProduct_notes(rs.getString("product_notes"));
					item.setProduct_image(rs.getString("product_image"));
					
					item.setPc_name(rs.getString("pc_name"));
					item.setPg_name(rs.getString("pg_name"));
					item.setPs_name(rs.getString("ps_name"));
					
					items.add(item);
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		
		return items;
	}
	
}
