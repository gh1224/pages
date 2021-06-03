package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="frontController", urlPatterns = "/front/*")
public class FrontController extends HttpServlet {
	protected Map<String, Controller> controllerMap = new HashMap<>();
	
	public FrontController() {
//		controllerMap.put("", new Controller());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String conPath = req.getContextPath() + req.getServletPath();
		String com = uri.substring(conPath.length());
		
		if (com.equals("/")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewResolver("index"));
			dispatcher.forward(req, resp);
		} else {
			String[] tokens = com.split("/");
			Controller controller = controllerMap.get(tokens[1]);
			if (controller == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			ModelAndView mv = controller.process(req, resp, com);
			if (mv == null)		// redirect
				return;
			if (mv.getStatus() != HttpServletResponse.SC_OK)
			{
				resp.setStatus(mv.getStatus());
				return;
			}
			String viewPath = viewResolver(mv.getViewName());
			View view = new View(viewPath);
			view.render(mv.getModel(), req, resp);
		}
	}
	
	private String viewResolver(String viewName) {
		return "/WEB-INF/view/" + viewName + ".jsp";
	}
}
