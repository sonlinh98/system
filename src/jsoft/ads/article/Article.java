package jsoft.ads.article;

import java.sql.*;
import jsoft.*;
import jsoft.objects.*;

public interface Article extends ShareControl{

	// Các chức năng cập nhật
	public boolean addArticle(ArticleObject item);
	public boolean editArticle(ArticleObject item);
	public boolean delArticle(ArticleObject item);
	
	
	// Các chức năng lấy dữ liệu
	public ResultSet getArticle(int id);
	public ResultSet getArticles(ArticleObject similar, short at, byte total);
}
