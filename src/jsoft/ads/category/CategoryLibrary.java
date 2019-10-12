package jsoft.ads.category;

import java.util.*;
import jsoft.objects.*;

public class CategoryLibrary {

	public CategoryLibrary() {
		// TODO Auto-generated constructor stub
	}

	public static String viewCategories(ArrayList<CategoryObject> items) {
		String tmp = "<table cellspacing=0 class=\"tblview\" >\n";

		tmp += "<tr>";
		tmp += "<th>STT</th>";
		tmp += "<th>Tên thể loại</th>";
		tmp += "<th>Tên danh mục</th>";
		tmp += "<th>Ghi chú</th>";
//		tmp += "<th>Tác giả</th>";
		tmp += "<th colspan=2 >Thực hiện</th>";
		tmp += "<th>ID</th>";
		tmp += "</tr>";

		int NO = 0;

		for (CategoryObject item : items) {

			NO++;

			tmp += "<tr>";
			tmp += "<td class=\"NO\" >" + NO + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getCategory_name() + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getSection_name() + "</td>";
			tmp += "<td class=\"NOTES\" >" + item.getCategory_notes() + "</td>";
//			tmp += "<td class=\"CREATED_DATE\" >" + item.getCategory_created_date() + "</td>";
//			tmp += "<td class=\"MANAGER\" >" + item.getCategory_manager_id() + "</td>";
//			tmp += "<td class=\"AUTHOR\" >" + item.getCategory_created_author_id() + "</td>";
			tmp += "<td class=\"ED\" ><a href=\"#\"><img src=\"/sys/adimgs/icons8-edit-20.png\" /></a></td>";
			tmp += "<td class=\"ED\" ><a href=\"#\"><img src=\"/sys/adimgs/icons8-trash-can-20.png\" /></a></td>";
			tmp += "<td class=\"ID\" >" + item.getCategory_id() + "</td>";

			tmp += "</tr>";
		}

		tmp += "";

		tmp += "</table>";

		return tmp;
	}
}
