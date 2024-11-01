package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.UserDAO;
import daoImpl.UseDAOImpl;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ManagerFactoryUtils;
import utils.TokenUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = {"/api/v1/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private ManagerFactoryUtils managerFactoryUtils;
		private UserDAO userDAO;     
		private TokenUtil tokenUtil;
    /**
     * @see HttpServlet#HttpServlet()
     */
		  public void  init () throws ServletException {
		    	super.init();
		    	managerFactoryUtils = new ManagerFactoryUtils();
		    	userDAO = new UseDAOImpl(managerFactoryUtils.getEntityManager());
		    }
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		 // Thiết lập response là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        PrintWriter out = response.getWriter();
//        JsonObject jsonObject = new JsonObject();
     // Đọc dữ liệu JSON từ body
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        // Phân tích JSON để lấy username và password
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        boolean check = userDAO.checkLogin(username, password);
        if(check) {
        	String role = userDAO.getRoleUser(username);
        	String token = tokenUtil.generateToken(username);
        	jsonObject.addProperty("message", "Login success");
        	jsonObject.addProperty("status", 200);
        	jsonObject.addProperty("token", token);
        	jsonObject.addProperty("role", role);
        	jsonObject.addProperty("username", username);
        }
        else
		{
			jsonObject.addProperty("message", "Login fail");
			jsonObject.addProperty("status", 404);
		}
        out.print(jsonObject.toString());
        out.flush();
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
