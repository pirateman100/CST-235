package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRootElement;

import business.OrderBusinessInterface;

@XmlRootElement(name="Order")
public class Order {
	String orderNo = "";
	String productName = "";
	float price = 0;
	int quantity = 0;
	
	@Inject
	OrderBusinessInterface services;
	
	
	public  Order(String orderNo, String productName, float price, int quantity){
		this.orderNo = orderNo;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		
	}
	
	@PostConstruct
	public void init() {
		services.getOrders();
		return;
	}
	
	


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
