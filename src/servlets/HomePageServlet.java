package servlets;

import com.google.gson.Gson;
import database.DBAO;
import database.Item;

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
@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HomePageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Auto-generated method stub
        ArrayList<Item> allItems = new ArrayList<>();

        try {
            DBAO DB = new DBAO();
            allItems.addAll(DB.getAllItem());
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
