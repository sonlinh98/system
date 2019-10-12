package jsoft.ads.ps;

import java.util.ArrayList;

import jsoft.objects.PSObject;

public class PSLibrary {

	public PSLibrary() {
		// TODO Auto-generated constructor stub
	}
	public static String viewPSs(ArrayList<PSObject> items) {
		
		String tmp = "<table cellspacing=0 class=\"tblview\" >\n";

		tmp += "<tr>";
		tmp += "<th>STT</th>";
		tmp += "<th>Hệ sản phẩm</th>";
		tmp += "<th>Ghi chú</th>";
		tmp += "<th>Ngày tạo</th>";
		tmp += "<th colspan=2 >Thực hiện</th>";
		tmp += "<th>ID</th>";
		tmp += "</tr>";

		int NO = 0;

		for (PSObject item : items) {

			NO++;

			tmp += (NO%2==0) ? "<tr class=\"EVEN\">" : "<tr>";
			tmp += "<td class=\"NO\" >" + NO + "</td>";
			tmp += "<td class=\"NAME\" >" + item.getPs_name() + "</td>";
			tmp += "<td class=\"EMAIL\" >" + item.getPs_notes() + "</td>";
			tmp += "<td class=\"EMAIL\" >" + item.getPs_created_date() + "</td>";
			tmp += "<td class=\"ED\" ><a href=\"#\"><img src=\"/sys/adimgs/icons8-edit-20.png\" /></a></td>";
			tmp += "<td class=\"ED\" ><a href=\"#\"><img src=\"/sys/adimgs/icons8-trash-can-20.png\" /></a></td>";
			tmp += "<td class=\"ID\" >" + item.getPs_id() + "</td>";

			tmp += "</tr>";
		}

		tmp += "";

		tmp += "</table>";

		return tmp;
	}

}
