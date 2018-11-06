package servlets;

import database.AccountDBAO;
import database.User;
import database.UserAccount;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());


//        User loginUser = userRepository.findByEmail(email);
//        if (loginUser == null) {
//            // Authentication fail, 400: Bad Request
//            redirectAttributes.addFlashAttribute("display", "show");
//            redirectAttributes.addFlashAttribute("msg", "Incorrect email");
//            return "redirect:/auth";
//        }
//
//        if (BCrypt.checkpw(password, loginUser.getUserAccount().getPassword())) {
//            // Authentication success, 200: Success
//            saveUserSession(session, loginUser);
//            return "redirect:/";
//        }
//
//        // Authentication fail, 400: Bad Request
//        redirectAttributes.addFlashAttribute("display", "show");
//        redirectAttributes.addFlashAttribute("msg", "Incorrect password");
//        return "redirect:/auth";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("loginEmail");
        String password = request.getParameter("loginPassword");
        // set headers and buffer size before accessing the Writer
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        if (!"".equals(email) && !"".equals(password)) {
            // TODO: Successful authentication with DB
            try {
                AccountDBAO accountDB = new AccountDBAO();
                User user = accountDB.getUser(email);
                // If can't get, user == null
                if (user != null) {
                    // Check password
                    // Save into Session?
                }
                System.out.println("TEST DB 2: " + user);
                System.out.println("TEST DB 2: " + user.getEmail());
                System.out.println("TEST DB 2: " + user.getUserAccount());
                System.out.println("TEST DB 2: " + user.getUserAccount().getPassword());
//                out.print("<h2>" + user.getEmail() + "</h2>");
            } catch (Exception ex) {
                response.resetBuffer();
                throw new ServletException(ex);
            }
//            Boolean success = true;
//            if (success) {
//                // Authentication success, 200: Success
//                response.setStatus(200);
//            }
//            else {
//                // Authentication fail, 400: Bad Request
//                response.setStatus(400);
//            }
        }
        out.close();
        // Authentication fail, 400: Bad Request
        response.setStatus(400);
    }

}
