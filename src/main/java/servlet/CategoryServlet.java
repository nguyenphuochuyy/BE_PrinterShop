package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dao.CategoryDAO;
import daoImpl.CategoryDAOImpl;
import entity.Category;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ManagerFactoryUtils;

/**
 * README
 * servlet này xử lý các request liên quan đến category
 * doGet: lấy danh sách category
 * doPost: thêm mới category , xóa category
 * doPut: update category
 * test các api này bằng cách sử dụng postman theo url sau http://localhost:8080/BE_PRINTER/api/v1/xxx (xxx là đường dẫn tương ứng)
 * Ví dụ: http://localhost:8080/BE_PRINTER/api/v1/categories để lấy danh sách category
 * http://localhost:8080/BE_PRINTER/api/v1/categories/add để thêm mới category
 * nếu có lỗi xảy ra sẽ trả về status code và message thông báo lỗi ví dụ: {"message":"Category not found"} và status code 404
 */
@WebServlet(urlPatterns = { "/api/v1/categories", "/api/v1/categories/*" })
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerFactoryUtils managerFactoryUtils;
	private CategoryDAO categoryDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		managerFactoryUtils = new ManagerFactoryUtils();
		categoryDAO = new CategoryDAOImpl(managerFactoryUtils.getEntityManager());
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		String pathInfo = request.getPathInfo();
		Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
		ObjectMapper mapper = new ObjectMapper(); // dùng để chuyển đổi object java thành JSON hoặc ngược lại
		PrintWriter out = response.getWriter();
		try {
			if (pathInfo == null || pathInfo.equals("/")) {
				List<Category> categories = new ArrayList<>();
				categories = categoryDAO.getAllCategory();
				out.println(mapper.writeValueAsString(categories));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				Category category = new Category(name, description, imguri, null);
				if (categoryDAO.insertCategory(category)) {
					jsonObject.addProperty("message", "Insert category successfully");
					response.setStatus(HttpServletResponse.SC_CREATED);
				} else {
					jsonObject.addProperty("message", "Insert category failed");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
			}
			else if(pathInfo.equals("/delete")) {
				// Xóa category theo id
                int id = Integer.parseInt(request.getParameter("id")); // lấy id từ request
                boolean deleteCategory = categoryDAO.deleteCategory(id); // gọi hàm xóa category từ categoryDAO và truyền vào id
                if(!deleteCategory) { // nếu xóa không thành công thì trả về thông báo lỗi
                    jsonObject.addProperty("message", "Category not found");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                else {
                	// nếu xóa thành công thì trả về thông báo xóa thành công và status code 200
                        jsonObject.addProperty("message", "Delete category successfully");
                        jsonObject.addProperty("status", HttpServletResponse.SC_OK);
                        response.setStatus(HttpServletResponse.SC_OK);

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

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			if (pathInfo == null || pathInfo.equals("/update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String imguri = request.getParameter("imguri");
				// tìm catgory theo id trước khi update
				Category categoryById = categoryDAO.getCategoryById(id);
				if (categoryById == null) {
					jsonObject.addProperty("message", "Category not found");
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
				else
				{
					categoryById.setProducts(categoryById.getProducts());
					categoryById.setName(name);
					categoryById.setDescription(description);
					categoryById.setImguri(imguri);
					boolean updateCategory = categoryDAO.updateCategory(categoryById);
					if (updateCategory) {
						jsonObject.addProperty("message", "Update category successfully");
						jsonObject.addProperty("status", HttpServletResponse.SC_OK);
						response.setStatus(HttpServletResponse.SC_OK);
					} else {
						jsonObject.addProperty("message", "Update category failed");
						jsonObject.addProperty("status", HttpServletResponse.SC_BAD_REQUEST);
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					}
				}

			}


			else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(gson.toJson(jsonObject));
			out.flush();
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Header CORS cho preflight request
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
