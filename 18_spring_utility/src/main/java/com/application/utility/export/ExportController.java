package com.application.utility.export;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;

/*

	# Excel Export (poi)
	
	- Apache POI는 Java에서 Microsoft Office 형식의 파일을 읽고 쓰기 위한 라이브러리이다. 
	- 스프링 부트(Spring Boot) Apache POI 라이브러리를 사용하여 Excel 파일의 데이터를 정리하고 보고서를 생성할 수 있다. 
	
	[ 구현방법 ]
	
	1) build.gradle파일에 의존성을 추가한다.
	
	// excel export
	implementation 'org.apache.poi:poi:5.2.2'
	implementation 'org.apache.poi:poi-ooxml:5.2.2'
	
	
	2) 컨트롤러에서 export 기능을 구현한다. (자세한 속성 및 사용 메뉴얼은 검색 하며 사용)
	
	- (import)
	import java.io.IOException;
    import java.net.URLEncoder;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	@GetMapping("/excelExport")
	public void excelExport(HttpServletResponse response) {
  
		try {
			 
			// 파일명
			String fileName = "엑셀파일다운로드";
			
			// sheet명
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("시트명");
			
			// 헤더
			String[] header = {"컬럼1", "컬럼2", "컬럼3", "컬럼4", "컬럼5"};
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
			    Cell cell = row.createCell(i);
			    cell.setCellValue(header[i]);
			}
	
			// 본문
			for (int i = 1; i < 10; i++) {
				
				row = sheet.createRow(i);  
				
				Cell cell = null;
				cell = row.createCell(0);
				cell.setCellValue("컬럼1 데이터");
				
				cell = row.createCell(1);
				cell.setCellValue("컬럼2 데이터");
				
				cell = row.createCell(2);
				cell.setCellValue("컬럼3 데이터");
				
				cell = row.createCell(3);
				cell.setCellValue("컬럼4 데이터");
				
				cell = row.createCell(4);
				cell.setCellValue("컬럼5 데이터");
				
			}

			// 엑셀 파일 생성 및 다운로드
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();
	
		} catch(IOException e) {
		    e.printStackTrace();
		}
	}

*/

@Controller
@RequestMapping("/export")
public class ExportController {
	
	@GetMapping("/excelExport")
	public void excelExport(HttpServletResponse response) {
  
		try {
			 
			// 파일명
			String fileName = "엑셀파일다운로드";
			
			// sheet명
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("이름");
			
			// 헤더
			String[] header = {"컬럼1", "컬럼2", "컬럼3", "컬럼4", "컬럼5", "칼럼6"};
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
			    Cell cell = row.createCell(i);
			    cell.setCellValue(header[i]);
			}
	
			// 본문
			for (int i = 1; i < 10; i++) {
				
				row = sheet.createRow(i);  
				
				Cell cell = null;
				cell = row.createCell(0);
				cell.setCellValue("컬럼1 데이터");
				
				cell = row.createCell(1);
				cell.setCellValue("컬럼2 데이터");
				
				cell = row.createCell(2);
				cell.setCellValue("컬럼3 데이터");
				
				cell = row.createCell(3);
				cell.setCellValue("컬럼4 데이터");
				
				cell = row.createCell(4);
				cell.setCellValue("컬럼5 데이터");
				
				cell = row.createCell(5);
				cell.setCellValue("컬럼6 데이터");
				
			}

			// 엑셀 파일 생성 및 다운로드
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();
	
		} catch(IOException e) {
		    e.printStackTrace();
		}
	}
	
}
