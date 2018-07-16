package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//use doPost method for security reasons
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String first_name = request.getParameter("first_name");
//		String last_name = request.getParameter("last_name");		
		String password = request.getParameter("password");
//		String password_conf = request.getParameter("password_conf");
		String email = request.getParameter("email");
//		String phone = request.getParameter("phone");
//		String photo = request.getParameter("photo");
		HttpSession session = request.getSession();
		
		UserDAO dao = new UserDAOImpl();
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		if (dao.find(email) == null) {
			session.setAttribute("usermail", "email");
			dao.create(user);
			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
		}
		


	}

}
