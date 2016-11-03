package com.jms.activemq.log;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueProducerTest {

	public static void main(String[] args) throws JMSException, NamingException {
		
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination queue = (Destination) context.lookup("LOG");
		
		MessageProducer producer = session.createProducer(queue);
		
		Message message = session.createTextMessage("INFO | ALGO DO LOG VAI AQUI!");
		//TODO: parei aqui
		producer.send(message, DeliveryMode.NON_PERSISTENT, 3, 5000);	
		
//		for (int i = 0; i < 1000; i++) {
//			Message message = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
//			producer.send(message);
//		}

		//new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}
}
