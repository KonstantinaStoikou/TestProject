package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDAO;
import dao.CommentDAOImpl;
import dao.PostDAO;
import dao.PostDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Comment;
import model.Post;
import model.User;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/postComment")
public class PostComment extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String text = request.getParameter("comment");
		int postId = Integer.parseInt(request.getParameter("post"));

		PostDAO postDao = new PostDAOImpl();
		Post post = postDao.find(postId);

		UserDAO userDao = new UserDAOImpl();
		String email = (String) session.getAttribute("email");
		User currentUser = userDao.findByEmail(email);

		CommentDAO commentDao = new CommentDAOImpl();
		Comment comment = new Comment();
		comment.setPost(post);
		comment.setText(text);
		comment.setUser(currentUser);

		commentDao.create(comment);

		// replace commented post with the updated one in session attribute posts lists
		List<Post> posts = (List<Post>) session.getAttribute("posts");
		for (Post p : posts) {
			if (p.getId() == post.getId()) {
				System.out.println(posts);
				int index = posts.indexOf(p);
				posts.remove(index);
				posts.add(index, post);
				System.out.println(posts);
				break;
			}
		}
		session.setAttribute("posts", posts);
		response.sendRedirect(request.getContextPath() + "/home.jsp");
	}

}
