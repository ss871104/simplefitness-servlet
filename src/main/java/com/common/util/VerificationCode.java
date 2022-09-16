package com.common.util;

import java.util.Random;

public class VerificationCode {
	
	public String getRandom() {
		
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		
		return String.format("%06d", number);
	}
	

}
