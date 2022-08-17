package com.pets1.app.utilerias;

import java.security.SecureRandom;


public class RandomKeyGenerator {
	
	public String clave() {

	    String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	    String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	    String NUMBER = "0123456789";

	    String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	    SecureRandom random = new SecureRandom();

	    StringBuilder sb = new StringBuilder(10);
	    
	    for (int i=0; i<10; i++) {
	        // 0-62 (exclusive), retornos aleatorios 0-61
	        int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
	        char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

	        sb.append(rndChar);
	    }

	    return sb.toString();
	}

}
