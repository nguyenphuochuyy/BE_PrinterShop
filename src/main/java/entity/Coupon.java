package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Coupon")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CouponId")
	private int id;
	@Column(name = "CouponCode")
	private String code;
	@Column(name = "TypeCoupon")
	private String discount_type;
	@Column(name = "ValueCoupon")
	private int discount_value;
	@Column(name = "StartDate")
	private LocalDateTime start_date;
	@Column(name = "EndDate")
	private LocalDateTime end_date;
	@Column(name = "MinPurchase")
	private double min_purchase;
	@Column(name = "UsageLimit")
	private int usage_limit;
	@Column(name = "UsageCount")
	private int usage_count;
	@ManyToMany(mappedBy = "coupons")
	private List<Cart> carts = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDiscount_type() {
		return discount_type;
	}
	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}
	public int getDiscount_value() {
		return discount_value;
	}
	public void setDiscount_value(int discount_value) {
		this.discount_value = discount_value;
	}
	public LocalDateTime getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}
	public LocalDateTime getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}
	public double getMin_purchase() {
		return min_purchase;
	}
	public void setMin_purchase(double min_purchase) {
		this.min_purchase = min_purchase;
	}
	public int getUsage_limit() {
		return usage_limit;
	}
	public void setUsage_limit(int usage_limit) {
		this.usage_limit = usage_limit;
	}
	public int getUsage_count() {
		return usage_count;
	}
	public void setUsage_count(int usage_count) {
		this.usage_count = usage_count;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	public Coupon(int id, String code, String discount_type, int discount_value, LocalDateTime start_date,
			LocalDateTime end_date, double min_purchase, int usage_limit, int usage_count, List<Cart> carts) {
		super();
		this.id = id;
		this.code = code;
		this.discount_type = discount_type;
		this.discount_value = discount_value;
		this.start_date = start_date;
		this.end_date = end_date;
		this.min_purchase = min_purchase;
		this.usage_limit = usage_limit;
		this.usage_count = usage_count;
		this.carts = carts;
	}



}
