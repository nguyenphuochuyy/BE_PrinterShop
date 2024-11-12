package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItem")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CartItemId")
	private int id;
	@ManyToOne
	@JoinColumn(name = "ProductId" , nullable = false)
	private Product product;
	@ManyToOne
	@JoinColumn(name = "UserId" , nullable = false)
	private User user;
	@Column(name = "Quantity" , columnDefinition = "int")
	private int quantity;
	@Column(name = "TotalPrice" , columnDefinition = "float")
	private double totalPrice;
	@Column(name = "Price" , columnDefinition = "float")
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProductId() {
		return product;
	}
	public void setProductId(Product productId) {
		this.product = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CartItem(int id, Product productId, int quantity, double totalPrice, double price) {
		super();
		this.id = id;
		this.product = productId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.price = price;
	}


}
