package space.crack;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class StringToSha256ToBase64Routine implements EncryptionRoutine {

	final static String SHA256 = "SHA-256";
	final static String UTF8 = "UTF-8";
	
	private MessageDigest md;
	private Encoder encoder;
	
	public StringToSha256ToBase64Routine() {
		this.encoder = Base64.getEncoder();
		md = null;
		try {
			md = MessageDigest.getInstance(SHA256);
		} catch (NoSuchAlgorithmException e)
		{
		}
	}
	
	@Override
	public String encrypt(String string) {

		try {
			md.update(string.getBytes(UTF8));
		} catch (UnsupportedEncodingException e) {
		}
	    return encoder.encodeToString(md.digest());

	}

}
