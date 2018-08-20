package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import dao.JobDAO;
import dao.JobDAOImpl;
import dao.PostDAO;
import dao.PostDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class Signup
 */
//@WebServlet("/signup")
@MultipartConfig

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/signup" }, loadOnStartup = 1)
public class Signup extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// use doPost method for security reasons
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String password = request.getParameter("password");
		String passwordConf = request.getParameter("password_conf");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		// get photo parameter as byte array to store to db
		Part filePart = request.getPart("photo");
		InputStream filecontent = filePart.getInputStream();
		byte[] photo = IOUtils.toByteArray(filecontent);

		HttpSession session = request.getSession();

		UserDAO dao = new UserDAOImpl();
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		if (dao.findByEmail(email) == null) {
			request.setAttribute("errorMessage", null);
			session.setAttribute("email", email);
			if (!password.equals(passwordConf)) {
				request.setAttribute("errorMessage", "Password confirmation is wrong");
				session.invalidate();
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", null);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPhone(phone);
				user.setPhoto(photo);
				dao.create(user);

				//// store user info in session
				session.setAttribute("id", user.getId());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("password", user.getPassword());
				session.setAttribute("first_name", user.getFirstName());
				session.setAttribute("last_name", user.getLastName());
				session.setAttribute("phone", user.getPhone());
				session.setAttribute("photo", user.getPhoto());
				JobDAO jobDao = new JobDAOImpl();
				session.setAttribute("recommendedJobs", jobDao.list());
				PostDAO postDao = new PostDAOImpl();
				session.setAttribute("posts", postDao.list());

				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "There is already a user with this email");
			// invalidate session so that user can't access welcome.jsp
			// because usermail will be null
			session.invalidate();
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}

	}

}
