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

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class Network
 */
@WebServlet("/network")
public class Network extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String hiddenParam = request.getParameter("action");
		
		if (hiddenParam.equals("search_form")) {
			String searchText = request.getParameter("search");
			request.setAttribute("searchText", searchText);
			String[] searchTextTokens = searchText.split(" "); 
			
			UserDAO dao = new UserDAOImpl();
			//get all users 
			List<User> users = dao.list();
			//list to add relevant to search users
			List<User> results= new ArrayList<User>();
			for (User u : users) {
				//concatenate users' first and last name into one
				String first = u.getFirstName();
				String last = u.getLastName();
				String full = first + " " + last;
				//if input equals the full name
				if (full.toLowerCase().equals(searchText.toLowerCase())) {
					//add at the beginning of list because it matches input the most
					results.add(0, u);   
				}
				//if whole input is contained in full name
				else if (full.toLowerCase().contains(searchText.toLowerCase())) {
					results.add(u);
				}
				else {
					//if any word of the input is contained in full name
					for (String token : searchTextTokens) {
						if (full.toLowerCase().contains(token.toLowerCase())) {
							results.add(u);
						}
					}
				}				
			}
			
			request.setAttribute("results", results);
			
			request.getRequestDispatcher("/search_results.jsp").forward(request, response);
		}
		else if (hiddenParam.equals("visit_user")) {
			int id = Integer.parseInt(request.getParameter("user"));
			UserDAO dao = new UserDAOImpl();
			User user = dao.find(id);
			request.setAttribute("user", user);
			
			request.getRequestDispatcher("/user_profile.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
