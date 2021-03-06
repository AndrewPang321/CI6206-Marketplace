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
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Blob;

import javax.servlet.RequestDispatcher;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50) // 50MB
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
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession httpSession = request.getSession(true);
    	HttpSession httpSession = request.getSession();
        Connection conn = null;
		
        int item_id = -1;
        String item_title = request.getParameter("item_title");
        String item_category = request.getParameter("item_category");
        String item_description = request.getParameter("item_description");
        String item_condition = request.getParameter("item_condition");
        String item_location = request.getParameter("item_location");
        String item_delivery_mode = request.getParameter("item_delivery_mode");
        float selling_price = Float.parseFloat(request.getParameter("selling_price"));
        float shipping_fee = Float.parseFloat(request.getParameter("shipping_fee"));
		
		// Blob photo = request.getBlob("photo");
		/*String photo = request.getParameter("photo");
		String image = request.getParameter("image");
		String photo_name = request.getParameter("photo_name");*/
        
        
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
            System.out.print("Item ID returned is: " + item_id);
            // Get item_id success, 200: Success
            // response.setStatus(200);
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
        
        try {        	
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(url, username, password);
 
            String photoname = request.getParameter("item_title");
            System.out.print("Photoname is: " + photoname);
 
            // Part list (multi files).
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    // File data
                    InputStream is = part.getInputStream();
                    // Write to file
                    // this.writeToDB(conn, fileName, is, description);
                    String sql = "INSERT INTO t_item_photo (item_id, photo_name, photo) values (?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, item_id);
                    statement.setString(2, fileName);
                    statement.setBlob(3, is);
                    statement.executeUpdate();
                }
            }
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
