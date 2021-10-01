package com.gentleflo.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gentleflo.memo.common.FileManagerService;
import com.gentleflo.memo.common.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	private PermissionInterceptor permissionInterceptor;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 서버 (내 컴퓨터)의 특정 위치의 파일들을
		// 내가 정한 url path로 접근하게 하는 설정
		registry.addResourceHandler("/images/**")   // /images/3_3283479874897/test.png
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionInterceptor)
		.addPathPatterns("/**")  // 어느 path의 접근을 낚아 챌지
		.excludePathPatterns("/static/**", "/user/sign_out");
	}

}
