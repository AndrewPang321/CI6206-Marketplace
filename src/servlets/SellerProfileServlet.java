package servlets;


import database.DBAO;
import database.Item;
import database.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		// Auto-generated method stub
		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
        ArrayList<Item> allSellerItems = new ArrayList<>();
        User seller = new User();

        try {
            DBAO DB = new DBAO();
            seller = DB.getSeller(sellerId);
            allSellerItems.addAll(DB.getAllItems(sellerId));
            seller.setItem(allSellerItems);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            throw new ServletException(ex);
        }
        String SellerItemsJson = new Gson().toJson(seller);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(SellerItemsJson);
        out.flush();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

    }


}
