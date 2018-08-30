package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import dao.JobDAOImpl;
import dao.PostDAO;
import dao.PostDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Post;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")

public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// use doPost method for security reasons
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail(email);
		if (user != null && password.equals(user.getPassword())) {
			request.setAttribute("errorMessage", null);
			// if(user == admin) go to admin page else { ...

			// store user info in session
			session.setAttribute("id", user.getId());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("first_name", user.getFirstName());
			session.setAttribute("last_name", user.getLastName());
			session.setAttribute("phone", user.getPhone());
			session.setAttribute("photo", user.getPhoto());

			session.setAttribute("expList", user.getExperiences());
			session.setAttribute("edList", user.getEducations());
			session.setAttribute("skList", user.getSkills());

			session.setAttribute("connectionList", user.getFriends());
			session.setAttribute("conversations", user.getConversations());
			session.setAttribute("lastConvUser", user.getLastConversationUser());

			JobDAO jobDao = new JobDAOImpl();
			session.setAttribute("recommendedJobs", jobDao.list());
			session.setAttribute("postedJobs", user.getJobs());

			PostDAO postDao = new PostDAOImpl();
			List<Post> posts = new ArrayList<Post>();
			List<User> connections = user.getFriends();
			// add to posts list all posts made from this user's connections
			for (User u : connections) {
				for (Post p : u.getPosts()) {
					posts.add(p);
				}
			}
			// add posts made by this user in posts list
			for (Post p : user.getPosts()) {
				posts.add(p);
			}
			// sort posts by id because id is auto incremented,
			// sorting by id means sorting by most recent posts
			// (more recent posts will have the larger ids)
			Comparator<Post> comparator = new Comparator<Post>() {
				@Override
				public int compare(Post left, Post right) {
					return left.getId() - right.getId();
				}
			};
			Collections.sort(posts, comparator);

			session.setAttribute("posts", posts);

			request.getRequestDispatcher("/home.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Invalid user or password");
			session.invalidate();
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
