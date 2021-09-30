package com.gentleflo.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;


public class FileManagerService {
	
	public final static String FILE_UPLOAD_PATH = "D:\\이나은\\memo_upload/";
	// 대문자로 변수 이름을 만들어준것은 -> 수정될 일도 없고 수정되어서도 않되는 변수이기 때문에~! -> final
	public static String saveFile(int userId, MultipartFile file) {  
		// 파일 경로
		// 파일 이름이 중복이 될 가능성이 있다! 해결 방법은?
		// 1. 올린 사람의 id로 구분해서 저장 -> but 같은 사람이 같은 파일을 올릴 수도 있다 
		// 2. 올린 시간을 기준으로 구분한다 -> 1번의 맹점 보완
		// /43_2345535049528/test.png -> 파일이름 앞에 디렉토리를 더해서 저장
		// 1970년 1월1일을 기준으로 몇 밀리초(1/1000)가 지났는지를 위의 예시 "_" 다음의 숫자처럼 사용
		 
//		Logger logger = LoggerFactory.getLogger(this.getClass());
		
		// 43_2345535049528/
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		// ex) C:\\Users\\LEE\\Desktop\\springTest\\upload\\images/43_2345535049528/
		String filePath = FILE_UPLOAD_PATH + directoryName;   // 전체 경로
		
		// 디렉토리 생성 (디렉토리도 결국 파일임)
		File directory = new File(filePath);
		if(directory.mkdir() == false) {    // mkdir : make directory의 약자, 리턴값이 boolean을 가진 메소드
			// 디렉토리 생성 에러
//			logger.error("[FileManagerService saveFile] 디렉토리 생성 에러");
			return null;
		}
		
		// 파일을 저장하기 위해서는 byte 배열 형태로 만들어진 변수가 있어야 함
		try {
			byte[] bytes = file.getBytes();
			// 파일 경로
			Path path = Paths.get(filePath + file.getOriginalFilename());
			// 파일 저장
			Files.write(path, bytes);
			
		} catch (IOException e) {
//			logger.error("[FileManagerService saveFile] 파일 생성 실패");
			e.printStackTrace();
			return null;
		}
		
		// 파일을 접근할 수 있는 경로를 리턴하여 db에 저장할 수 있도록 만들어주기
		// <img src= " ">  " " 안에 들어갈 경로를 만들어 주는 것!
		// 밑에 "/images/" -> 이건 그냥 정해주는것!
		return "/images/" + directoryName + file.getOriginalFilename();
	}
	
	public static void removeFile(String filePath) {
		// filePath
		// post 테이블에 있는 imagePath
		// ex> /images/01-293824899/test.png   --> 접근하기 위한 경로 (실제 파일 경로 아님)
		// 실제 경로 ex> D:\\이나은\\memo_upload\images\01-293824899\test.png
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		
		Path path = Paths.get(realFilePath); // path는 파일경로를 좀 더 다루기 쉽게 해놓은 클래스임
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		// 디렉토리(폴더) 삭제
		path = path.getParent();
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
