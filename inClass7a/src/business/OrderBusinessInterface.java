package business;

import java.util.List;

import javax.ejb.Local;

import beans.Order;

@Local
public interface OrderBusinessInterface {
	public void test();
	public List<Order> getOrders();
	public void setOrders(List<Order> orders);
	public void sendOrder(Order order);
}
