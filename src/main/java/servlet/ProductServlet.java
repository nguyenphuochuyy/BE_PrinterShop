package servlet;

import java.io.BufferedReader;
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

import dao.CategoryDAO;
import dao.ProductDAO;
import daoImpl.CategoryDAOImpl;
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
	  private CategoryDAO categoryDAO;
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
      	categoryDAO = new CategoryDAOImpl(managerFactoryUtils.getEntityManager());
      }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   	response.setContentType("application/json;charset=UTF-8");
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
	        Gson gson1 = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
	        System.out.println("pathInfo: " + pathInfo);
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
	 		                  // get category name by id of each product in list products then add to json object
	 		                  
	 		                  
	 		                  // dùng ObjectMapper để chuyển đổi object java thành JSON
	 		                String json = objectMapper.writeValueAsString(products);
	 		                // dùng gson để chuyển đổi json thành JsonObject
	 		                jsonObject.add("data", gson.fromJson(json, JsonArray.class));
	 		                jsonObject.addProperty("status", response.getStatus());
	 		                  out.println(jsonObject.toString());
	 		               }
	 				}
	 				else {
	 		            // Lấy ID sản phẩm từ URL
	 		            String idParam = pathInfo.substring(1); // Bỏ dấu "/"
	 		            try {
	 		            	 String[] pathParts = pathInfo.split("/");
	 		                int id = Integer.parseInt(pathParts[1]);
	 		                // Gọi DAO để lấy sản phẩm theo ID
	 		                Product product = productDAO.getProductById(id);
	 		                if (product == null) {
	 		                    jsonObject.addProperty("message", "Sản phẩm không tồn tại");
	 		                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	 		                } else {
	 		                    jsonObject.addProperty("message", "Lấy sản phẩm thành công");
	 		                       // dùng ObjectMapper để chuyển đổi object java thành JSON
	 		                    String json = objectMapper.writeValueAsString(product);
	 		                    // dùng gson để chuyển đổi json thành JsonObject
	 		                   jsonObject.add("data", gson.fromJson(json, JsonObject.class));	
	 		                    response.setStatus(HttpServletResponse.SC_OK);
	 		                   out.println(jsonObject.toString());
	 		                  
	 		                }
	 		            } catch (NumberFormatException e) {
	 		                jsonObject.addProperty("message", "ID không hợp lệ");
	 		                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	 		            }
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
			if (pathInfo == null || pathInfo.equals("/add")) {
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String imguri = request.getParameter("imguri");
				int inStock = Integer.parseInt(request.getParameter("inStock"));
				double price = Double.parseDouble(request.getParameter("price"));
			    String sizePage = request.getParameter("sizePage");
			    int ram = Integer.parseInt(request.getParameter("ram"));
			    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			    Category category = categoryDAO.getCategoryById(categoryId);
			    // nếu các giá trị trên bằng null hoặc 0 thì trả về status code 400
				if (name  == null || price == 0 || categoryId == 0 || inStock == 0 ||ram == 0) {
					jsonObject.addProperty("message", "Invalid request");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					
				}
				// nếu các giá trị kia bằng null thì gán giá trị rỗng cho chúng
				else if(description == null || imguri == null || sizePage == null) {
					description = "";
					imguri = "";
					sizePage = "";
				}
				if (category == null) {
					jsonObject.addProperty("message", "Category not found");
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
				else {
					Product product = new Product(name, price, description, imguri, sizePage, ram, inStock, category);
					boolean addProduct = productDAO.addProduct(product);
					if (addProduct) {
						jsonObject.addProperty("message", "Thêm sản phẩm thành công");
						jsonObject.addProperty("status", HttpServletResponse.SC_OK);
					} else {
						jsonObject.addProperty("message", "Thêm sản phẩm không thành công");
						jsonObject.addProperty("status", HttpServletResponse.SC_BAD_REQUEST);
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					}
				}
			   
			}
			// xử lý khi client gửi request không hợp lệ va trả về status code 400
			else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(gson.toJson(jsonObject));
			out.flush();
			out.close();
		
	}

	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		String pathInfo = request.getPathInfo();
//		Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
		JsonObject jsonObject = new JsonObject();
		PrintWriter out = response.getWriter();
	    StringBuilder sb = new StringBuilder();
	    try (BufferedReader reader = request.getReader()) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	        }
	    }
	    String jsonBody = sb.toString();
	    Gson gson = new Gson();
	    Product product = gson.fromJson(jsonBody, Product.class);
	    Category category = categoryDAO.getCategoryById(product.getCategoryId());
		if (category == null) {
			jsonObject.addProperty("message", "Category not found");
			jsonObject.addProperty("status", HttpServletResponse.SC_NOT_FOUND);
			out.print(gson.toJson(jsonObject));
		}
		product.setCategory(category);
		try {	
		
			if (pathInfo == null || pathInfo.equals("/")) 
				{
				 jsonObject.addProperty("message", "Invalid request");
                 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                 out.print(gson.toJson(jsonObject));
                 return;
				}
			
				boolean updateProduct = productDAO.updateProduct(product);
				if (updateProduct) {
					jsonObject.addProperty("message", "Cập nhật sản phẩm thành công");
					jsonObject.addProperty("status", HttpServletResponse.SC_OK);
				} else {
					jsonObject.addProperty("message", "Cập nhật sản phẩm không thành công");
					jsonObject.addProperty("status", HttpServletResponse.SC_BAD_REQUEST);
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}	
			} 
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(gson.toJson(jsonObject));
			out.flush();
			out.close();
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String pathInfo = request.getPathInfo();
        Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
        JsonObject jsonObject = new JsonObject();
        PrintWriter out = response.getWriter();
        try {
        	   
            if (pathInfo == null || pathInfo.equals("/")) {
            	jsonObject.addProperty("message", "Invalid request");
            	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            else {
            	
            	String idParam = pathInfo.substring(1);
                int id = Integer.parseInt(idParam);
                boolean deleteProduct = productDAO.deleteProduct(id);
                if (!deleteProduct) {
                    jsonObject.addProperty("message", "Không tìm thấy sản phẩm !");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } 
                else {
                    jsonObject.addProperty("message", "Xóa thành công !");
                    jsonObject.addProperty("status", HttpServletResponse.SC_OK);
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            } 
            
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.print(gson.toJson(jsonObject));
            out.flush();
            out.close();
        }
    }
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setStatus(HttpServletResponse.SC_OK);
	}

	
}
