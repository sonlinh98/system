package jsoft.ads.product;

import java.util.ArrayList;

import jsoft.objects.ProductObject;

public class ProductLibrary {

	public ProductLibrary() {
		// TODO Auto-generated constructor stub
	}

	public static String viewProducts(ArrayList<ProductObject> items) {
		String tmp ="<table cellspacing=0 class=\"tblview\"> ";
		
		tmp += "<tr>";
		tmp += "<th>STT</th>";
		tmp += "<th>Sản phẩm</th>";
		tmp += "<th>Số lượng</th>";
		tmp += "<th>Số lượng bán</th>";
		tmp += "<th>Giá bán</th>";
		tmp += "<th>Loại sản phẩm</th>";
		tmp += "<th>Nhóm sản phẩm</th>";
		tmp += "<th>Hệ sản phẩm</th>";
		tmp += "<th colspan=\"2\">Thực hiện</th>";
		tmp += "<th>ID</th>";
		tmp += "</tr>";
		
		int NO = 1;
		for (ProductObject item : items) {
			
			tmp += (NO%2==0) ? "<tr class=\"EVEN\">" : "<tr>";
			tmp += "<td class=\"NO\">"+NO+"</td>";
			tmp += "<td class=\"NAME\">"+item.getProduct_name()+"<img src=\""+item.getProduct_image()+"\" />"+"</td>";
			tmp += "<td class=\"\">"+item.getProduct_total()+"</td>";
			tmp += "<td class=\"\">"+item.getProduct_sold()+"</td>";
			tmp += "<td class=\"\">"+item.getProduct_price()+"</td>";
			tmp += "<td class=\"NAME\">"+item.getPc_name()+"</td>";
			tmp += "<td class=\"NAME\">"+item.getPg_name()+"</td>";
			tmp += "<td class=\"NAME\">"+item.getPs_name()+"</td>";
			tmp += "<td class=\"ED\"><a href=\"#\" ><img src=\"/sys/adimgs/icons8-edit-20.png\" /></a></td>";
			tmp += "<td class=\"ED\"><a href=\"#\" ><img src=\"/sys/adimgs/icons8-trash-can-20.png\" /></a></td>";
			tmp += "<td class=\"NO\">"+item.getProduct_id()+"</td>";
			tmp += "</tr>";
			
			
			NO++;
		}
		
		
		tmp += "</table>";
		
		return tmp;
	}
}
