package jsoft.ads.ps;

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
 * Servlet implementation class PSView
 */
@WebServlet(name = "psview", urlPatterns = { "/ps/view" })
public class PSView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PSView() {
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
		UserObject uo = (UserObject) request.getSession().getAttribute("userLogined");

		if (uo != null) {
			view(request, response);

		} else {
			response.sendRedirect("/sys/user/login");
		}
	}

	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Xác định kiểu xuất nội dung
		response.setContentType(CONTENT_TYPE);

		
		// Lấy đối tượng xuất nội dung
		PrintWriter out = response.getWriter();
		
		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		
		
		// Tạo đối tượng thực thi chức năng
		PSControl psc = new PSControl(cp);
		
		if(cp==null) {
			getServletContext().setAttribute("CPool", psc.getCP());
		}
		
		// Lấy cấu trúc html
		String view = psc.viewPS(null, (short)1, (byte)10);
		
		// Trả lại kết nối
		psc.releaseConnection();
		
		// Xây dựng giao diện trả về
		RequestDispatcher header = request.getRequestDispatcher("/header");
		if (header!=null) {
			header.include(request, response);
		}
		
		out.print("<div class=\"c10\">");
		
		out.print("<div class=\"view\">");
		out.print(view);
		
		out.print("");
		out.print("</div>");		
		out.print("</div>");
		
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer!=null) {
			footer.include(request, response);
		}
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
