 package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;


@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId" , columnDefinition = "int")
	private int id;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	@Column(name = "TotalPrice" , columnDefinition = "float")
	private double totalPrice;
	@Column(name = "Status" , columnDefinition = "nvarchar(255)")
	private String status;
	@Column(name = "ShippingAddress" , columnDefinition = "nvarchar(255)")
	private String shippingAddress;
	@Column(name = "CreateAt" , columnDefinition = "date")
	private LocalDate createAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUserId() {
		return user;
	}
	public void setUserId(User userId) {
		this.user = userId;
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
	public Order(int id, User userId,double totalPrice, String status, String shippingAddress,
			LocalDate createAt) {
		super();
		this.id = id;
		this.user = userId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.shippingAddress = shippingAddress;
		this.createAt = createAt;
	}


}
