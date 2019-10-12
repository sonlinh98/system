package jsoft.ads.article;

import java.sql.*;
import jsoft.*;
import jsoft.objects.*;
import java.util.*;

public class ArticleModel {

	private Article a;

	public ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp);
	}
	
	public void finalize() {
		this.a = null;
		System.gc();
	}
	
	public ConnectionPool getCP() {
		return this.a.getCP();
	}
	
	public void releaseConnection() {
		this.a.releaseConnection();
	}
	
	//---------------------------------------------
	
	public boolean addArticle(ArticleObject item) {
		return this.a.addArticle(item);
	}
	
	public boolean editArticle(ArticleObject item) {
		return this.a.editArticle(item);
	}
	
	public boolean delArticle(ArticleObject item) {
		return this.a.delArticle(item);
	}
	
	//---------------------------------------------
	
	public ArticleObject getArticleObject(int id) {
		ArticleObject item = null;
		
		ResultSet rs = this.a.getArticle(id);
		
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image("article_image");
					item.setArticle_author_name("article_author_name");
					
					item.setArticle_category_id(rs.getShort("article_category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setArticle_section_id(rs.getShort("article_section_id"));
					item.setSection_name(rs.getString("section_name"));
					
				}
				rs.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return item;
	}
	
	
	public ArrayList<ArticleObject> getArticleObjects(ArticleObject similar, short page, byte total) {
		
		ArrayList<ArticleObject> items = new ArrayList<ArticleObject>();
		
		ArticleObject item = null;
		
		short at = (short) ((page-1)*total);
		
		ResultSet rs = this.a.getArticles(similar, at, total);
		
		if (rs!=null) {
			try {
				while (rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					item.setArticle_visited(rs.getShort("article_visited"));
					
					item.setArticle_category_id(rs.getShort("article_category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setArticle_section_id(rs.getShort("article_section_id"));
					item.setSection_name(rs.getString("section_name"));
					
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
