package business;


import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import beans.Order;
import data.OrdersDataService;

@Stateless
@Local(OrderBusinessInterface.class)
@Alternative
public class OrdersBusinessService implements OrderBusinessInterface {

	OrdersDataService service;
	List<Order> orders = new ArrayList<Order>();
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from #{User.firstName}. Order Business Service Version");
	}
	
	public OrdersBusinessService() {
		
		
	}
	
	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return service.findAll();
	}


	@Override
	public void setOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		
	}

	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;

	@Override
	public void sendOrder(Order order) {
		// TODO Auto-generated method stub
		try 
		{
			Connection connection = connectionFactory.createConnection();
			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage Message1 = session.createTextMessage();
			Message1.setText("This is test message");
			messageProducer.send(Message1);
			
			ObjectMessage Message2 = session.createObjectMessage();
			Message2.setObject((Serializable) order);
			messageProducer.send(Message2);
			connection.close();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}	

		
	}
	

}
