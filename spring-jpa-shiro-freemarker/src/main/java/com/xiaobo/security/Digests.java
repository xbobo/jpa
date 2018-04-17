package com.xiaobo.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang.Validate;

public class Digests {
	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";

	private static SecureRandom random = new SecureRandom();

	public static byte[] sha1(byte[] input) {
		return digest(input, SHA1, null, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt) {
		return digest(input, SHA1, salt, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, SHA1, salt, iterations);
	}

	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw  new RuntimeException(e);
		}
	}

	
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	
	public static byte[] md5(InputStream input) throws IOException {
		return digest(input, MD5);
	}

	
	public static byte[] sha1(InputStream input) throws IOException {
		return digest(input, SHA1);
	}

	private static byte[] digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return messageDigest.digest();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		
		/**
		 * 加密
		 */
		byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
		System.out.println("old:"+salt);
		String saltStr=Encodes.encodeHex(salt);
		byte[] decodeHex = Encodes.decodeHex(saltStr);
		System.out.println("new:"+decodeHex);
		String password="a123456";
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, Encodes.HASH_INTERATIONS);
		String passwordencod=Encodes.encodeHex(hashPassword);
		System.out.println(passwordencod);
		//System.out.println("test");
		/*byte[] salt=Encodes.decodeHex("4330b05d7ed9d0e8");
		String password="a123456";
		byte[] hashPassword = Digests.sha1(password.getBytes(),salt,Encodes.HASH_INTERATIONS);
		String passwordEncode=Encodes.encodeHex(hashPassword);
		System.out.println(passwordEncode);*/
		
		/**
		 * 解密
		 */
		
		/*byte[] salt1 = Encodes.decodeHex(saltStr);
		byte[] hashPassword1 = Digests.sha1(descrypedPwd.getBytes(), salt,Encodes.HASH_INTERATIONS);*/
		 
	}
}
