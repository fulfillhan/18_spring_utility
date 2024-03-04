package com.application.utility.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 
 	# 선언적 트랜잭션 (Declarative Transaction)
 
 	선언적 트랜잭션 관리에서는 @Transactional 어노테이션을 메소드나 클래스에 추가함으로써, 
 	해당 메소드나 클래스 내에서 실행되는 모든 데이터베이스 연산을 하나의 트랜잭션으로 묶을 수 있다.
 
 */

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Transactional
	public void bankTransfer() {
	
		/*
		  
			예시 1) 
			은행에서 계좌를 이체하는경우 
			계좌의 돈을 송금하고 입금하여 잔액을 업데이트하는 
			일련의 과정들을 모두 트랜잭션으로 처리해야한다.
		
		*/
		
        transactionDAO.transfer();
		
		//System.out.println(0/0); // 에러 발생
		
		transactionDAO.deposit();
		
		
	}
	
	@Transactional
	public void addOrder() {
		
		/*
		  
			예시 2)
	
			전자상거래 이용중 구매절차를 이용하는 경우
			포인트 업데이트 , 장바구니 저장 품목수 업데이트 , 주문건수 증가 등등 다양한 단계가 존재한다.
			이 전체 과정은 '주문'이라는 하나의 큰 트랜잭션이다.
			모든 단계가 성공적으로 완료되어야 최종적으로 상품을 구매할 수 있다.
			
		*/
		
		
		transactionDAO.increasePoints();
		transactionDAO.decreaseCartQty();
		System.out.println(0/0);  // 에러 발생
		transactionDAO.increaseOrerQty();
		
	}
	
	
}
