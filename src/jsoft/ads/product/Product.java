package jsoft.ads.product;

import java.sql.ResultSet;

import jsoft.ShareControl;
import jsoft.objects.ProductObject;

public interface Product extends ShareControl{

	public boolean addProduct(ProductObject item);
	public boolean editProduct(ProductObject item);
	public boolean delProduct(ProductObject item);

	public ResultSet getProduct(int id);
	public ResultSet getProducts(ProductObject similar, short at, byte total);	
	
}
