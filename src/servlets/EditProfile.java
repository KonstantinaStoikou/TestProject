package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import dao.ExperienceDAO;
import dao.ExperienceDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import jpautils.EntityManagerHelper;
import model.Experience;
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
		HttpSession session = request.getSession();
		
		String hiddenParam = request.getParameter("action");
		
		String email = (String)session.getAttribute("email");
		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail(email);
		
		//if an info form is clicked to be added
		if (hiddenParam != null) { 
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
				ExperienceDAO expDao = new ExperienceDAOImpl();
				Experience exp = new Experience();
				
				String position = request.getParameter("position");
				String company = request.getParameter("company");
				
				exp.setCompany(company);
				exp.setPosition(position);
				exp.setUser(user);
				
				//convert html date to sql date
				String startDate = request.getParameter("start_date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date parsedDate = (Date) dateFormat.parse(startDate);
					exp.setStartDate(parsedDate);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				
				String endDate = request.getParameter("end_date");
				try {
					Date parsedDate = (Date) dateFormat.parse(endDate);
					exp.setEndDate(parsedDate);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				expDao.create(exp);
				
				session.setAttribute("expList", expDao.findByUser(user));
			}
			else if (hiddenParam.equals("education_info")) {
						
			}
			else if (hiddenParam.equals("skill_info")) {
				
			}
		}
		
		//if delete an experience is clicked
		String deleteExpParam = request.getParameter("delete_exp");
		if (deleteExpParam != null) {
			System.out.println(deleteExpParam);
			if (!deleteExpParam.equals("nothing")) {
				ExperienceDAO expDao = new ExperienceDAOImpl();
				Experience exp = expDao.findById(Integer.parseInt(deleteExpParam));
				expDao.delete(exp);
				
				session.setAttribute("expList", expDao.findByUser(user));
			}
		}
		
		request.getRequestDispatcher("/edit_profile.jsp").forward(request, response);
	}

}
