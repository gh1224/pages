package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="image", urlPatterns="/image/*")
public class ImageServer extends HttpServlet {
	private static final String ATTACHED_PATH = "D:/attached_file";	// MultipartFrontController와 같게
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String conPath = req.getContextPath() + req.getServletPath();
		String com = uri.substring(conPath.length());
		
		File imgFile = new File(ATTACHED_PATH + URLDecoder.decode(com, "utf-8"));
		FileInputStream fin = new FileInputStream(imgFile);
		ServletOutputStream sout = resp.getOutputStream();
		sout.write(fin.readAllBytes());
		fin.close();
	}
}
