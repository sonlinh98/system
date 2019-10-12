package jsoft.ads.article;

import java.sql.*;
import jsoft.*;
import jsoft.ads.category.CategoryImpl;
import jsoft.objects.*;

public class ArticleImpl extends CategoryImpl implements Article {

	public ArticleImpl(ConnectionPool cp) {
		super(cp, "article");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addArticle(ArticleObject item) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO tblarticle(";

		sql += "article_title, article_summary, article_content, ";
		sql += "article_created_date, article_last_modified, article_image, article_category_id, ";
		sql += "article_section_id, article_author_name, article_enable, ";
		sql += "article_url_link, article_tag, article_title_en, article_summary_en, ";
		sql += "article_content_en, article_tag_en, article_fee, article_isfee, ";
		sql += "article_delete, article_deleted_date, article_restored_date, article_modified_author_name, ";
		sql += "article_author_permission, article_source, article_language, article_focus, ";
		sql += "article_type, article_forhome) ";

		sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";// Số param
																											// hiện tại
																											// là: 28

		// Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());

			pre.setString(4, item.getArticle_created_date());
			pre.setString(5, item.getArticle_last_modified());
			pre.setString(6, item.getArticle_image());
			pre.setInt(7, item.getArticle_category_id());

			pre.setInt(8, item.getArticle_section_id());

			pre.setString(9, item.getArticle_author_name());
			pre.setBoolean(10, item.isArticle_enable());

			pre.setString(11, item.getArticle_url_link());
			pre.setString(12, item.getArticle_tag());
			pre.setString(13, item.getArticle_title_en());
			pre.setString(14, item.getArticle_summary_en());

			pre.setString(15, item.getArticle_content_en());
			pre.setString(16, item.getArticle_tag_en());
			pre.setInt(17, item.getArticle_fee());
			pre.setBoolean(18, item.isArticle_isfee());

			pre.setBoolean(19, item.isArticle_delete());
			pre.setString(20, item.getArticle_deleted_date());
			pre.setString(21, item.getArticle_restored_date());
			pre.setString(22, item.getArticle_modified_author_name());

			pre.setShort(23, item.getArticle_author_permission());
			pre.setString(24, item.getArticle_source());
			pre.setByte(25, item.getArticle_language());
			pre.setBoolean(26, item.isArticle_focus());

			pre.setByte(27, item.getArticle_type());
			pre.setBoolean(28, item.isArticle_forhome());

			return this.add(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		return false;
	}

	@Override
	public boolean editArticle(ArticleObject item) {
		// TODO Auto-generated method stub

		String sql = "UPDATE tblarticle SET ";
		sql += "article_title = ?, article_summary = ?, article_content = ?, ";
		sql += "article_last_modified = ?, article_image = ?, article_category_id = ?, article_section_id = ?, ";
		sql += "article_enable = ?, article_url_link = ?, ";
		sql += "article_tag = ?, article_title_en = ?, article_summary_en = ?, article_content_en = ?, ";
		sql += "article_tag_en = ?, article_fee = ?, article_isfee = ?, article_delete = ?, ";
		sql += "article_modified_author_name = ?, article_author_permission = ?, ";
		sql += "article_source = ?, article_language = ?, article_focus = ?, article_type = ?, ";
		sql += "article_forhome = ? ";
		sql += "WHERE article_id = ?";// Số lượng param hiện tại là: 25
		
		// Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());


			pre.setString(4, item.getArticle_last_modified());
			pre.setString(5, item.getArticle_image());
			pre.setInt(6, item.getArticle_category_id());

			pre.setInt(7, item.getArticle_section_id());


			pre.setBoolean(8, item.isArticle_enable());

			pre.setString(9, item.getArticle_url_link());
			pre.setString(10, item.getArticle_tag());
			pre.setString(11, item.getArticle_title_en());
			pre.setString(12, item.getArticle_summary_en());

			pre.setString(13, item.getArticle_content_en());
			pre.setString(14, item.getArticle_tag_en());
			pre.setInt(15, item.getArticle_fee());
			pre.setBoolean(16, item.isArticle_isfee());

			pre.setBoolean(17, item.isArticle_delete());


			pre.setString(18, item.getArticle_modified_author_name());

			pre.setShort(19, item.getArticle_author_permission());
			pre.setString(20, item.getArticle_source());
			pre.setByte(21, item.getArticle_language());
			pre.setBoolean(22, item.isArticle_focus());

			pre.setByte(23, item.getArticle_type());
			pre.setBoolean(24, item.isArticle_forhome());
			
			pre.setInt(25, item.getArticle_id());

			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 

		return false;
	}

	@Override
	public boolean delArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE tblarticle WHERE article_id = ? ";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			pre.setInt(1, item.getArticle_id());
			
			return this.del(pre);
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException ex1) {
				// TODO Auto-generated catch block
				ex1.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public ResultSet getArticle(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblarticle ";
		sql += "LEFT JOIN tblcategory on article_category_id = category_id ";
		sql += "LEFT JOIN tblsection on article_section_id = section_id ";
		sql += "WHERE article_id=?";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getArticles(ArticleObject similar, short at, byte total) {
		// TODO Auto-generated method stub
		String sql = "SELECT  * FROM tblarticle ";
		sql += "";
		sql += "LEFT JOIN tblcategory on article_category_id = category_id ";
		sql += "LEFT JOIN tblsection on article_section_id = section_id ";
		sql += "LIMIT " + at + ", " + total;
		return this.gets(sql);
	}

}
