package space.crack;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringToSha256ToBase64Routine implements EncryptionRoutine {

	final static String SHA256 = "SHA-256";
	final static String UTF8 = "UTF-8";
	
	
	@Override
	public String encrypt(String string) {
		MessageDigest md;
		try {
			
			md = MessageDigest.getInstance(SHA256);
			md.update(string.getBytes(UTF8));
		    return Base64.getEncoder().encodeToString(md.digest());
		    
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

		}
	    return null;
	}

}
