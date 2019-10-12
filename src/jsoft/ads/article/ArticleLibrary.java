
package jsoft.ads.article;

import java.util.*;
import jsoft.objects.*;

public class ArticleLibrary {

	public ArticleLibrary() {
		// TODO Auto-generated constructor stub
	}

	public static String viewArticles(ArrayList<ArticleObject> items) {
		String tmp = "<table cellspacing=0 class=\"tblview\" >";

		tmp += "<tr>";
		tmp += "<th>STT</th>";
		tmp += "<th>Tiêu đề</th>";
//		tmp += "<th>Nội dung</th>";
		tmp += "<th>Thể loại</th>";
		tmp += "<th>Chuyên mục</th>";
		tmp += "<th>Lượt xem</th>";
		tmp += "<th>Tác giả</th>";
		tmp += "<th colspan=2 >Thực hiện</th>";
		tmp += "<th>ID</th>";
		tmp += "</tr>";

		int NO = 0;

		for (ArticleObject item : items) {

			NO++;

			tmp += "<tr>";
			tmp += "<td class=\"NO\" >" + NO + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getArticle_title() + "</td>";
//			tmp += "<td class=\"NOTES\" >" + item.getArticle_content() + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getCategory_name() + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getSection_name() + "</td>";
			tmp += "<td class=\"NO\" >" + item.getArticle_visited() + "</td>";
			tmp += "<td class=\"AUTHOR\" >" + item.getArticle_author_name() + "</td>";
			tmp += "<td class=\"ED\" ><a href=\"#\"><img src=\"/sys/adimgs/icons8-edit-20.png\" /></a></td>";
			tmp += "<td class=\"ED\" ><a href=\"#\"><img src=\"/sys/adimgs/icons8-trash-can-20.png\" /></a></td>";
			tmp += "<td class=\"ID\" >" + item.getArticle_id() + "</td>";

			tmp += "</tr>";
		}

		tmp += "";

		tmp += "</table>";

		return tmp;
	}
}
