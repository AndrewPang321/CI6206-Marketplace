package servlets;


import database.DBAO;
import database.User;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SellerProfileServlet
 */
@WebServlet("/sellerProfile")
public class SellerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SellerProfileServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unlikely-arg-type")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		
		boolean result = false;
        try {
            if (!"".equals(sellerId)) {
                DBAO accountDB = new DBAO();
                User seller = accountDB.getSeller(sellerId);
                if (seller != null) {
                        response.setStatus(200);
                        ((ServletRequest) response).setAttribute("sellerProfile", seller);
                        result = true;
                        return;
                }
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        // Authentication fail, 400: Bad Request
        response.setStatus(400);
        if (result){
        	request.getRequestDispatcher("/sellerProfile").forward(request,response);
        	return;
        	}
        	else {
        	response.sendRedirect("login.jsp");
        	return;
        	}

    }


}
