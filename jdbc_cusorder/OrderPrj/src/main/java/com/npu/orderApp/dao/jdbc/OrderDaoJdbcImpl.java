package com.npu.orderApp.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;
import com.npu.orderApp.domain.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.npu.orderApp.dao.OrderDAO;

@Repository("orderDaoJdbc")
public class OrderDaoJdbcImpl implements OrderDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private OrderRowMapper orderRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		orderRowMapper = new OrderRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("CusOrder")
		                 .usingGeneratedKeyColumns("id")
		                 .usingColumns("cusnum", "date", "amount");
	}
	
	public int getOrderCount() {
		String sql = "select count(*) FROM CusOrder";
		return jdbcTemplate.queryForInt(sql);
	}
	
	public List<Order> findAllOrders() {
		String sql = "SELECT * FROM CusOrder";
		List<Order> orderList = jdbcTemplate.query(sql, orderRowMapper);
		return orderList;
	}
	
	public void insertOrder(Order order) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(order);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	    order.setId(newId.intValue());
	}
	

	public List<Order> findOrdersByCustAndAmtGtrThan(Order order) 
		{
				String sql = 
		        "SELECT * FROM CusOrder WHERE cusNum=:cusnum AND amount>:amount";
				BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(order);
				List<Order> orderList = dbTemplate.query(sql, params,
		                 orderRowMapper);
				return orderList;
		}
	
	
	public Order findOrderById(int cusId) {
		String sql = "SELECT * FROM CusOrder WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", cusId);
		Order order = dbTemplate.queryForObject(sql, params, orderRowMapper);

		return order;
	}
	
}
