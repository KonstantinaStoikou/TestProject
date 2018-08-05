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
 * Servlet implementation class Connection
 */
@WebServlet("/connection")
public class Connection extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String u = request.getParameter("user");
		String email = (String)session.getAttribute("email");
		
		UserDAO dao = new UserDAOImpl();
		User befriended = dao.findByEmail(u);
		User currentUser = dao.findByEmail(email);
		
		if (!currentUser.getFriends().contains(befriended) && currentUser != befriended) {
			EntityManager em = EntityManagerHelper.getEntityManager();
			em.getTransaction().begin();
			currentUser.addFriends(befriended);
			em.getTransaction().commit();
		}
		request.setAttribute("connected", currentUser.getFriends().contains(befriended));
		request.setAttribute("user", befriended);
		request.getRequestDispatcher("/user_profile.jsp").forward(request, response);
	    
	}

}
