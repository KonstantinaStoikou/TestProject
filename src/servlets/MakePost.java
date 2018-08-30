package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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
		Part filePart = request.getPart("file");

		PostDAO postDao = new PostDAOImpl();
		Post post = new Post();
		post.setUser(user);
		if (text != null) {
			post.setText(text);
		}
		if (filePart != null) {

			InputStream filecontent = filePart.getInputStream();
			byte[] fileArray = IOUtils.toByteArray(filecontent);

			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dots", "api_key", "966661985845386",
					"api_secret", "Z2dX_qZBL8e0STEJ_yPhq7gi_8o"));

			String mediaType = request.getParameter("hidden");
			if (mediaType.equals("image")) {
				post.setMediaType("image");
				Map uploadResult = cloudinary.uploader().upload(fileArray, ObjectUtils.emptyMap());
				String url = (String) uploadResult.get("url");
				post.setFilePath(url);

			} else if (mediaType.equals("video")) {
				post.setMediaType("video");
				Map uploadResult = cloudinary.uploader().upload(fileArray, ObjectUtils.asMap("resource_type", "video"));
				String url = (String) uploadResult.get("url");
				post.setFilePath(url);

			} else if (mediaType.equals("audio")) {
				post.setMediaType("audio");
				Map uploadResult = cloudinary.uploader().upload(fileArray, ObjectUtils.asMap("resource_type", "video"));
				String url = (String) uploadResult.get("url");
				post.setFilePath(url);
			}

		}

		postDao.create(post);

		session.setAttribute("posts", postDao.list());

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
