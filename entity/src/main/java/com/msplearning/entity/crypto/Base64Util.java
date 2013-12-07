package com.msplearning.entity.crypto;

import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * The Base64Util class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class Base64Util {

	private static final byte[] encryptionKey = new byte[] { 'W', 'g', 's', 'c', 'x', 'c', 'g', 'r', 'F', 'q', 'Q', 'f', 'w', 'v', 'P', 'J' };

	public static String encode(String string) {
		Key key;
		try {
			key = createKey();
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedBytes = c.doFinal(string.getBytes());
			byte[] base64Bytes = Base64.encodeBase64(encryptedBytes);
			return new String(base64Bytes);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public static String decode(String base64String) {
		Key key;
		try {
			key = createKey();
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptedBase64Bytes = Base64.decodeBase64(base64String);
			byte[] decryptedBytes = c.doFinal(decryptedBase64Bytes);
			return new String(decryptedBytes);
		} catch (GeneralSecurityException e) {
			return base64String;
		}
	}

	private static Key createKey() {
		Key key = new SecretKeySpec(Base64Util.encryptionKey, "AES");
		return key;
	}

}