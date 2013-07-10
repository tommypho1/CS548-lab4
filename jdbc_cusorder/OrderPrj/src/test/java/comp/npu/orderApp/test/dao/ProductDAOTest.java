package comp.npu.orderApp.test.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.npu.orderApp.dao.OrderDAO;
import com.npu.orderApp.dao.ProductDAO;

@ContextConfiguration("classpath:orderdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDAOTest {
	@Autowired
	@Qualifier("productDaoJdbc")
	private ProductDAO productDAO;

	@Test
	public void testProductCount() {
		int cnt = productDAO.getProductCount();
		System.out.println(cnt);
	}
	
	@Test
	public void testTotalOrdersByProdName() {
		int totalOrders = productDAO.findTotalOrdersByName("Z500");
		System.out.println(totalOrders);
	}
	
	@Test
	public void testLessThanOrderCount() {
		List<String> prods = productDAO.findProdsWithLessThanTotalOrder(1);
		System.out.println(prods.size());
		System.out.println(prods);
	}
	
	@Test
	public void testFindProdNameById() {
		String expectedProdName = "X2200";
		String name = productDAO.findProdNameById(2);
		assertEquals(name, expectedProdName);
	}
}