package com.npu.orderApp.dao.jdbc;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.npu.orderApp.dao.ProductDAO;

@Repository("productDaoJdbc")
public class ProductDaoJdbcImpl implements ProductDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getProductCount() {
		String sql = "select count(*) from Product";
		return jdbcTemplate.queryForInt(sql);
	}

	public int findTotalOrdersByName(String prodName) {
		String sql = "select totalOrders from Product where name=?";
		return jdbcTemplate.queryForInt(sql,prodName);
	}
	
	public List<String> findProdsWithLessThanTotalOrder(int orderCnt) {
		String sql = "select name from Product where totalOrders<?";
		return jdbcTemplate.queryForList(sql, String.class, orderCnt);
	}
	
	public String findProdNameById(int id) {
		String sql = "select name from Product where id=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}
	
}
