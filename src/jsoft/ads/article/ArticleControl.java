package jsoft.ads.article;

import java.util.ArrayList;

import jsoft.*;
import jsoft.objects.ArticleObject;

public class ArticleControl {

	private ArticleModel am;
	
	public ArticleControl(ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		am = new ArticleModel(cp);
	}

	public void finalize() {
		this.am = null;
		System.gc();
	}
	
	public ConnectionPool getCP() {
		return this.am.getCP();
	}
	
	public void releaseConnection() {
		this.am.releaseConnection();
	}
	
	//---------------------------------------
	
	public boolean addArticle(ArticleObject item) {
		return this.am.addArticle(item);
	}
	
	public boolean editArticle(ArticleObject item) {
		return this.am.editArticle(item);
	}
	
	public boolean delArticle(ArticleObject item) {
		return this.am.delArticle(item);
	}
	
	
	//---------------------------------------
	
	public String viewArticles(ArticleObject similar, short page, byte total) {
		// Lấy danh sách đối tượng
		ArrayList<ArticleObject> items = this.am.getArticleObjects(similar, page, total);
		
		// Trả về cấu trúc trình bày
		return ArticleLibrary.viewArticles(items);
	}
	
	public static void main(String[] args) {
		ArticleControl ac = new ArticleControl(null);
		
		String views = ac.viewArticles(null, (short)1, (byte)10);
		
		ac.releaseConnection();
		System.out.println(views);
	}
	
}
