package com.newdemo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class TokenProcessor {
	private TokenProcessor() {
		
	}
	
	private static final TokenProcessor instance=new TokenProcessor();
	
	public static TokenProcessor getInstance(){
		return instance;
	}
	
	public String maketoken(){
		String token=(System.currentTimeMillis()+new Random().nextInt(999999999))+"";
		try {
			MessageDigest md=MessageDigest.getInstance("md5");
			byte[] md5=md.digest(token.getBytes());
			
			BASE64Encoder encoder=new BASE64Encoder();
			token=encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
}
