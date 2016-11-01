package com.jms.activemq;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderFactory {

	public Order generateOrderWithValues() {
		Order order = new Order(2459, generateDate("22/12/2016"), generateDate("23/12/2016"), new BigDecimal("145.98"));
		
		Item motoG = generateItem(23,"Moto G");
		Item motoX = generateItem(51,"Moto X");
		
		order.addItem(motoX);
		order.addItem(motoG);
		
		return order;

	}

	private Item generateItem(int id, String nome) {
		return new Item(id,nome);
	}

	private Calendar generateDate(String dataComString) {
		try {
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataComString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			return cal;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
}
