package com.gentleflo.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gentleflo.memo.common.FileManagerService;
import com.gentleflo.memo.post.dao.PostDAO;
import com.gentleflo.memo.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		
		String imagePath = null;  
		// imagePath가 필수 항목이 아니기 때문에 파일이 있을 경우에만 진행되도록 저장 로직 진행
		if(file != null ) {
			imagePath = FileManagerService.saveFile(userId, file);
			// saveFile에서 저장 실패 한 경우
			if(imagePath == null) {
				return 0; // 문제가 생겼다는 것을 표시해줄 수 있도록 리턴 0을 해줌 -> controller에서 count가 0으로 처리되서 예외처리됨
			}
		}

		return postDAO.insertPost(userId, subject, content, imagePath);
	}
	// 복잡한건 controller보다 bo에서 처리하는 것이 좋음 그리고 따로 클래스로 만들어서 사용하는 것이 좋다~!
	
	
	public List<Post> getMemoList(int userId) {
		return postDAO.selectMemoList(userId);
	}
	
	
	public Post getMemo(int id, int userId) {
		return postDAO.selectMemo(id, userId);
	}
	
	
	// 메모 삭제
	public int deleteMemo(int id, int userId) { 
		Post post = this.getMemo(id, userId);
		if(post.getImagePath() != null) {
			FileManagerService.removeFile(post.getImagePath());
		}
		return postDAO.deleteMemo(id, userId);
	}
	
	// 메모 업데이트
	public int updateMemo(int id, String subject, String content, int userId) {
		return postDAO.updateMemo(id, subject, content, userId);
	}
}
