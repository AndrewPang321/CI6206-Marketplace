package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.DBAO;
import database.Item;
import database.User;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/userProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Auto-generated method stub
		int user_id = User.currentUser.getUserId();
        ArrayList<Item> allUSerItems = new ArrayList<>();
        User user = new User();

        try {
            DBAO DB = new DBAO();
            user = DB.getCurrentUserProfile(user_id);
            allUSerItems.addAll(DB.getAllItems(user_id));
            user.setItem(allUSerItems);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            throw new ServletException(ex);
        }
        String UserItemsJson = new Gson().toJson(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(UserItemsJson);
        out.flush();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
