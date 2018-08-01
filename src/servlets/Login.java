package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EducationDAO;
import dao.EducationDAOImpl;
import dao.ExperienceDAO;
import dao.ExperienceDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")

public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	//use doPost method for security reasons
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail(email);
		if (user != null && password.equals(user.getPassword())) {
			request.setAttribute("errorMessage", null);
			//if(user == admin) go to admin page else { ...
			
			//store user info in session
			session.setAttribute("email", user.getEmail());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("first_name", user.getFirstName());
			session.setAttribute("last_name", user.getLastName());
			session.setAttribute("phone", user.getPhone());
			session.setAttribute("photo", user.getPhoto());
			
			ExperienceDAO expDao = new ExperienceDAOImpl();
			session.setAttribute("expList", expDao.findByUser(user));
			EducationDAO edDao = new EducationDAOImpl();
			session.setAttribute("edList", edDao.findByUser(user));
			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
		}
		else {
			request.setAttribute("errorMessage", "Invalid user or password");
			//invalidate session so that user can't access welcome.jsp 
			//because usermail will be null
			session.invalidate();
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
