package represetation;

public class HexRepresentation implements ByteRepresentation {

	@Override
	public String getAsString(byte[] input) {
		
		StringBuilder sb = new StringBuilder();
	    for (byte b : input) {
	    	
	    	//assuming that we are looking for a lowercase Hex string without spaces
	    	sb.append(String.format("%02X ", b).toLowerCase().trim());
	    }

		return sb.toString();
		
	}

}
