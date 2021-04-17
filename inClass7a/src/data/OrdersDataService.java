package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;


import beans.Order;

@Stateless
public class OrdersDataService implements DataAccessInterface {

	
	List<Order> orders = new ArrayList<Order>();
	
	@Override
	public List<Order> findAll() {
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "root";
		String sql = "SELECT * FROM testapp.orders";
		
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String order_num = rs.getString("order_no");
				String Name = rs.getString("product_name");
				float Price = rs.getFloat("price");
				int quanity = rs.getInt("quantity");
				orders.add(new Order(order_num, Name, Price, quanity));
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
		return orders;
	}

	

}
