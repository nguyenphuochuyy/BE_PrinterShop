package entity;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "CartItem")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CartItemId")
	private int id;
	@ManyToOne
	@JoinColumn(name = "CartId")
	private Cart cartId;
	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product productId;
	@Column(name = "Quantity")
	private int quantity;
	@Column(name = "TotalPrice")
	private double totalPrice;
	@Column(name = "Price")
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cart getCartId() {
		return cartId;
	}
	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
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
	public CartItem(int id, Cart cartId, Product productId, int quantity, double totalPrice, double price) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.price = price;
	}
	
	
}
