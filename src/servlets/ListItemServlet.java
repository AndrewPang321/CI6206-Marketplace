package servlets;

import database.DBAO;
import database.User;
import database.UserAccount;

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
		
		String item_title = request.getParameter("item_title");
		// Blob photo = request.getBlob("photo");
		String photo_name = request.getParameter("photo_name");
        String item_category = request.getParameter("item_category");
        String item_description = request.getParameter("item_description");
        String item_condition = request.getParameter("item_condition");
        String item_location = request.getParameter("item_location");
        String item_delivery_mode = request.getParameter("item_delivery_mode");        
        float selling_price = Float.parseFloat(request.getParameter("selling_price"));
        float shipping_fee = Float.parseFloat(request.getParameter("shipping_fee"));
        
        //for testing int user_id = 1;
        int user_id = User.currentUser.getUserId();
        
        if ("".equals(user_id)) {
            // List item fail, 400: Bad Request
            response.setStatus(400);
            return;
        }        
        
        try {
            DBAO DB = new DBAO();

            int item_id = DB.addItem(user_id, item_title, item_category, item_description, item_condition, item_location, item_delivery_mode, selling_price, shipping_fee);
            // int item_photo_id = DB.addItemPhoto(item_id, photo_name, photo);
            
            InputStream inputStream = null; // input stream of the upload file
            
            // obtains the upload file part in this multipart request
            Part filePart = request.getPart("photo");
            if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                 
                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();
            }
             
            Connection conn = null; // connection to the database
            String message = null;  // message will be sent back to client
            
            try {
                // connects to the database
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection(url, username, password);
     
                // constructs SQL statement
                String sql = "INSERT INTO t_item_photo(item_id, photo_name, photo) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, item_id);
                statement.setString(2, photo_name);
                 
                if (inputStream != null) {
                    // fetches input stream of the upload file for the blob column
                    statement.setBlob(3, inputStream);
                }
     
                // sends the statement to the database server
                int row = statement.executeUpdate();
                if (row > 0) {
                    message = "File uploaded and saved into database";
                }
            } catch (SQLException ex) {
                message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    // closes the database connection
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                // sets the message in request scope
                request.setAttribute("Message", message);
                 
                // forwards to the message page
                getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
            }
        
            // Sign up success, 201: Created
            response.setStatus(201);
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
    }

}
