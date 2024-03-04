package com.application.utility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.utility.transaction.TransactionService;

@SpringBootTest
public class TransactionTest {
	
	@Autowired
	private TransactionService transactionService;
	
	@Test
	public void bankTransfer() {
		transactionService.bankTransfer();
	}
	
	@Test
	public void addOrder() {
		transactionService.addOrder();
	}

}
