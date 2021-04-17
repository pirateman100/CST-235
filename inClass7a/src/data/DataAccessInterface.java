package data;

import java.util.List;



import beans.Order;


public interface DataAccessInterface {

	public List<Order> findAll();
}
