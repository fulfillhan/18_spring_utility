package com.application.utility.scheduler;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//스케쥴링,트랜잭션,export
//기본패키지에 @EnableScheduling추가


@Service
public class ScedulerService {
	
	@Autowired
	private SchedulerDAO schedulerDAO;
	
	@Scheduled(cron="59 59 23 * * * ")//(cron="초 분 시 일 월 요일 (연도)")
	void ScedulerServiceEx() {
		System.out.println("비즈니스 로직");
	}
	@Scheduled(cron="0 34 15 * * *")
	void getProductList() {
			
		System.out.println("00시 스케쥴러");
		
		List<Map<String, Object>> productList = schedulerDAO.getProductList();
		for (Map<String, Object> map : productList) {
			System.out.println(map);
		}
		System.out.println();
		
		}
	
	@Scheduled(cron="0 35 15 * * *")
	void getBrandList() {
			
		System.out.println("00시 스케쥴러");
		
		List<Map<String, Object>> brandList = schedulerDAO.getBrandList();
		for (Map<String, Object> map : brandList) {
			System.out.println(map);
		}
		System.out.println();
		
		}
		
	
}
