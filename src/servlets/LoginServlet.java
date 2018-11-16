package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.DBAO;
import database.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject currentUser = new JsonObject();

        if (User.currentUser != null) {
            currentUser.addProperty("currentUserId", User.currentUser.getUserId());
        } else {
            currentUser.addProperty("currentUserId", -1);
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(currentUser);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String email = request.getParameter("loginEmail");
        String password = request.getParameter("loginPassword");

        try {
            if (!"".equals(email) && !"".equals(password)) {
                DBAO DB = new DBAO();
                User user = DB.getUser(email);
                if (user != null) {
                    if (BCrypt.checkpw(password, user.getUserAccount().getPassword())) {
                        httpSession.setAttribute("user", user);
                        // Using static variable to achieve the same purpose as httpsession but available outside servlets
                        User.currentUser = user;
                        // Authentication success, 200: Success
                        response.setStatus(200);
//                        System.out.println(((User)httpSession.getAttribute("user")).getUserId());
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        // Authentication fail, 400: Bad Request
        response.setStatus(400);
    }

}
