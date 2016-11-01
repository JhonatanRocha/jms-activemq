package com.jms.activemq;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

//JAX-B
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private Calendar orderDate;
	private Calendar paymentDate;
	private BigDecimal amountTotal;
	
	@XmlElementWrapper(name="items")
	@XmlElement(name="item")
	private Set<Item> items = new LinkedHashSet<>();
	
	
	Order() {
	}

	public Order(Integer code, Calendar orderDate, Calendar paymentDate, BigDecimal amountTotal) {
		this.code = code;
		this.orderDate = orderDate;
		this.paymentDate = paymentDate;
		this.amountTotal = amountTotal;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public Integer getCode() {
		return code;
	}

	public Calendar getOrderDate() {
		return orderDate;
	}

	public Calendar getPaymentDate() {
		return paymentDate;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public Set<Item> getItems() {
		return Collections.unmodifiableSet(this.items);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + code + ", dataPedido=" + orderDate + ", dataPagamento=" + paymentDate
				+ ", valorTotal=" + amountTotal + ", itens=" + items + "]";
	}

	
	
}
