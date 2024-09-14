package represetation;

import java.util.Base64;

public class Base64Representation implements ByteRepresentation {

	@Override
	public String getAsString(byte[] input) {

		return Base64.getEncoder().encodeToString(input);

	}

}
