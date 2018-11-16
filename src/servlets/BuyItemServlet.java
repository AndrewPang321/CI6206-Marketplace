package servlets;

import database.DBAO;
import database.User;
import database.UserAccount;
import database.Item;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


 
//import org.o7planning.tutorial.jdbc.ConnectionUtils;
 
@WebServlet("/buyitem")

public class BuyItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 // database connection settings
    private String dbURL = "jdbc:mysql://161.117.121.110:3306/marketplace";
    private String dbUser = "root";
    private String dbPass = "Password123";
    

 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//HttpSession httpSession = request.getSession(true);
    	HttpSession httpSession = request.getSession();
        Connection conn = null;
        
        
        //String str_item_id = request.getParameter("item_id");
		//System.out.print("String Item ID returned is: " + str_item_id);
		
        int item_id = Integer.parseInt(request.getParameter("item_id"));
		System.out.print("String Item ID returned is: " + item_id);
        //String item_id = request.getParameter("item_id");
       //int item_id = 1;
       //float offer_price = 10; 
        float offer_price = Float.parseFloat(request.getParameter("offer_price"));
        
    
       String item_title = null;
        
        // not needed.
        
        
        
        //for testing int user_id = 1;
        //int user_id = 1;
        int user_id = User.currentUser.getUserId();
        System.out.print("User ID is: " + user_id);
        if ("".equals(user_id)) {
            // List item fail, 400: Bad Request
            response.setStatus(400);
            return;
        }
        

        
        
        
        // get listing item_id from database
        try {
            DBAO dbao = new DBAO();
            item_title = dbao.getItemTitle(item_id);        
            // Get item_id success, 200: Success
            // response.setStatus(200);
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
        
        
        try {
            // Connection to Database
            // (See more in JDBC Tutorial).
        	
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            //conn = ConnectionUtils.getMyConnection();
            //conn.setAutoCommit(false);
 

                       
            
  
 
         
                    // Write to file
                    //this.writeToDB(conn, fileName, is, description);
                    String sql = "INSERT INTO t_offer (buyer_id, item_id, offer_price, item_title) values (?,?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, user_id);
                    statement.setInt(2, item_id);
                    statement.setFloat(3, offer_price);
                    statement.setString(4, item_title);

                    
                    statement.executeUpdate();
                    
       
            conn.commit();
            // Upload successfully!.
            response.sendRedirect("/userHome.jsp");
            //actionResponse.setRenderParameter("jspPage","/userHome.jsp");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            this.closeQuietly(conn);
        }
        response.sendRedirect("userHome.jsp");
    }
 
    private String extractFileName(Part part) {

        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
 
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
             
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
 


 
    private void closeQuietly(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }
 
}