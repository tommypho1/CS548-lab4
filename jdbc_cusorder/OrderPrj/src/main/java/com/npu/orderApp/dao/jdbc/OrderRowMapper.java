package com.npu.orderApp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.npu.orderApp.domain.Order;

public class OrderRowMapper implements RowMapper<Order> {

	public Order mapRow(ResultSet resultSet, int row) throws SQLException {
		String cusnum;
		Date date;
		Order order;
		
		cusnum = resultSet.getString("cusnum");
		date = resultSet.getDate("date");
		order = new Order(date, cusnum);
		
		order.setId(resultSet.getInt("id"));
		order.setAmount(resultSet.getFloat("amount"));
		
		order.setDate(resultSet.getDate("date"));
		return order;
	}

}
