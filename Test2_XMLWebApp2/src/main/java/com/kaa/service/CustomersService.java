package com.kaa.service;

import java.math.BigDecimal;

import com.kaa.XMLentities.Customer;
import com.kaa.XMLentities.Customers;
import com.kaa.XMLentities.Order;
import com.kaa.XMLentities.Position;

public class CustomersService {

	private Customers customers;
	private BigDecimal maxOrderCost;
	private BigDecimal totalCostAllCustomersOrdersR;
	private int numOfOrdersR;
	
	
	CustomersService (){
		customers = null;		
	}
	
	public CustomersService (Customers customers){
		this.customers = customers;		
	}
	
	// 3.1 Сумму всех заказов	
	public String totalCostAllCustomersOrders() {
		BigDecimal totalCostAllCustomersOrders = new BigDecimal(0);

		for (Customer cus : customers.getCustomer()) {
			for (Order order : cus.getOrders().getOrder()) {
				for (Position pos : order.getPositions().getPosition()) {
					
					totalCostAllCustomersOrders = totalCostAllCustomersOrders.add((pos.getPrice().multiply(new BigDecimal(
							String.valueOf(pos.getCount())))));
				}
			}
			;
		}
		totalCostAllCustomersOrdersR = totalCostAllCustomersOrders;		
		System.out.println("3.1 Сумму всех заказов "+ totalCostAllCustomersOrders);
		return "3.1 Сумму всех заказов "+ totalCostAllCustomersOrders;

	}
	
	// 3.2 Клиента с максимальной суммой заказов	
	public String cusWithMaxCostOfAllOrders() {
		String cusWithMaxCostOfAllOrders = "0";
		String CostOfAllOrdersCus = "0";
		BigDecimal maxCostOfAllOrders = new BigDecimal(0);
		BigDecimal CostOfAllOrders = new BigDecimal(0);

		for (Customer cus : customers.getCustomer()) {
			
			if (CostOfAllOrders.compareTo(maxCostOfAllOrders) == 1) {
				cusWithMaxCostOfAllOrders = CostOfAllOrdersCus;
				maxCostOfAllOrders = CostOfAllOrders;				
			}
			CostOfAllOrdersCus = cus.getName();
			
			CostOfAllOrders = new BigDecimal(0);
			for (Order order : cus.getOrders().getOrder()) {

				for (Position pos : order.getPositions().getPosition()) {
					CostOfAllOrders = CostOfAllOrders.add((pos.getPrice().multiply(new BigDecimal(String.valueOf(pos
							.getCount())))));
				}
			}
			;
		}
		if (CostOfAllOrders.compareTo(maxCostOfAllOrders) == 1) {
			cusWithMaxCostOfAllOrders = CostOfAllOrdersCus;
			maxCostOfAllOrders = CostOfAllOrders;				
		}
		System.out.println("3.2 Клиента с максимальной суммой заказов " + cusWithMaxCostOfAllOrders);
		return "3.2 Клиента с максимальной суммой заказов " + cusWithMaxCostOfAllOrders;

	}
	
	// 3.3 Сумму максимального заказа
	public String maxOrderCost() {

		BigDecimal maxOrderCostM = new BigDecimal(0);
		BigDecimal MaxOrderCostItr = new BigDecimal(0);

		for (Customer cus : customers.getCustomer()) {
			
			for (Order order : cus.getOrders().getOrder()) {
				
				if (MaxOrderCostItr.compareTo(maxOrderCostM) == 1) {
					maxOrderCostM = MaxOrderCostItr;				
				}		
				MaxOrderCostItr = new BigDecimal(0);

				for (Position pos : order.getPositions().getPosition()) {
					MaxOrderCostItr = MaxOrderCostItr.add((pos.getPrice().multiply(new BigDecimal(String.valueOf(pos
							.getCount())))));
				}
			}
			;
		}
		if (MaxOrderCostItr.compareTo(maxOrderCostM) == 1) {
			maxOrderCostM = MaxOrderCostItr;				
		}		
		maxOrderCost = maxOrderCostM;
		System.out.println("3.3 Сумму максимального заказа " + maxOrderCostM);
		return "3.3 Сумму максимального заказа " + maxOrderCostM;
	}
	
	// 3.4 Сумму минимального заказа
	public String minOrderCost() {	
		
		BigDecimal MinOrderCost = maxOrderCost;
		BigDecimal MinOrderCostItr = maxOrderCost;

		for (Customer cus : customers.getCustomer()) {
			
			for (Order order : cus.getOrders().getOrder()) {
				
				if (MinOrderCostItr.compareTo(MinOrderCost) == -1) {
					MinOrderCost = MinOrderCostItr;				
				}		
				MinOrderCostItr = new BigDecimal(0);

				for (Position pos : order.getPositions().getPosition()) {
					MinOrderCostItr = MinOrderCostItr.add((pos.getPrice().multiply(new BigDecimal(String.valueOf(pos
							.getCount())))));
				}
			}
			;
		}
		if (MinOrderCostItr.compareTo(MinOrderCost) == -1) {
			MinOrderCost = MinOrderCostItr;				
		}		

		System.out.println("3.4 Сумму минимального заказ " + MinOrderCost);
		return "3.4 Сумму минимального заказ " + MinOrderCost;
	}
	
	// 3.5 Количество заказов
	public String numOfOrders() {
		int numOfOrders = 0;
		for (Customer cus : customers.getCustomer()) {

			for (@SuppressWarnings("unused") Order order : cus.getOrders().getOrder()) {
				numOfOrders++;
			}
			;
		}
		numOfOrdersR = numOfOrders;
		System.out.println("3.5 Количество заказов " + numOfOrders);
		return "3.5 Количество заказов " + numOfOrders;
	}
	
	// 3.6 Средняя сумма заказа
	public String everegeOrderPrice() {
		BigDecimal everegeOrderPrice = totalCostAllCustomersOrdersR.divide(new BigDecimal(String.valueOf(numOfOrdersR)), 1, BigDecimal.ROUND_HALF_EVEN);

		System.out.println("3.6 Средняя сумма заказа " + everegeOrderPrice);
		return "3.6 Средняя сумма заказа " + everegeOrderPrice;
	}
	
	
	
}
