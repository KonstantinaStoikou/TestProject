package servlets;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profilepic")
public class ProfilePic extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		byte[] image = (byte[]) session.getAttribute("photo");

		response.setContentType("image/*");
		response.setContentLength(image.length);
		BufferedOutputStream output = null;
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(image);
			output = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[4096];
			int length = 0;
			while ((length = input.read(buffer)) >= 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException logOrIgnore) {
				}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
