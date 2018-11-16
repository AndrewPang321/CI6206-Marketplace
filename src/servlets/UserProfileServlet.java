package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.DBAO;
import database.User;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/userProfile")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (User.currentUser != null) {
            int user_id = User.currentUser.getUserId();
            User user;

            try {
                DBAO DB = new DBAO();
                user = DB.getUserWithId(user_id);
                response.setStatus(200);
            } catch (Exception ex) {
                response.setStatus(400);
                throw new ServletException(ex);
            }
            String userInfoJson = new Gson().toJson(user);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(userInfoJson);
            out.flush();
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.append("-1");
            out.close();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
