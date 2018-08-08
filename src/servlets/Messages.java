package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDAO;
import dao.MessageDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Message;
import model.User;

/**
 * Servlet implementation class Messages
 */
@WebServlet("/messages")
public class Messages extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String u = request.getParameter("user");

		UserDAO dao = new UserDAOImpl();
		User messagedUser = dao.find(Integer.parseInt(u));

		request.setAttribute("messagedUser", messagedUser);
		request.getRequestDispatcher("/messages.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");
		String receiverId = request.getParameter("receiver");
		UserDAO dao = new UserDAOImpl();

		User sender = dao.findByEmail(email);
		User receiver = dao.find(Integer.parseInt(receiverId));

		System.out.println(receiver.getEmail());

		String text = request.getParameter("text");

		MessageDAO Msgdao = new MessageDAOImpl();
		Message msg = new Message();
		msg.setSender(sender);
		msg.setReceiver(receiver);
		msg.setText(text);
		Msgdao.create(msg);

		session.setAttribute("conversations", sender.getConversations());

		request.setAttribute("messagedUser", receiver);
		request.getRequestDispatcher("/messages.jsp").forward(request, response);
	}

}
