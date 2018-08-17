package servlets;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

@WebServlet("/fileServlet")
public class FileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filename = request.getParameter("filename");

		OutputStream os = null;
		try {
			InputStream is = FileUtils.openInputStream(new File(filename));
			byte[] buf = new byte[8192];
			int bytesRead;
			os = new BufferedOutputStream(response.getOutputStream());
			while ((bytesRead = is.read(buf)) != -1)
				os.write(buf, 0, bytesRead);
		} finally {
			os.close();
		}

	}
}
