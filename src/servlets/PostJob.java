package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String position = request.getParameter("position");
		String company = request.getParameter("company");
		String description = request.getParameter("description");

		List<String> skills = new ArrayList<String>();
		Integer count = 1; // variable to count skill input ids
		String skill = request.getParameter(count.toString());
		while (skill != null) {
			skills.add(skill);
			count++;
			skill = request.getParameter(count.toString());
		}

		System.out.println(position);
		System.out.println(company);
		System.out.println(description);
		System.out.println(skills);
	}

}
