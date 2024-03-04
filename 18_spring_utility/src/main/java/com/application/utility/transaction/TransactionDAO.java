package com.application.utility.transaction;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionDAO {

	public void transfer();
	public void deposit();
	
	public void increasePoints();
	public void decreaseCartQty();
	public void increaseOrerQty();
	
}
