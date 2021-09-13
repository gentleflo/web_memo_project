package com.gentleflo.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	// 암호화를 위한 클래스임!
	// 암호화 메소드
	public static String md5(String message) {  // 객체 생성 생성 주 목적이 멤버변수 만들기 위한 공간을 만드는것
												// 여기서는 멤버 변수 만드는것이 목적이 아닌 클래스 사용을 위해 만드는것이기 때문에
												// 낭비의 느낌이 난다 그래서 static 사용~! (객체 생성 없이 사용하기 위해 단, 멤버변수는 사용할 수 없게됨)
												// static이 붙는 순간 바로 메모리에 올라가서 바로 사용이 가능하게 되는 것
		String encData = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // 자바 내부에 암호화를 위해 이미 만들어져 있는 클래스 우리가 갖다 쓰는 것!
			// 1byte = 8bit = 10101011 : 컴퓨터의 가장 기본 단위 
			// ex) asdf -> a = 64 = 1000000
			//             [1000000, 1010100, 1000101, 101011]
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest(); 
			// byte -> 16진수 -> 문자열
			for(int i = 0; i < digest.length; i++) {
				encData += Integer.toHexString(digest[i] & 0xff);  // 2진수 연산을 통해서 16진수로 바꾸어가는 과정
				// encData는 문자열에 16진수 숫자열을 쭈루룩 붙여주기 위해 쓰는것
			}
	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encData;
			
	}
}
