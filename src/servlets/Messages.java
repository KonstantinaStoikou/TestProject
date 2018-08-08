package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class Messages
 */
@WebServlet("/messages")
public class Messages extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String u = request.getParameter("user");
		
		UserDAO dao = new UserDAOImpl();
		User messagedUser = dao.findByEmail(u);
		System.out.println(messagedUser.getEmail());
		request.setAttribute("messagedUser", messagedUser);
		request.getRequestDispatcher("/messages.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dopost gia na stelnei minimata
	}

}
