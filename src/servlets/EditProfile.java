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

import dao.EducationDAO;
import dao.EducationDAOImpl;
import dao.ExperienceDAO;
import dao.ExperienceDAOImpl;
import dao.SkillDAO;
import dao.SkillDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import jpautils.EntityManagerHelper;
import model.Education;
import model.Experience;
import model.Skill;
import model.User;

/**
 * Servlet implementation class EditProfile
 */
//@WebServlet("/editProfile")
@MultipartConfig

@WebServlet(name = "UploadServlet", urlPatterns = { "/editProfile" }, loadOnStartup = 1)
public class EditProfile extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String hiddenParam = request.getParameter("action");

		String email = (String) session.getAttribute("email");
		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail(email);

		// if an info form is clicked to be added
		if (hiddenParam != null) {
			if (hiddenParam.equals("general_info")) {
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String phone = request.getParameter("phone");

				// get photo parameter as byte array to store to db
				Part filePart = request.getPart("photo");
				InputStream filecontent = filePart.getInputStream();
				// check if user uploaded new image, if not do nothing
				if (filecontent.available() != 0) {
					byte[] photo = IOUtils.toByteArray(filecontent);

					EntityManager em = EntityManagerHelper.getEntityManager();
					em.getTransaction().begin();
					user.setPhoto(photo);
					em.getTransaction().commit();

					session.setAttribute("photo", user.getPhoto());
				}

				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPhone(phone);

				em.getTransaction().commit();

				session.setAttribute("first_name", user.getFirstName());
				session.setAttribute("last_name", user.getLastName());
				session.setAttribute("phone", user.getPhone());

			} else if (hiddenParam.equals("experience_info")) {
				ExperienceDAO expDao = new ExperienceDAOImpl();
				Experience exp = new Experience();

				String position = request.getParameter("position");
				String company = request.getParameter("company");
				String privacy = request.getParameter("privacy");

				exp.setCompany(company);
				exp.setPosition(position);
				exp.setUser(user);
				if (privacy.equals("private")) {
					exp.setPrivacy(true);
				} else if (privacy.equals("public")) {
					exp.setPrivacy(false);
				}

				// convert html date to sql date
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
			} else if (hiddenParam.equals("education_info")) {
				EducationDAO edDao = new EducationDAOImpl();
				Education ed = new Education();

				String institution = request.getParameter("institution");
				String level = request.getParameter("level");
				String privacy = request.getParameter("privacy");

				ed.setInstitution(institution);
				ed.setLevel(level);
				ed.setUser(user);
				if (privacy.equals("private")) {
					ed.setPrivacy(true);
				} else if (privacy.equals("public")) {
					ed.setPrivacy(false);
				}

				// convert html date to sql date
				String startDate = request.getParameter("start_date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date parsedDate = (Date) dateFormat.parse(startDate);
					ed.setStartDate(parsedDate);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}

				String endDate = request.getParameter("end_date");
				try {
					Date parsedDate = (Date) dateFormat.parse(endDate);
					ed.setEndDate(parsedDate);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				edDao.create(ed);

				session.setAttribute("edList", edDao.findByUser(user));
			} else if (hiddenParam.equals("skill_info")) {
				SkillDAO skDao = new SkillDAOImpl();
				Skill sk = new Skill();

				String name = request.getParameter("name");
				String type = request.getParameter("type");
				String privacy = request.getParameter("privacy");

				sk.setName(name);
				sk.setType(type);
				sk.setUser(user);
				if (privacy.equals("private")) {
					sk.setPrivacy(true);
				} else if (privacy.equals("public")) {
					sk.setPrivacy(false);
				}

				skDao.create(sk);

				session.setAttribute("skList", skDao.findByUser(user));
			}
		}

		// if delete an experience is clicked
		String deleteExpParam = request.getParameter("delete_exp");
		if (deleteExpParam != null) {
			ExperienceDAO expDao = new ExperienceDAOImpl();
			Experience exp = expDao.find(Integer.parseInt(deleteExpParam));
			expDao.delete(exp);

			session.setAttribute("expList", expDao.findByUser(user));
		}
		// if delete an education is clicked
		String deleteEdParam = request.getParameter("delete_ed");
		if (deleteEdParam != null) {
			EducationDAO edDao = new EducationDAOImpl();
			Education ed = edDao.find(Integer.parseInt(deleteEdParam));
			edDao.delete(ed);

			session.setAttribute("edList", edDao.findByUser(user));

		}
		// if delete a skill is clicked
		String deleteSkParam = request.getParameter("delete_sk");
		if (deleteSkParam != null) {
			SkillDAO skDao = new SkillDAOImpl();
			Skill sk = skDao.find(Integer.parseInt(deleteSkParam));
			skDao.delete(sk);

			session.setAttribute("skList", skDao.findByUser(user));
		}

		// if change an experience's privacy is clicked
		String privacyExpParam = request.getParameter("privacy_exp");
		if (privacyExpParam != null) {
			ExperienceDAO expDao = new ExperienceDAOImpl();
			Experience exp = expDao.find(Integer.parseInt(privacyExpParam));
			if (exp.getPrivacy() == true) {
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				exp.setPrivacy(false);
				em.getTransaction().commit();
			} else {
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				exp.setPrivacy(true);
				em.getTransaction().commit();
			}

			session.setAttribute("expList", expDao.findByUser(user));
		}
		// if change an education's privacy is clicked
		String privacyEdParam = request.getParameter("privacy_ed");
		if (privacyEdParam != null) {
			EducationDAO edDao = new EducationDAOImpl();
			Education ed = edDao.find(Integer.parseInt(privacyEdParam));
			if (ed.getPrivacy() == true) {
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				ed.setPrivacy(false);
				em.getTransaction().commit();
			} else {
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				ed.setPrivacy(true);
				em.getTransaction().commit();
			}

			session.setAttribute("edList", edDao.findByUser(user));
		}
		// if change a skill's privacy is clicked
		String privacySkParam = request.getParameter("privacy_sk");
		if (privacySkParam != null) {
			SkillDAO skDao = new SkillDAOImpl();
			Skill sk = skDao.find(Integer.parseInt(privacySkParam));
			if (sk.getPrivacy() == true) {
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				sk.setPrivacy(false);
				em.getTransaction().commit();
			} else {
				EntityManager em = EntityManagerHelper.getEntityManager();
				em.getTransaction().begin();
				sk.setPrivacy(true);
				em.getTransaction().commit();
			}

			session.setAttribute("skList", skDao.findByUser(user));
		}

		request.getRequestDispatcher("/edit_profile.jsp").forward(request, response);
	}

}
