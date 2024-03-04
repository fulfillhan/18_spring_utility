package com.application.utility.scheduler;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulerDAO {

	public List<Map<String,Object>> getProductList();
	public List<Map<String,Object>> getBrandList();
	
}
