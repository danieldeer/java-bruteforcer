package represetation;


/**
 * The representation of a byte[] array.
 * Intended to convert the byte[] to a String.
 * @author User
 *
 */
public interface ByteRepresentation {

	/**
	 * Converts the given byte[] to a String.
	 * @param input
	 * @return
	 */
	public String getAsString(byte[] input);
	
}
