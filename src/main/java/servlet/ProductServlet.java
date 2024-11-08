package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.ProductDAO;
import daoImpl.ProductDAOImpl;
import entity.Category;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ManagerFactoryUtils;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet( urlPatterns = {"/api/v1/products", "/api/v1/products/*"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private ManagerFactoryUtils managerFactoryUtils;
	  private ProductDAO productDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
  	public void  init () throws ServletException {
      	super.init();
      	managerFactoryUtils = new ManagerFactoryUtils();
      	productDAO = new ProductDAOImpl(managerFactoryUtils.getEntityManager());
      }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	        Gson gson = new GsonBuilder().serializeNulls().create();
	        PrintWriter out = response.getWriter();
	        String pathInfo = request.getPathInfo();
	        JsonObject jsonObject = new JsonObject();
	        ObjectMapper objectMapper = new ObjectMapper(); // Khởi tạo ObjectMapper của Jackson
	        try {
	 				if (pathInfo == null || pathInfo.equals("/")) {
	 					   List<Product> products = new ArrayList<>();
	 					  products = productDAO.getAllProducts();
	 					   if(products == null) {
	 						   jsonObject.addProperty("message", "No products found");
	 						   response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	 					   }
	 					   else{
	 						  jsonObject.addProperty("message", "Products found");
	 		                  jsonObject.addProperty("total", products.size());
	 		                  // dùng ObjectMapper để chuyển đổi object java thành JSON
	 		                String json = objectMapper.writeValueAsString(products);
	 		                // dùng gson để chuyển đổi json thành JsonObject
	 		                jsonObject.add("data", gson.fromJson(json, JsonArray.class));
	 		                jsonObject.addProperty("status", response.getStatus());
	 		                  out.println(jsonObject.toString());

	 		               }
	 				}
	 				else {
	 					jsonObject.addProperty("message", "Invalid request");
	 					jsonObject.addProperty("status", HttpServletResponse.SC_BAD_REQUEST);
	 					out.println(jsonObject.toString());
	 				}
			}
	 		 catch (Exception e) {
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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		String pathInfo = request.getPathInfo();
		Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
		JsonObject jsonObject = new JsonObject(); // tạo một đối tượng JSON Object để chứa thông tin trả về cho client
		PrintWriter out = response.getWriter(); // dùng để ghi thông tin trả về cho client
		try {
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(gson.toJson(jsonObject));
			out.flush();
			out.close();
		
	}

	}
	
}
