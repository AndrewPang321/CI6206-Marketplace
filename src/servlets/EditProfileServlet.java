package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBAO;
import database.User;
import java.util.Date;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditUserProfile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id = User.currentUser.getUserId();
		User user = new User();
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			String email = request.getParameter("email");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			Date dateOfBirth = dateFormatter.parse(request.getParameter("dateOfBirth"));
			String gender = request.getParameter("gender");
			int contact = Integer.parseInt(request.getParameter("contact"));
			String address = request.getParameter("address");
			int postalCode = Integer.parseInt(request.getParameter("postalCode"));
			String country = request.getParameter("country");
		
			DBAO DB = new DBAO();
			user = DB.updateUser(user_id);
			user.setEmail(email);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setDateOfBirth(dateOfBirth);
			user.setGender(gender);
			user.setContact(contact);
			user.setAddress(address);
			user.setPostalCode(postalCode);
			user.setCountry(country);
			
		}catch (Exception ex) {
            response.setStatus(400);
            throw new ServletException(ex);
        }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
