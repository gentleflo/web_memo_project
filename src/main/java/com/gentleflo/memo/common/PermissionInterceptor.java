package com.gentleflo.memo.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class PermissionInterceptor implements HandlerInterceptor{
	// 요청이 들어올때
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		// 로그인 상태에 따른 접근 권한 관리
		HttpSession session = request.getSession();
		
		// 현재 요청한 uri(path) 알아 오기
		String uri = request.getRequestURI();
		
		// 비로그인
		if(session.getAttribute("userId") == null) {
			// 리스트 화면, 디테일 화면, 글쓰기 화면 안보이도록
			// /post/** : post로 시작하는 모든것들 접근 못하도록 -> 로그인 페이지로 이동
			if(uri.startsWith("/post")) {
				response.sendRedirect("/user/signin_view");  // false일때
				return false;
			}
		} else {   // 로그인
			// 로그인 화면, 회원 가입화면 안보이도록
			// /user/** : user로 시작하는 모든것들 접근 못하도록 -> 리스트 페이지로 이동
			// 이 경우에 로그아웃도 포함이 되어 버리기 때문에 포함되지 않도록 WebMvcConfig에서 exclude 해주는 것이다
			if(uri.startsWith("/user")) {
				response.sendRedirect("/post/list_view");
				return false;
			}
		}
		return true;  // true로 리턴하면 사용자가 요청한 요청대로 controller로 이동
	}
	
	// response 처리 할때
	@Override
	public void postHandle(HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, ModelAndView modelAndView) {
	}
	
	// 모든 것이 완료 되었을때 
	@Override
	public void afterCompletion(HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, Exception ex) {
	}
}
