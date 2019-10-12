package jsoft.ads.section;

import java.util.ArrayList;

import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

public class SectionLibrary {

	public SectionLibrary() {
		// TODO Auto-generated constructor stub
	}

	public static String viewSections(ArrayList<SectionObject> items) {
		String tmp = "<table cellspacing=0 class=\"tblview\" >";

		tmp += "<tr>";
		tmp += "<th>STT</th>";
		tmp += "<th>Tên chuyên mục</th>";
		tmp += "<th>Ghi chú</th>";
		tmp += "<th>Ngày tạo</th>";
//		tmp += "<th>Người quản lý</th>";
//		tmp += "<th>Tác giả</th>";
		tmp += "<th colspan=2 >Thực hiện</th>";
		tmp += "<th>ID</th>";
		tmp += "</tr>";

		int NO = 0;

		for (SectionObject item : items) {

			NO++;

			tmp += (NO % 2 == 0) ? "<tr class=\"EVEN\">" : "<tr>";
			tmp += "<td class=\"NO\" >" + NO + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getSection_name() + "</td>";
			tmp += "<td class=\"NOTES\" >" + item.getSection_notes() + "</td>";
			tmp += "<td class=\"CREATED_DATE\" >" + item.getSection_created_date() + "</td>";
//			tmp += "<td class=\"MANAGER\" >" + item.getSection_manager_id() + "</td>";
//			tmp += "<td class=\"AUTHOR\" >" + item.getSection_created_author_id() + "</td>";
			tmp += "<td class=\"ED\" ><a href=\"/sys/section/ae?id=" + item.getSection_id() + "\"><img src=\"/sys/adimgs/icons8-edit-20.png\" /></a></td>";
			tmp += "<td class=\"ED\" ><a href=\"javascript:confirmDel('/sys/section/del?id="+ item.getSection_id() +"'); void(0); \"" + "><img src=\"/sys/adimgs/icons8-trash-can-20.png\" /></a></td>";
			tmp += "<td class=\"ID\" >" + item.getSection_id() + "</td>";

			tmp += "</tr>";
		}

		tmp += "";

		tmp += "</table>";

		return tmp;
	}

	public static String viewManagerOptions(ArrayList<UserObject> users) {
		String tmp = "<select name=\"slcSectionManager\">";

		for (UserObject item : users) {
			tmp += "<option value=\"" + item.getUser_id() + "\">";
			tmp += item.getUser_name() + "("+item.getUser_fullname()+")";
			tmp += "</option>";
		}

		tmp += "</select>";

		return tmp;
	}
	
	public static String viewItemOnPage(byte number) {
		
		String tmp = "<div class=\"wrapPageNumber\">";
		tmp += "<span>Hiển thị </span>";
		tmp += "<select type=\"text\">";
		
		String selected = "";
		for (int i = 1; i < 6; i++) {	
			if ((i*10)==number) {
				selected = "selected";
			}
			tmp += "<option value=\""+i+"\" "+selected+">"+(i*10)+"</option>";
			selected = "";
		}
		
		tmp += "</select>";
		tmp += "</div>";
		
		return tmp;
	}
	
	public static String showListButtonPages(short pageCurrent, short total_record) {
		String tmp = "";
		
		int pagePrev = pageCurrent;
		int pageNext = pageCurrent;
		int total_page = total_record/15;
		
		if (pageCurrent>1 && pageCurrent<=total_page) {
			pagePrev = pageCurrent - 1;
			pageNext = pageCurrent + 1;
		}
		
		tmp += "<a href=\""+pagePrev+"\"><img src=\"/sys/adimgs/icons8-prev-16.png\" /></a>";
		
		String active = "";
		for (int i = 1; i < 6; i++) {
			if (i==pageCurrent) {
				active = "active";
			}
			tmp += "<a href=\"/sys/section/view?page="+i+"\" class=\""+active+"\"> "+i+"</a>";		
			active = "";
		}
		
		tmp += "<a href=\""+pageNext+"\"><img src=\"/sys/adimgs/icons8-end-16.png\"></a>";
		
		return tmp;
	}

}
