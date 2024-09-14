package hirschdaniel.javaBruteforcer;

/**
 * An interface for an encryption routine. An encryption routine specifies a way to get from String A to the encrypted String B.
 * @author Daniel Hirsch
 *
 */
public interface EncryptionRoutine {

	public byte[] encrypt(String string);
	
}
