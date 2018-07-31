package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import dao.UserDAO;
import dao.UserDAOImpl;
import jpautils.EntityManagerHelper;
import model.User;

/**
 * Servlet implementation class EditProfile
 */
//@WebServlet("/editProfile")
@MultipartConfig

@WebServlet(
        name = "UploadServlet",
        urlPatterns = { "/editProfile"},
        loadOnStartup = 1
)
public class EditProfile extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Part part = request.getPart("action");
//		InputStream file = part.getInputStream();
//		String hiddenParam = IOUtils.toString(file);
		HttpSession session = request.getSession();
		
		String hiddenParam = request.getParameter("action");
		
		String email = (String)session.getAttribute("email");
		System.out.println(hiddenParam);
		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail(email);
		
		if (hiddenParam.equals("general_info")) {
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String phone = request.getParameter("phone");
			
			//get photo parameter as byte array to store to db
			Part filePart = request.getPart("photo");
			InputStream filecontent = filePart.getInputStream();
			byte[] photo = IOUtils.toByteArray(filecontent);
			
			EntityManager em = EntityManagerHelper.getEntityManager();
			em.getTransaction().begin();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(phone);
			user.setPhoto(photo);
			
			em.getTransaction().commit();
			
			session.setAttribute("first_name", user.getFirstName());
			session.setAttribute("last_name", user.getLastName());
			session.setAttribute("phone", user.getPhone());
			session.setAttribute("photo", user.getPhoto());
			
		}
		else if (hiddenParam.equals("experience_info")) {
			
		}
		else if (hiddenParam.equals("education_info")) {
					
		}
		else if (hiddenParam.equals("skill_info")) {
			
		}
		
		request.getRequestDispatcher("/edit_profile.jsp").forward(request, response);
	}

}
