package servlets;

import database.DBAO;
import database.User;
import database.UserAccount;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class ListItemServlet
 */
@WebServlet("/listitem")
public class ListItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		
		String item_title = request.getParameter("item_title");
        String item_category = request.getParameter("item_category");
        String item_description = request.getParameter("item_description");
        String item_condition = request.getParameter("item_condition");
        String item_location = request.getParameter("item_location");
        String item_delivery_mode = request.getParameter("item_delivery_mode");
        float selling_price = Float.valueOf("selling_price");
        float shipping_fee = Float.valueOf("shipping_fee");
        
        int user_id = 1;
        
        try {
            DBAO DB = new DBAO();

            int item_id = DB.addItem(user_id, item_title, item_category, item_description, item_condition, item_location, item_delivery_mode, selling_price, shipping_fee);

            // Sign up success, 201: Created
            response.setStatus(201);
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
    }

}
