package com.npu.orderApp.dao;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
	public int getProductCount();
	int findTotalOrdersByName(String prodName);
	public List<String> findProdsWithLessThanTotalOrder(int orderCnt);
	public String findProdNameById(int id);
}
