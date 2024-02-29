package com.application.utility;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.utility.sqlLog.SqlLogDAO;

@SpringBootTest
public class SqlLog_Test {

	@Autowired
	private SqlLogDAO sqlLogDAO;
	
	@Test
	public void getBrandList() {
		sqlLogDAO.getBrandList();
	}
	
	@Test
	public void getBrandDetail() {
		long brandId = 1;
		sqlLogDAO.getBrandDetail(brandId);
	}
	
	@Test
	public void createBrand() {
		Map<String, Object> brandMap = new HashMap<String, Object>();
		brandMap.put("brandId", 100);
		brandMap.put("brandNm", "추가된브랜드");
		brandMap.put("activeYn", "Y");
		sqlLogDAO.createBrand(brandMap);
	}
	
	@Test
	public void updateBrand() {
		Map<String, Object> brandMap = new HashMap<String, Object>();
		brandMap.put("brandId", 100);
		brandMap.put("brandNm", "추가된브랜드1");
		sqlLogDAO.updateBrand(brandMap);
	}
	
	@Test
	public void deleteBrand() {
		long brandId = 100;
		sqlLogDAO.deleteBrand(brandId);
	}
	
}
