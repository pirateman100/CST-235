package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

@Stateless
@Local(OrderBusinessInterface.class)
@Alternative
public class AnotherOrdersBusinessService implements OrderBusinessInterface {

	List<Order> orders = new ArrayList<Order>();
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from the #{User.firstName}. Another Order Business Service Version");
	}

	public AnotherOrdersBusinessService() {
		orders.add(new Order("A", "1", (float)1.00, 1));
		orders.add(new Order("B", "2", (float)1.10, 1));
		orders.add(new Order("C", "3", (float)0.90, 1));
		orders.add(new Order("D", "4", (float)11.00, 1));
		orders.add(new Order("E", "5", (float)10.00, 1));
		orders.add(new Order("F", "6", (float)20.00, 1));
		
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		this.orders = orders;
	}

	@Override
	public void sendOrder(Order order) {
		// TODO Auto-generated method stub
		
	}
}
