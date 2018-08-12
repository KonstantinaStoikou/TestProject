package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import dao.JobDAOImpl;
import dao.Job_SkillDAO;
import dao.Job_SkillDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import jpautils.EntityManagerHelper;
import model.Job;
import model.Job_Skill;
import model.User;

/**
 * Servlet implementation class PostJob
 */
@WebServlet("/postJob")
public class PostJob extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");
		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail(email);

		String position = request.getParameter("position");
		String company = request.getParameter("company");
		String description = request.getParameter("description");

		JobDAO jobDao = new JobDAOImpl();
		Job job = new Job();
		job.setUser(user);
		job.setPosition(position);
		job.setCompany(company);
		job.setDescription(description);
		jobDao.create(job);

		List<Job_Skill> jobSkills = new ArrayList<Job_Skill>();
		Integer count = 1; // variable to count skill input ids
		String skill = request.getParameter(count.toString());
		while (skill != null) {
			Job_SkillDAO jobSkDao = new Job_SkillDAOImpl();
			Job_Skill jobSk = new Job_Skill();
			jobSk.setName(skill);
			jobSk.setJob(job);
			jobSkDao.create(jobSk);

			jobSkills.add(jobSk);
			count++;
			skill = request.getParameter(count.toString());
		}

		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		job.setJobSkills(jobSkills);
		em.getTransaction().commit();

	}

}
