package hirschdaniel.javaBruteforcer;

import java.nio.CharBuffer;

/**
 * A class that provides String manipulation utilities.
 * @author Daniel Hirsch
 *
 */
public class StringUtilities {

	/**
	 * Increments the given string to the next character from the charset.
	 * @param string
	 * @return
	 */
	public static String incrementString(String string, String characterSet)
	{
		final char characterSetLowest = characterSet.charAt(0);
		final char characterSetHighest = characterSet.charAt(characterSet.length()-1);
		StringBuilder sb = new StringBuilder(string);
		
		if(isMaximallyIncremented(string, characterSet))
		{
			//reset the stringBuilder
			sb.setLength(0);
			//initialize it with all lower limit characters
			string.chars().forEach(c -> sb.append(characterSetLowest));
			//add one additional character to increase the length
			sb.append(characterSetLowest);
		}
		else
		{
			for(int i=string.length()-1; i>=0; i--)
			{
				final boolean characterCanBeIncremented = sb.charAt(i) != characterSetHighest;
				if(characterCanBeIncremented)
				{
					char incrementedCharacter = characterSet.charAt(characterSet.indexOf(sb.charAt(i)) + 1);
					sb.setCharAt(i, incrementedCharacter);
					break;
				}
				else
				{
					sb.setCharAt(i, characterSetLowest);
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Returns whether a string is maximally incremented according to the given characterSet.
	 * For example, for characterSet "abc" the string "ccc" is fully incremented.
	 * @param string
	 * @param characterSet
	 */
	public static boolean isMaximallyIncremented(String string, String characterSet)
	{
		final char characterSetHighest = characterSet.charAt(characterSet.length()-1);
		return CharBuffer.wrap(string.toCharArray())
				.chars()
				.asLongStream()
				.allMatch(c -> (c == characterSetHighest));
	}
}
