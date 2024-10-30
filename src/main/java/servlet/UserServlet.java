package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.UserDAO;
import daoImpl.UseDAOImpl;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ManagerFactoryUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet( urlPatterns = {"/api/v1/user", "/api/v1/user/*"} )
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private ManagerFactoryUtils managerFactoryUtils;
	private UserDAO userDAO;  
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public void  init () throws ServletException {
    	super.init();
    	managerFactoryUtils = new ManagerFactoryUtils();
    	userDAO = new UseDAOImpl(managerFactoryUtils.getEntityManager());
    }
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		   response.setContentType("application/json");
	       response.setCharacterEncoding("UTF-8");
	       String pathInfo = request.getPathInfo();
	       Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
	       PrintWriter out = response.getWriter();
	       try {
				if (pathInfo == null || pathInfo.equals("/")) {
					System.out.println("PathInfo: " + pathInfo);
					   	List<User> users = new ArrayList<User>();
						users = userDAO.getAllUser();
			            users.forEach(user -> user.setCartId(null));
			            response.setStatus(HttpServletResponse.SC_OK);
			            out.print(gson.toJson(users));
				}
				else if("/role".equals(pathInfo)) {
					int id = Integer.parseInt(request.getParameter("id"));
					response.setStatus(HttpServletResponse.SC_OK);
				
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
		}    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
         response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
         PrintWriter out = response.getWriter();
         try {
           String pathInfo = request.getPathInfo();
           if(pathInfo == null || pathInfo.equals("/user/checklogin")) {
        	   
        	   String UserName = request.getParameter("UserName");
        	   String Password = request.getParameter("Password");
        	   Boolean check = userDAO.checkLogin(UserName, Password);
        	   if(check) {
					response.setStatus(HttpServletResponse.SC_OK);
					out.print(gson.toJson("Login success"));
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					out.print(gson.toJson("Login fail"));
        	   }
        
           }
        } catch (Exception e) {
		e.printStackTrace();
	} finally {
		out.flush();
		out.close();
	}

}
}
