package servlets;

import database.DBAO;
import database.User;
import database.UserAccount;
import database.Item;
import database.Offer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;
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
@WebServlet("/acceptoffer")
public class AcceptOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String url = "jdbc:mysql://161.117.121.110:3306/marketplace";
    private static String dbdriver = "com.mysql.jdbc.Driver";
    private static String username = "andrew";
    private static String password = "Password123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptOfferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession httpSession = request.getSession();
        Connection conn = null;
        
        String str_item_id = request.getParameter("item_id");
		System.out.println("in accept offer (doPost): " + str_item_id);
		int item_id = Integer.parseInt(str_item_id);
        
        try {
        	// add new listing item into database
            DBAO DB = new DBAO();
            DB.acceptOffer(item_id);
            // Sign up success, 201: Created
            response.setStatus(201);
            conn.commit();
            System.out.println("this accept offer is for: " + item_id);
            // Upload successfully!.
            response.sendRedirect("/userHome.jsp");
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }        
        
        response.sendRedirect("userHome.jsp");
    }

}
