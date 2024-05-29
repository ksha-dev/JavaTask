package runner;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionRunner {
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";
	private static final String KEY_TEXT = "abc";
	private static SecretKey KEY;

	static {
		try {
			KEY = new SecretKeySpec(
					String.format("%12s", KEY_TEXT + System.currentTimeMillis()).getBytes(Charset.forName("UTF-8")),
					ALGORITHM);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setKey(long key) {
		String keyText = (key % (10 ^ 16)) + "";
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		Random random = new Random();
		for (int i = 1; i <= 40; i++) {
			String s = "";
			for (int j = 1; j <= i; j++) {
				s = s + (j % 10);
			}
			System.out.println(s + " : " + encrypt(s));
		}
	}

	public static String encrypt(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, KEY);
		String encryptedValue = Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
		return encryptedValue;
	}

	public static String decrypt(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, KEY);

		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(value));
		String decryptedText = new String(decryptedBytes);
		System.out.println(decryptedText);
		return decryptedText;
	}
}
//package runner;
//
//import java.nio.charset.Charset;
//import java.security.GeneralSecurityException;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//public class EncryptionRunner {
//
//	public static void main(String[] args) {
//		try {
//
//			String key = "ThisIsASecretKey";
//			byte[] ciphertext = encrypt(key, "1234567890123456");
//			System.out.println("decrypted value:" + (decrypt(key, ciphertext)));
//
//		} catch (GeneralSecurityException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static byte[] encrypt(String key, String value) throws GeneralSecurityException {
//
//		byte[] raw = key.getBytes(Charset.forName("UTF-8"));
//		if (raw.length != 16) {
//			throw new IllegalArgumentException("Invalid key size.");
//		}
//
//		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
//		return cipher.doFinal(value.getBytes(Charset.forName("UTF-8")));
//	}
//
//	public static String decrypt(String key, byte[] encrypted) throws GeneralSecurityException {
//
//		byte[] raw = key.getBytes(Charset.forName("UTF-8"));
//		if (raw.length != 16) {
//			throw new IllegalArgumentException("Invalid key size.");
//		}
//		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
//		byte[] original = cipher.doFinal(encrypted);
//
//		return new String(original, Charset.forName("UTF-8"));
//	}
//}
