package com.lding.wiqs.general.web.validate;

import java.util.Random;

/**
 * 随机字符串
 * @author xxg
 *
 */
public class Randoms {
	private static final Random RANDOM = new Random();
	private static final char ALPHA[] = { 
				'A','B','C','D','E','F','G','H',    'G','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				'a','b','c','d','e','f','g','h','i','j','k',    'm','n',    'p','q','r','s','t','u','v','w','x','y','z',
				'1','2','3','4','5','6','7','8','9' };
	
	public static int num(int min,int max) {
		return min + RANDOM.nextInt(max-min);
	}
	
	public static int num(int max) {
		return num(0, max);
	}
	
	public static char alpha() {
		return ALPHA[num(ALPHA.length)];
	}
	
	public static void main(String[] args) {
		System.out.println(alpha());
	}

}
