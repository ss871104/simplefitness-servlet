package com.common.adapter;

import static com.common.util.Constants.BASE64;

import java.util.Base64;

public class Base64Adapter {
	
	public String Encoder(byte[] b) {
		String s = BASE64 + Base64.getEncoder().encodeToString(b);
		return s;
	}
	
	public byte[] Decoder(String s) {
		
		s = s.replace(BASE64, "");
		byte[] b = Base64.getDecoder().decode(s);
		return b;
	}

}
