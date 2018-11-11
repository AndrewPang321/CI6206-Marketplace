package servlets;

import database.DBAO;
import database.User;

import java.io.IOException;
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
        // Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
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
