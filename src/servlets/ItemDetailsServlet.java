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
@WebServlet("/itemdetails")
public class ItemDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String url = "jdbc:mysql://161.117.121.110:3306/marketplace";
    private static String dbdriver = "com.mysql.jdbc.Driver";
    private static String username = "andrew";
    private static String password = "Password123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int user_id = User.currentUser.getUserId();
    	System.out.print("User ID returned is: " + user_id);
        
        if ("".equals(user_id)) {
            // List item fail, 400: Bad Request
            response.setStatus(400);
            return;
        } 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    }

}
