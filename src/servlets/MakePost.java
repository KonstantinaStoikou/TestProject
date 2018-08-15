package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import dao.PostDAO;
import dao.PostDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Post;
import model.User;

/**
 * Servlet implementation class MakePost
 */
@WebServlet("/makePost")
@MultipartConfig
public class MakePost extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");
		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail(email);

		String text = request.getParameter("text");
		PostDAO postDao = new PostDAOImpl();
		Post post = new Post();
		post.setUser(user);
		post.setPostcol(text);
		postDao.create(post);

		// save file uploaded in home.jsp to external folder
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		Path folder = Paths.get("/home/tina/Desktop/Uploads");
		String name = FilenameUtils.getBaseName(fileName);
		String extension = FilenameUtils.getExtension(fileName);
		Path file = Files.createTempFile(folder, name + "-", "." + extension);

		try (InputStream input = filePart.getInputStream()) {
			Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
		}

		System.out.println("Uploaded file successfully saved in " + file);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
