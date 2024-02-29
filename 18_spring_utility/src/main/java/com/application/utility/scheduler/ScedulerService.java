package com.application.utility.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//기본패키지에 @EnableScheduling추가
@Service
public class ScedulerService {
	@Scheduled(cron="*/5 20,21,22 * * * * ")//(cron="초 분 시 일 월 요일 (연도)")
	void ScedulerServiceEx() {
		System.out.println("비즈니스 로직");
	}
}
