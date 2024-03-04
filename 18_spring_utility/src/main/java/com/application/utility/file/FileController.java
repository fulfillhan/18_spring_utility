package com.application.utility.file;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*

	
	# Spring 파일 'MultipartFile'
	
	- Spring MVC에서는 MultipartFile을 사용하여 클라이언트로부터 전송된 파일을 받아서 처리할 수 있다.

	- MultipartFile 객체에서 제공되어지는 메서드
	
		getBytes() : 업로드된 파일의 내용을 바이트 배열로 반환한다.
		getContentType() :업로드된 파일의 MIME 유형을 반환한다.
		getInputStream() :업로드된 파일의 InputStream을 반환한다. 이를 통해 파일의 내용을 읽어올 수 있다.
		getOriginalFilename() : 클라이언트가 업로드한 파일의 원래 파일 이름을 반환한다.

	- 스프링 파일업로드 기능 구현

		1) 스프링부트 프로젝트에 파일 업로드기능이 포함되어있어서 의존성을 추가하지 않아도 된다.
	
		2) application.properties 파일에 파일업로드 관련 설정을 추가한다.
		
			# 멀티파트 요청에서 개별 파일의 최대 허용 크기 (기본값 1MB)
			spring.servlet.multipart.max-file-size=30MB
			
			# 멀티파트 요청 전체의 최대 허용 크기 (기본값 10MB)         
			spring.servlet.multipart.max-request-size=100MB
			
			# 파일저장 경로 
			file.repo.path=C:/spring_file_test/
		
	    3) MultipartFile 객체를 사용하여 파일관련 기능을 구현한다.

*/

@Controller
@RequestMapping("/file")
public class FileController {

	/*
	 * 
	 * 웹애플리케이션의 설정정보 위치를 통합하기 위하여 application.properties파일에서 파일저장 위치를 명시하고 @Value
	 * 애노테이션을 사용하여 file.repo.path경로 대입
	 * 
	 * @Value 를 사용하는 이유는? import org.springframework.beans.factory.annotation.Value;
	 * 
	 */
	@Value("${file.repo.path}")
	private String fileRepositoryPath;

	@GetMapping("/fileEx")
	public String fileEx01() {
		return "file/fileEx";
	}

	// 1) 파일 업로드 기초 예시
	@PostMapping("/upload1")
	// @RequestParam("name명") 어노테이션을 사용하여 MultipartFile타입으로 파일을 전달받는다.
	public String upload1(@RequestParam("upFile") MultipartFile upFile) throws IllegalStateException, IOException {

//			System.out.println(multipartFile.getOriginalFilename());
//			System.out.println(multipartFile.getContentType());

		File targetFile = new File(fileRepositoryPath + upFile);// 파일객체를 생성한다.
		// upfile.transferTo(new File(fileRepositoryPath +
		// upfile.getOriginalFilename())); 이렇게 작성도 가능하다.
		upFile.transferTo(targetFile);//// transferTo(파일객체) 메서드를 사용하여 파일저장소에 파일을 저장한다.
		// 해당 작업을 해야 파일에저장된다.

		return "redirect:/file/fileEx";
	}

	// 2) 다중파일 업로드 + 파일명 수정(UUID 적용) 예시
	@PostMapping("/upload2")
	// @RequestParam("name명") 어노테이션을 사용하여 List<MultipartFile>타입으로 파일을 전달받는다.
	public String upload2(@RequestParam("files") List<MultipartFile> files) throws IllegalStateException, IOException {

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {//파일이 비어있지 않다면(파일이 비어있지않아야지 다음 구문실행)
				// 원본파일이름가지고오기
				String originalFilename = file.getOriginalFilename();
				// 범용고용식별자(UUID) 생성
				UUID uuid = UUID.randomUUID();

				// 확장자 추출
				String extenstion = originalFilename.substring(originalFilename.indexOf("."));

				// 업로드 파일 이름 수정
				String uploadFileName = uuid + extenstion;

				// transferTo() 메서드를 사용하여 파일 업로드 구현
				// file.transferTo(new File(fileRepositoryPath+ file.getOriginalFilename()));
				file.transferTo(new File(fileRepositoryPath + uploadFileName));
			}
		}
		return "redirect:/file/fileEx";
	}

	/*
	 * 
	 * import org.springframework.core.io.InputStreamResource; import
	 * org.springframework.core.io.Resource; import
	 * org.springframework.core.io.UrlResource; import
	 * org.springframework.http.ContentDisposition; import
	 * org.springframework.http.HttpHeaders; import
	 * org.springframework.http.HttpStatus; import
	 * org.springframework.http.ResponseEntity; import java.nio.file.Path; import
	 * java.nio.file.Paths;
	 * 
	 */

	// 3) 썸네일기능 구현 예시
	@GetMapping("/thumbnails")
	@ResponseBody
	public Resource thumbnails(@RequestParam("fileName") String fileName) throws MalformedURLException {
		//new UrlResource("file :"+ fileRepositoryPath+fileName);
		// new UrlResource("file:" + 파일접근경로) 객체를 반환하여 이미지를 조회한다.
		return new UrlResource("file :"+ fileRepositoryPath+fileName);// 이미지 경로 수정하여 사용
	}
	

	// 4) 파일 다운로드 기능 구현 예시	
	@GetMapping("downloadFile")
	public ResponseEntity<Object> downloadFile(@RequestParam("fileName") String fileName) {
		String downloadFile = fileRepositoryPath+fileName;//파일 경로
		
		try {
			
			Path filePath = Paths.get(downloadFile);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			
			File file = new File(downloadFile);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
			
		} catch(Exception e) {
			
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
		
	}

	// 5) 파일 삭제 예시
	@PostMapping("/delete")
	public String delete(@RequestParam("deleteFileName") String deleteFileName) {
		 File file = new File(fileRepositoryPath+deleteFileName);
		 
		 if(file.exists()) {// 파일이 존재하면
			 file.delete(); // 삭제한다
		 }
		 return "redirect:/file/fileEx";
	}

	// 6) 파일 수정 예시(지우고 업데이트 한다는 전제하)
	@PostMapping("/update")
	public String update(@RequestParam("deleteFileName") String deleteFileName,
			              @RequestParam("modifyFile") MultipartFile modifyFile) throws IllegalStateException, IOException {
		
		File deleteFile = new File(fileRepositoryPath+deleteFileName);
		
		if(deleteFile.exists() && !modifyFile.isEmpty()) {
			deleteFile.delete();// 파일 삭제한다.
			
			 String  originalFilename = modifyFile.getOriginalFilename();
			 	
			 UUID uuid = UUID.randomUUID();
			 
			 String extension = originalFilename.substring(originalFilename.indexOf("."));
			 
			 String uploadFile = uuid + extension;
			 
			 modifyFile.transferTo(new File(fileRepositoryPath+uploadFile));
		}
		
		return "redirect:/file/fileEx";
		
	}
}
