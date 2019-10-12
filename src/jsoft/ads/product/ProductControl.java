package jsoft.ads.product;

import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.objects.ProductObject;

public class ProductControl {

	private ProductModel pm;
	
	public ProductControl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		this.pm = new ProductModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.pm.getCP();
	}
	
	public void releaseConnection() {
		this.pm.releaseConnection();
	}
	
	//---------------------------------------
	
	public boolean addProduct(ProductObject item) {
		return this.pm.addProduct(item);
	}
	
	public boolean editProduct(ProductObject item) {
		return this.pm.editProduct(item);
	}
	
	public boolean delProduct(ProductObject item) {
		return this.pm.delProduct(item);
	}
	
	//---------------------------------------
	
	public String viewProducts(ProductObject similar, short page, byte total) {
		
		ArrayList<ProductObject> items = this.pm.getProducts(similar, page, total);
		
		return ProductLibrary.viewProducts(items);
		
	}
	
	
}
