package entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private int id;
	private User userId;
	private Cart cartId;
	@Column(name = "TotalPrice")
	private double totalPrice;
	@Column(name = "Status")
	private String status;
	@Column(name = "ShippingAddress")
	private String shippingAddress;
	@Column(name = "CreateAt")
	private LocalDate createAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Cart getCartId() {
		return cartId;
	}
	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public LocalDate getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}
	public Order(int id, User userId, Cart cartId, double totalPrice, String status, String shippingAddress,
			LocalDate createAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.shippingAddress = shippingAddress;
		this.createAt = createAt;
	}
	
	
}
