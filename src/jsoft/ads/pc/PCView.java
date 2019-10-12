package jsoft.ads.pc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class PCView
 */
@WebServlet(name = "pcview", urlPatterns = { "/pc/view" })
public class PCView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONTENT_TYPE ="text/html; charset=UTF-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PCView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Kiểm tra phiên làm việc
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		
		if (user!=null) {
			view(request, response);
		}
		else {
			response.sendRedirect("/sys/user/login");
		}
		
	}
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//Xác định kiểu trả về cho trình khách
		response.setContentType(CONTENT_TYPE);
		
		// Lấy đối tượng xuất nội dung
		PrintWriter out = response.getWriter();
		
		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		
		// Tạo đối tượng thực thi chức năng
		PCControl pcc = new PCControl(cp);
		if (cp==null) {
			getServletContext().setAttribute("CPool", pcc.getCP());
		}
		
		// Lấy giao diện html
		String view = pcc.viewPCs(null, (short)1, (byte)10);
	
		// Trả lại kết nối
		pcc.releaseConnection();
		
		// Xây dựng nội dung trả về hoàn chỉnh
		RequestDispatcher header = request.getRequestDispatcher("/header");
		if (header!=null) {
			header.include(request, response);
		}
		
		out.print("<div class=\"c10\">");
		
		out.print("<div class=\"view\">");
		out.print("<div class=\"c4 acenter\">");
		out.print("<a href=\"/sys/section/ae\" ><img src=\"/sys/adimgs/icons8-plus-math.png\" /></a>");
		out.print("</div>");

		out.print("<div class=\"c8 aleft p-t-10\" >");
		out.print("<form name=\"frmsearch\" action=\"\" method=\"\" > ");
		out.print("<input type=\"text\" name=\"txtKeyword\" />");
		out.print("<input type=\"submit\" value=\"Tìm kiếm\" />");
		out.print("</form>");
		out.print("</div>");
		out.print("<div class=\"clr\" ></div>");
		out.print("</div>");
		//------------------------------------
		out.print("<div class=\"view\">");
		out.print(view);
		out.print("");
		out.print("</div>");	
		
		//-------------------------------------
		out.print("<div class=\"view\">");
		out.print("<div class=\"c3 acenter\"></div>");
		out.print("<div class=\"c6 aleft p-t-10 p-b-10\">");
		out.print("");
		out.print("<div class=\"wrapPageNumber\">");
		out.print("<span>Hiển thị </span>");
		out.print("<select type=\"text\" value=\"5\">");
		out.print("<option value=\"1\">5</option>");
		out.print("<option value=\"2\">10</option>");
		out.print("<option value=\"3\">15</option>");
		out.print("<option value=\"4\">20</option>");
		out.print("</select>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"wrapPageNumber\">");
		out.print("<div class=\"pageNumber m-l-20\">");
		out.print("");
		out.print("<a href=\"#\"><img src=\"/sys/adimgs/icons8-prev-16.png\" /></a>");
		out.print("");
		out.print("<a href=\"#\"> 1</a>");
		out.print("<a href=\"#\" class=\"active\"> 2</a>");
		out.print("<a href=\"#\"> 3</a>");
		out.print("<a href=\"#\"> 4</a>");
		out.print("<a href=\"#\"> 5</a>");
		out.print("");
		out.print("<a href=\"#\"><img src=\"/sys/adimgs/icons8-end-16.png\"></a>");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("				</div>");
		out.print("<div class=\"c3 left p-t-10\"></div>");
		out.print("<div class=\"clr\"></div>");
		out.print("</div>");
		
		out.print("</div>");
		
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer!=null) {
			footer.include(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
