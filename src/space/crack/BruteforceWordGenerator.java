package space.crack;

import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BruteforceWordGenerator implements WordGenerator{
	
	private final String characterSet;
	
	private String currentWord;
	
	/**
	 * Sets up the word generator with a characterSet String that contains all characters that should be used for generation.
	 * @param characterSet
	 */
	public BruteforceWordGenerator(String characterSet)
	{
		this.currentWord = "";
		this.characterSet = characterSet;
	}
	
	@Override
	public String next()
	{
		this.currentWord = incrementString(currentWord);
		return currentWord;
	}
	
	
	/**
	 * Increments the given string to the next character from the charset.
	 * @param string
	 * @return
	 */
	private String incrementString(String string)
	{
		final char characterSetLowest = characterSet.charAt(0);
		final char characterSetHighest = characterSet.charAt(characterSet.length()-1);
		StringBuilder sb = new StringBuilder(string);
		final boolean stringIsMaximallyIncremented = CharBuffer.wrap(string.toCharArray())
																.chars()
																.asLongStream()
																.allMatch(c -> (c == characterSetHighest));
		
		
		if(stringIsMaximallyIncremented)
		{
			//reset the stringBuilder
			sb.setLength(0);
			//initialize it with all lower limit characters
			string.chars().forEach(c -> sb.append(characterSetLowest));
			//add one additional character to increase the length
			sb.append(characterSetLowest);
			Logger.getGlobal().log(Level.INFO, "WordGenerator reached " + sb.length() + " digits.");
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
	
}
