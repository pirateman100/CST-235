package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.User;
import business.MyTimerService;
import business.OrderBusinessInterface;

@ManagedBean
@SessionScoped
public class FormController {
	@Inject
	OrderBusinessInterface services;
	
	@EJB
	MyTimerService timer;
	
	public String onLogOff() {
		// Invalidate the Session to clear the security token
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
		// Redirect to a protected page (so we get a full HTTP Request) to get Login Page
		return "TestResponse.xhtml?faces-redirect=true";

	}
	
	public OrderBusinessInterface getService() {
		return services;
	}
	
	private void getAllOrders() {
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "root";
		String sql = "SELECT * FROM testapp.orders";
		List<Order> orders = new ArrayList<Order>();
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int ID =  rs.getInt("id");
				String Name = rs.getString("product_name");
				float Price = rs.getFloat("price");
				System.out.println("ID is " + ID + " for Product is " + Name + " for Price is " + Price);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	} 
	
	public void insertOrder() {
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "root";
		String sql = "INSERT INTO testapp.orders(order_no, product_name, price, quanity) VALUES ('0121258', 'New Prod', 87.00, 234)";
		
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			Statement stmt = conn.createStatement();
			 stmt.executeUpdate(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	public String onSendOrder(Order orders) {
		services.sendOrder(orders);
		return "OrderResponse.xhtml";
	}
}
