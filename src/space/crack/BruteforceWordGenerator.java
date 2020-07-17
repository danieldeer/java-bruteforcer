package space.crack;

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
		this.currentWord = StringUtilities.incrementString(this.currentWord, this.characterSet);
		return currentWord;
	}
	
	
	
	
}
