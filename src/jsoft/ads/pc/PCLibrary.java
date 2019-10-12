package jsoft.ads.pc;

import java.util.ArrayList;

import jsoft.objects.PCObject;

public class PCLibrary {

	public PCLibrary() {
		// TODO Auto-generated constructor stub
	}

	public static String viewPCs(ArrayList<PCObject> items) {
		String tmp ="<table cellspacing=0 class=\"tblview\"> ";
		
		tmp += "<tr>";
		tmp += "<th>STT</th>";
		tmp += "<th>Loại sản phẩm</th>";
		tmp += "<th>Ghi chú</th>";
		tmp += "<th>Nhóm sản phẩm</th>";
		tmp += "<th>Hệ sản phẩm</th>";
		tmp += "<th colspan=\"2\">Thực hiện</th>";
		tmp += "<th>ID</th>";
		tmp += "</tr>";
		
		int NO = 1;
		for (PCObject item : items) {
			
			tmp += (NO%2==0) ? "<tr class=\"EVEN\">" : "<tr>";
			tmp += "<td class=\"NO\">"+NO+"</td>";
			tmp += "<td class=\"NAME\">"+item.getPc_name()+"</td>";
			tmp += "<td class=\"NOTE\">"+item.getPc_notes()+"</td>";
			tmp += "<td class=\"NAME\">"+item.getPg_name()+"</td>";
			tmp += "<td class=\"NAME\">"+item.getPs_name()+"</td>";
			tmp += "<td class=\"ED\"><a href=\"#\" ><img src=\"/sys/adimgs/icons8-edit-20.png\" /></a></td>";
			tmp += "<td class=\"ED\"><a href=\"#\" ><img src=\"/sys/adimgs/icons8-trash-can-20.png\" /></a></td>";
			tmp += "<td class=\"NO\">"+item.getPc_id()+"</td>";
			tmp += "</tr>";
			
			
			NO++;
		}
		
		
		tmp += "</table>";
		
		return tmp;
	}
}
