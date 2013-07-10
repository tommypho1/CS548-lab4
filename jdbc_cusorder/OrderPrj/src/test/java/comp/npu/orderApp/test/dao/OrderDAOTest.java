package comp.npu.orderApp.test.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.npu.orderApp.dao.OrderDAO;
import com.npu.orderApp.domain.Order;


@ContextConfiguration("classpath:orderdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderDAOTest {
	
	@Autowired
	@Qualifier("orderDaoJdbc")
	private OrderDAO orderDAO;

	@Test
	public void testOrderCount() {
		int cnt = orderDAO.getOrderCount();
		System.out.println(cnt);
	}
	
	@Test
	public void testFindAllOrders() {
		List<Order> orders = orderDAO.findAllOrders();
		int orderCnt = orderDAO.getOrderCount();
		assertTrue(orders.size() == orderCnt);
		System.out.println(orders);
	}
	
	@Test
	public void testFindOrderById() {
		String expectedCusNum = "5B999";
		Order order = orderDAO.findOrderById(1);
		String cusNum = order.getCusnum();
		assertEquals(cusNum, expectedCusNum);
		System.out.println(order);
	}
	
	@Test
	public void testInsertOrder() {
		Order orderFromDb;
		GregorianCalendar today = new GregorianCalendar();
		Date todayDate = today.getTime();
		
		int newOrderId;
		Order order = new Order(null, "6C779");
		order.setAmount(75.0F);
		order.setDate(todayDate);
		
		/* The id is set by the insert function */
		orderDAO.insertOrder(order);
		newOrderId = order.getId();
		
		/* Verify that an order id was assigned */
		assertTrue(newOrderId > 0);
		orderFromDb = orderDAO.findOrderById(newOrderId);
		assertEquals(order, orderFromDb);
		System.out.println(order.getId());
	}
}
