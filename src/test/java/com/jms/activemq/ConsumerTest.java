package com.jms.activemq;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConsumerTest {

	public static void main(String[] args) throws JMSException, NamingException {
		
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination queue = (Destination) context.lookup("financial");
		MessageConsumer consumer = session.createConsumer(queue);
		
		Message receivedMessage = consumer.receive();
		
		System.out.println("Consumed Message " + receivedMessage);
		
		//new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}
}
