package servlets;

import database.DBAO;
import database.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
        // Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String email = request.getParameter("signupEmail");
        String password = request.getParameter("signupPassword");
        String confirmPassword = request.getParameter("signupConfirmPassword");
        String firstname = request.getParameter("signupFirstName");
        String lastname = request.getParameter("signupLastName");
        String dateOfBirth = request.getParameter("signupDateOfBirth");
        String gender = request.getParameter("signupGender");
        String username = request.getParameter("signupUsername");
        String contactAsString = request.getParameter("signupContact");
        int contact = Integer.valueOf(contactAsString);
        String address = request.getParameter("signupAddress");
        String postalCodeAsString = request.getParameter("signupPostalCode");
        int postalCode = Integer.valueOf(postalCodeAsString);
        String country = request.getParameter("signupCountry");

        if ("".equals(email) || "".equals(password) || "".equals(confirmPassword) || "".equals(firstname) ||
                "".equals(lastname) || "".equals(dateOfBirth) || "".equals(gender) || "".equals(username) ||
                "".equals(String.valueOf(contact)) || "".equals(address) || "".equals(String.valueOf(postalCode)) ||
                "".equals(country)) {
            // Sign up fail, 400: Bad Request
            response.setStatus(400);
            return;
        }

        if (!password.equals(confirmPassword)) {
            // Sign up fail, 400: Bad Request
            response.setStatus(400);
            return;
        }

        // Convert date string to date object
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirthWithTypeDate;
        try {
            dateOfBirthWithTypeDate = dateFormatter.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            DBAO DB = new DBAO();

            // Check for duplicated sign up email
            User user = DB.getUser(email);
            if (user != null) {
                // Authentication failed, 409: duplicate entities
                response.setStatus(409);
                return;
            }

            int user_id = DB.addUser(email, firstname, password, dateOfBirth, gender, contactAsString, address, country, postalCodeAsString);
            DB.addUserAccount(user_id, username, BCrypt.hashpw(password, BCrypt.gensalt()));
            httpSession.setAttribute("user", DB.getUser(email));
            // Sign up success, 201: Created
            response.setStatus(201);
        } catch (Exception ex) {
            System.out.println("servlet: "+ex);
            throw new ServletException(ex);
        }
    }

}
