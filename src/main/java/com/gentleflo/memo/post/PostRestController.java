package com.gentleflo.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gentleflo.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	@Autowired
	private PostBO postBO;
	

	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("subject") String subject
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.getAttribute("userId");      //getAttribute의 리턴 타입은 Object임 그래서 타입을 맞추는 캐스팅이 필요
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postBO.addPost(userId, subject, content);
		
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;

	}

}
