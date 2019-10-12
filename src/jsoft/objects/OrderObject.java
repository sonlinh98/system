package jsoft.objects;

public class OrderObject {

	
	private int order_id;
	private String order_article_id;
	private String order_customer_id;
	private String order_created_date;
	public int getOrder_id() {
		return order_id;
	}
	public String getOrder_article_id() {
		return order_article_id;
	}
	public String getOrder_customer_id() {
		return order_customer_id;
	}
	public String getOrder_created_date() {
		return order_created_date;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public void setOrder_article_id(String order_article_id) {
		this.order_article_id = order_article_id;
	}
	public void setOrder_customer_id(String order_customer_id) {
		this.order_customer_id = order_customer_id;
	}
	public void setOrder_created_date(String order_created_date) {
		this.order_created_date = order_created_date;
	}
	
	
	
}
