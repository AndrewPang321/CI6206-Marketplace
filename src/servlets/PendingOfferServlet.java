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
@WebServlet("/pendingoffer")
public class PendingOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String url = "jdbc:mysql://161.117.121.110:3306/marketplace";
    private static String dbdriver = "com.mysql.jdbc.Driver";
    private static String username = "andrew";
    private static String password = "Password123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingOfferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	

		String str_item_id = request.getParameter("item_id");
		System.out.println(str_item_id);
		int item_id = Integer.parseInt(str_item_id);

        if (User.currentUser != null) {
            // int user_id = User.currentUser.getUserId();
            Offer offer;

            try {
                DBAO DB = new DBAO();
                offer = DB.getPendingOffer(item_id);
                
                response.setStatus(200);
            } catch (Exception ex) {
                response.setStatus(400);
                throw new ServletException(ex);
            }
            String offerInfoJson = new Gson().toJson(offer);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(offerInfoJson);
            out.flush();
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.append("-1");
            out.close();
        }
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    }

}
