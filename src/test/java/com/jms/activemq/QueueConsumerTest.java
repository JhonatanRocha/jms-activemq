package com.jms.activemq;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueConsumerTest {

	public static void main(String[] args) throws JMSException, NamingException {
		
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		
//		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
		
		Destination queue = (Destination) context.lookup("financial");
		MessageConsumer consumer = session.createConsumer(queue);
		
		//Message receivedMessage = consumer.receive(2000);
		
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				
				TextMessage textMessage = (TextMessage) message;
				
				try {
					//message.acknowledge();
					System.out.println(textMessage.getText());
					//session.commit();
					session.rollback();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}
}
