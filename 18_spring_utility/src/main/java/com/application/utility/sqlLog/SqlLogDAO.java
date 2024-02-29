package com.application.utility.sqlLog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SqlLogDAO {

	public List<Map<String,Object>> getBrandList();
	public Map<String,Object> getBrandDetail(long brandId);
	public void createBrand(Map<String,Object> brandMap);
	public void updateBrand(Map<String,Object> brandMap);
	public void deleteBrand(long brandId);
	
}
