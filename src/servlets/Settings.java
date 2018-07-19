package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Settings
 */
@WebServlet("/settings")
public class Settings extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hiddenParam = request.getParameter("action");
		System.out.println(hiddenParam);
		if (hiddenParam.equals("email_change")) {
			System.out.println("email change");
		} else if (hiddenParam.equals("password_change")){
			System.out.println("password change");
		}
		request.getRequestDispatcher("/settings.jsp").forward(request, response);
		}

}
