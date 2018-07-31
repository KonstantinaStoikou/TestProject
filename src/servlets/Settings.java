package servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import jpautils.EntityManagerHelper;
import model.User;

/**
 * Servlet implementation class Settings
 */
@WebServlet("/settings")
public class Settings extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hiddenParam = request.getParameter("action");
		HttpSession session = request.getSession();
		if (hiddenParam.equals("email_change")) {
			
			String email = (String)session.getAttribute("email");
			UserDAO dao = new UserDAOImpl();
			User user = dao.findByEmail(email);
			
			String newEmail = request.getParameter("email");
			EntityManager em = EntityManagerHelper.getEntityManager();
			em.getTransaction().begin();
			user.setEmail(newEmail);
			em.getTransaction().commit();
			session.setAttribute("email", user.getEmail());
			
		} else if (hiddenParam.equals("password_change")){
			
			String newPassword = request.getParameter("newpass");
			String oldPassword = request.getParameter("oldpass");
			if (!oldPassword.equals(session.getAttribute("password"))) {
				request.setAttribute("errorMessage", "The old password you entered is wrong");
				request.getRequestDispatcher("/settings.jsp").forward(request, response);
			}
			else {
				String email = (String)session.getAttribute("email");
				
				UserDAO dao = new UserDAOImpl();
				User user = dao.findByEmail(email);
				
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				user.setPassword(newPassword);
				em.getTransaction().commit();
				session.setAttribute("password", user.getPassword());
			}
			
		}
		request.getRequestDispatcher("/settings.jsp").forward(request, response);
	}

}
