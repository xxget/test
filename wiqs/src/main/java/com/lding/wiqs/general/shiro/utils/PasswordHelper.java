package com.lding.wiqs.general.shiro.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.lding.wiqs.modular.userinfo.domain.UserInfo;



public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;
	
	public PasswordHelper(String algorithmName, int hashIterations) {
		this.algorithmName = algorithmName;
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(UserInfo user) {
		if((user.getSalt()==null)||"".equals(user.getSalt()))
			user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()),
				hashIterations).toHex();
		user.setPassword(newPassword);
	}
}
