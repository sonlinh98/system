package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class menu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		out.print("<div class=\"row\">");
		out.print("<div class=\"c2\">");
		out.print("<div class=\"menu\">");
		out.print("<a href=\"/sys/view\"><span><img src=\"/sys/adimgs/home.png\" />  Home</span></a>");
		out.print("<a href=\"/sys/user/view\"><span><img src=\"/sys/adimgs/user.png\" />  Quản lý Người sử dụng</span></a>");
		out.print("<a href=\"/sys/section/view\"><span><img src=\"/sys/adimgs/section.png\" />  Quản lý Chuyên mục</span></a>");
		out.print("<a href=\"/sys/category/view\"><span><img src=\"/sys/adimgs/category.png\" />  Quản lý Thể loại</span></a>");
		out.print("<a href=\"/sys/article/view\"><span>  <img src=\"/sys/adimgs/article.png\" />Quản lý Bài viết</span></a>");
		out.print("<a href=\"/sys/ps/view\"><span><img src=\"/sys/adimgs/ps.png\" /> Quản lý Hệ sản phẩm</span></a>");
		out.print("<a href=\"/sys/pg/view\"><span><img src=\"/sys/adimgs/pg.png\" /> Quản lý Nhóm sản phẩm</span></a>");
		out.print("<a href=\"/sys/pc/view\"><span><img src=\"/sys/adimgs/pc.png\" /> Quản lý loại sản phẩm</span></a>");
		out.print("<a href=\"/sys/product/view\"><span><img src=\"/sys/adimgs/product.png\" /> Quản lý Sản phẩm</span></a>");
		out.print("<a href=\"#\"><span><img src=\"/sys/adimgs/bill.png\" /> Quản lý Hóa đơn</span></a>");
		out.print("<a href=\"#\"><span><img src=\"/sys/adimgs/kh.png\" /> Quản lý Khách hàng</span></a>");
		out.print("</div>");
		out.print("</div>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
