package space.crack;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringToSha256Routine implements EncryptionRoutine {

	final static String SHA256 = "SHA-256";
	final static String UTF8 = "UTF-8";
	
	private MessageDigest md;
	
	public StringToSha256Routine() {
		md = null;
		try {
			md = MessageDigest.getInstance(SHA256);
		} catch (NoSuchAlgorithmException e)
		{
			System.err.println(SHA256 + " was not found.");;
		}
	}
	
	@Override
	public byte[] encrypt(String string) {

		try {
			md.update(string.getBytes(UTF8));
		} catch (UnsupportedEncodingException e) {
		}
	    return md.digest();

	}

}
