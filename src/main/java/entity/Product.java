package entity;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "Product")
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductId")
	private int id;

	@Column(name = "ProductName", columnDefinition = "nvarchar(255)")
	private String name;

	@Column(name = "Price", columnDefinition = "float")
	private double price;

	@Column(name = "Description", columnDefinition = "nvarchar(255)")
	private String description;

	@Column(name = "Img", columnDefinition = "nvarchar(255)")
	private String img;

	@Column(name = "SizePage", columnDefinition = "nvarchar(255)")
	private String sizePage;

	@Column(name = "Ram", columnDefinition = "int")
	private int ram;
	@Column(name = "InStock" , columnDefinition = "int")
	private int inStock;
	@ManyToOne
	@JoinColumn(name = "id")
	private Category categoryId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSizePage() {
		return sizePage;
	}
	public void setSizePage(String sizePage) {
		this.sizePage = sizePage;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getInStock() {
		return inStock;
	}
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	public Category getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	public Product(int id, String name, double price, String description, String img, String sizePage, int ram,
			int inStock, Category categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.img = img;
		this.sizePage = sizePage;
		this.ram = ram;
		this.inStock = inStock;
		this.categoryId = categoryId;
	}
	
	
}
