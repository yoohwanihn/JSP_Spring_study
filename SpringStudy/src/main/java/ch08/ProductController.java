package ch08;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService service;

	public ProductController() {
		service = new ProductService();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action"); // info
		String view = "";

		if (req.getParameter("action") == null) {
			getServletContext().getRequestDispatcher("/pcontrol?action=list").forward(req, resp);
		} else {
			switch (action) {
			case "list":
				view = list(req, resp);
				break;
			case "info":
				view = info(req, resp);
				break;
			}
			getServletContext().getRequestDispatcher("/ch08/" + view).forward(req, resp);

		}
	}

	private String info(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("p", service.find(req.getParameter("id")));

		return "productInfo.jsp";
	}

	private String list(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("products", service.findAll());

		return "productList.jsp";
	}

}
