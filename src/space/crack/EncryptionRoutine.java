package space.crack;

/**
 * An interface for an encryption routine. An encryption routine specifies a way to get from String A to the encrypted String B.
 * @author User
 *
 */
public interface EncryptionRoutine {

	public String encrypt(String string);
	
}
