package servlets;

import database.DBAO;
import database.User;
import database.UserAccount;
import database.Item;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.jdbc.Blob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;


/**
 * Servlet implementation class ListItemServlet
 */
@WebServlet("/listitem")
public class ListItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String url = "jdbc:mysql://161.117.121.110:3306/marketplace";
    private static String dbdriver = "com.mysql.jdbc.Driver";
    private static String username = "andrew";
    private static String password = "Password123";
       
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
		int item_id = -1;
		
		String item_title = request.getParameter("item_title");
		// Blob photo = request.getBlob("photo");
		String photo = request.getParameter("photo");
		String image = request.getParameter("image");
		String photo_name = request.getParameter("photo_name");
        String item_category = request.getParameter("item_category");
        String item_description = request.getParameter("item_description");
        String item_condition = request.getParameter("item_condition");
        String item_location = request.getParameter("item_location");
        String item_delivery_mode = request.getParameter("item_delivery_mode");        
        float selling_price = Float.parseFloat(request.getParameter("selling_price"));
        float shipping_fee = Float.parseFloat(request.getParameter("shipping_fee"));
        
        //for testing int user_id = 1;
        if (User.currentUser == null) {
            // List item fail, 400: Bad Request
            response.setStatus(400);
            return;
        }
        int user_id = User.currentUser.getUserId();
        
        try {
        	// add new listing item into database
            DBAO DB = new DBAO();
            DB.addItem(user_id, item_title, item_category, item_description, item_condition, item_location, item_delivery_mode, selling_price, shipping_fee);
            // Sign up success, 201: Created
            response.setStatus(201);
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
        
        
        // get listing item_id from database
        try {
            DBAO dbao = new DBAO();
            item_id = dbao.getItemId(user_id);        
            // Get item_id success, 200: Success
            // response.setStatus(200);
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
        
        // System.out.print("Item ID returned is: " + item_id);
        // System.out.print(" To input is: " + photo);
        // System.out.print(" To input is: " + image);
        // System.out.print(" To input is: " + photo_name);
        // photo_name = "test.jpg";
        // System.out.print(" Manually input is: " + photo_name);
        // add new listing item photo into database
        try {
            DBAO dbao_dbao = new DBAO();
            dbao_dbao.addItemPhoto(item_id, photo_name);        
            // Sign up success, 201: Created
            // response.setStatus(201);
            // request.getRequestDispatcher("userHome.jsp").forward();
            System.out.print("Item ID returned is: " + item_id);
            // response.sendRedirect("userHome.jsp"); 
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
        
        return;
    }

}
