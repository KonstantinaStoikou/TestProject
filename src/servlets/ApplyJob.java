package servlets;

import java.io.IOException;
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
import dao.UserDAO;
import dao.UserDAOImpl;
import jpautils.EntityManagerHelper;
import model.Job;
import model.User;

/**
 * Servlet implementation class ApplyJob
 */
@WebServlet("/applyJob")
public class ApplyJob extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");
		UserDAO userDao = new UserDAOImpl();
		User user = userDao.findByEmail(email);

		String jobId = request.getParameter("job");
		JobDAO jobDao = new JobDAOImpl();
		Job job = jobDao.find(Integer.parseInt(jobId));

		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		user.addAppliedJob(job);
		job.addAppliedUsers(user);
		em.getTransaction().commit();

		// remove job from session attribute list of recommended jobs
		List<Job> jobs = (List<Job>) session.getAttribute("recommendedJobs");
		for (Job j : jobs) {
			if (j.getId() == job.getId()) {
				jobs.remove(j);
				break;
			}
		}
		session.setAttribute("recommendedJobs", jobs);

		request.getRequestDispatcher("/jobs.jsp").forward(request, response);
	}

}
