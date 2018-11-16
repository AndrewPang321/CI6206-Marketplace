package servlets;

import com.google.gson.Gson;
import database.DBAO;
import database.Item;
import database.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/userhome")
public class UserHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserHomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	if (User.currentUser == null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.append("-1");
            out.close();
            return;
        }
        int user_id = User.currentUser.getUserId();

        ArrayList<Item> allItems = new ArrayList<>();
        try {
            DBAO DB = new DBAO();
            allItems.addAll(DB.getAllItemWithUserId(user_id));
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            throw new ServletException(ex);
        }
        String AllItemsJson = new Gson().toJson(allItems);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(AllItemsJson);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
