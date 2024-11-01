package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
		   response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	        response.setHeader("Access-Control-Allow-Credentials", "true");
         Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
         PrintWriter out = response.getWriter();
         JsonObject jsonObject = new JsonObject();
         try {
           String pathInfo = request.getPathInfo();
           System.out.println("PathInfo: " + pathInfo);
           // xử lý thêm mới 1 user vào db
           if(pathInfo.equals("/add")) {
        	   	String username = request.getParameter("username");
        	   	String password = request.getParameter("password");
        	   	String role = request.getParameter("role");
        	   	String email = request.getParameter("email");
        	   	String phone = request.getParameter("phone");
        	   	User user = new User(username, role, password, email, phone);
        	   	boolean isAddUser = userDAO.addUser(user);
        	   	if(isAddUser) {
					response.setStatus(HttpServletResponse.SC_OK);
					jsonObject.addProperty("message", "Add user success");
					jsonObject.addProperty("status", HttpServletResponse.SC_CREATED);
					
				} else {
					jsonObject.addProperty("message", "Add user fail");
					jsonObject.addProperty("status", HttpServletResponse.SC_BAD_REQUEST);
					
        	   	}
        	   	out.print(gson.toJson(jsonObject));
           }
         
        } catch (Exception e) {
		e.printStackTrace();
	} finally {
		out.flush();
		out.close();
	}
  }
	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
		PrintWriter out = response.getWriter();
		JsonObject jsonObject = new JsonObject();
		try {
			String pathInfo = request.getPathInfo();
			if (pathInfo.equals("/update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String role = request.getParameter("role");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				User user = userDAO.getUserById(id);
				if (user == null) {
					jsonObject.addProperty("message", "Update fail, user not found");
					jsonObject.addProperty("status", HttpServletResponse.SC_NOT_FOUND);
					out.print(gson.toJson(jsonObject));
					return;
				}
				else {
					user.setUsername(username);
					user.setPassword(password);
					user.setRole(role);
					user.setEmail(email);
					user.setPhone(phone);
					boolean isUpdate = userDAO.updateUser(user);
					if (isUpdate) {
                        response.setStatus(HttpServletResponse.SC_OK);
                        jsonObject.addProperty("message", "Update user success");
                        jsonObject.addProperty("status", HttpServletResponse.SC_OK);
                    } else {
                        jsonObject.addProperty("message", "Update user fail");
                        jsonObject.addProperty("status", HttpServletResponse.SC_BAD_REQUEST);
				}

				out.print(gson.toJson(jsonObject));
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	 @Override
	    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Header CORS cho preflight request
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	        response.setStatus(HttpServletResponse.SC_OK);
	    }
}
