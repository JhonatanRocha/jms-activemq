package com.jms.activemq;

import java.io.StringWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;

public class TopicProducerTest {

	public static void main(String[] args) throws JMSException, NamingException {
		
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination topic = (Destination) context.lookup("store");
		
		MessageProducer producer = session.createProducer(topic);
		
		Order order = new OrderFactory().generateOrderWithValues();
		
//		StringWriter writer = new StringWriter();
//		
//		JAXB.marshal(order, writer);
//		
//		String xml = writer.toString();
//		
//		System.out.println(xml);
		Message message = session.createObjectMessage(order);
//		message.setBooleanProperty("ebook", true);
		producer.send(message);

		session.close();
		connection.close();
		context.close();
	}
}
