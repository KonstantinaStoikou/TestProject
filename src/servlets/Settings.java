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
//			System.out.println(request.getSession(false).getAttribute("email"));
//			String email = (String)request.getSession(false).getAttribute("email");
//			
//			UserDAO dao = new UserDAOImpl();
//			User user = dao.find(email);
//			
//			EntityManager em = EntityManagerHelper.getEntityManager();
//			em.getTransaction().begin();
//			user.setEmail("email@email");
//			em.getTransaction().commit();
			
		} else if (hiddenParam.equals("password_change")){
			String new_password = request.getParameter("newpass");
			String old_password = request.getParameter("oldpass");
			if (!old_password.equals(session.getAttribute("password"))) {
				request.setAttribute("errorMessage", "The old password you entered is wrong");
				request.getRequestDispatcher("/settings.jsp").forward(request, response);
			}
			else {
				String email = (String)session.getAttribute("email");
				
				UserDAO dao = new UserDAOImpl();
				User user = dao.find(email);
				
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				user.setPassword(new_password);
				em.getTransaction().commit();
				session.setAttribute("password", user.getPassword());
			}
			
		}
		request.getRequestDispatcher("/settings.jsp").forward(request, response);
	}

}
