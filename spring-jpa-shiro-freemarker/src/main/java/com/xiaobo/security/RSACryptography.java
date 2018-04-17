package com.xiaobo.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;

import org.apache.commons.lang.Validate;

public class RSACryptography {
	
	public static String data="hello world";
	
	private static SecureRandom random = new SecureRandom();
	

	public static void main(String[] args) throws Exception {
		
		System.out.println(new Date().getTime());
		Date date2 = new Date(1515037977000l);
		System.out.println(date2.toLocaleString());
		Date date = new Date(1623586949000l);
		System.out.println(date.toLocaleString());
		/**
		 * 用户登录账号安全原理：
		 * 采用公钥 私钥的方式加密解密密码。将前端加密密码传输到后台，在后台进行解密还原密码，
		 * 最后通过salt对密码加密后存入数据库。数据库不存储铭文密码。
		 * 用户登录通过密码salt解密后，进行校验 。提高安全性
		 */
		
		
		// TODO Auto-generated method stub
		
		KeyPair keyPair=genKeyPair(1024);
		
		//获取公钥，并以base64格式打印出来
		PublicKey publicKey=keyPair.getPublic();		
		System.out.println("公钥："+new String(Base64.getEncoder().encode(publicKey.getEncoded())));
		
		//获取私钥，并以base64格式打印出来
		PrivateKey privateKey=keyPair.getPrivate();		
		System.out.println("私钥："+new String(Base64.getEncoder().encode(privateKey.getEncoded())));
		
		//公钥加密
		byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);	
		System.out.println("加密后："+new String(encryptedBytes));
		
		//私钥解密
		byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);		
		System.out.println("解密后："+new String(decryptedBytes));
	}
	
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}
	
	//生成密钥对
	public static KeyPair genKeyPair(int keyLength) throws Exception{
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);		
		return keyPairGenerator.generateKeyPair();
	}
	
	//公钥加密
	public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
		Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}
	
	//私钥解密
	public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}

}