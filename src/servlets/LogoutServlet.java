package servlets;

import database.DBAO;
import database.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (User.currentUser != null) {
            User.currentUser = null;
            request.removeAttribute("user");
            // Logout success, response code 200
            response.setStatus(200);
            System.out.println("Logout success: " + User.currentUser);
            return;
        }
        // Logout failure, response code 400
        response.setStatus(400);
        System.out.println("Logout failure: " + User.currentUser);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
