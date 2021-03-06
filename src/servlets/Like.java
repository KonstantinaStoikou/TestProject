package servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import dao.PostDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import jpautils.EntityManagerHelper;
import model.Post;
import model.User;

/**
 * Servlet implementation class Like
 */
@WebServlet("/like")
public class Like extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int postId = Integer.parseInt(request.getParameter("post"));
		PostDAO postDao = new PostDAOImpl();
		Post post = postDao.find(postId);

		UserDAO userDao = new UserDAOImpl();
		String email = (String) session.getAttribute("email");
		User currentUser = userDao.findByEmail(email);

		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		post.addLikeUser(currentUser);
		currentUser.addLikedPost(post);
		em.getTransaction().commit();

		session.setAttribute("posts", postDao.list());

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
