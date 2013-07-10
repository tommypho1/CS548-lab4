package com.npu.orderApp.dao;

import java.util.List;

import com.npu.orderApp.domain.Order;

public interface OrderDAO {
	public int getOrderCount();
	public List<Order> findAllOrders();
	public Order findOrderById(int id);
	public void insertOrder(Order order);
	public List<Order> findOrdersByCustAndAmtGtrThan(Order order);
}
