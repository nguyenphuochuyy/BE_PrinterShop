package entity;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private int id;
	@Column(name = "CategoryName")
	private String name;
	@Column(name = "Description")
	private String description;
	@Column(name = "ImgUri")
	private String imguri;
	@OneToMany(mappedBy = "categoryId")
	private List<Product> products;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImguri() {
		return imguri;
	}
	public void setImguri(String imguri) {
		this.imguri = imguri;
	}
	public Category(int id, String name, String description, String imguri) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imguri = imguri;
	}
	public Category() {
		super();
	} 
	
	
}
