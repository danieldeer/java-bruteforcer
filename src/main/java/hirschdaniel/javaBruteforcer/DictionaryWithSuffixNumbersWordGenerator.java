package hirschdaniel.javaBruteforcer;

import java.nio.file.Path;

public class DictionaryWithSuffixNumbersWordGenerator extends DictionaryWordGenerator {

	private String currentWord;
	private String currentSuffix;
	private final int suffixLength;
	private final String suffixCharacterSet;
	
	
	
	public DictionaryWithSuffixNumbersWordGenerator(Path dictionaryFilePath, int suffixLength) {
		super(dictionaryFilePath);
		this.suffixLength = suffixLength;
		
		this.suffixCharacterSet = CharacterSets.ALL_NUMBERS;
		this.currentSuffix = "";
		
		try {
			this.currentWord = super.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String next() throws Exception
	{
		if(finishedGeneratingForCurrentWord())
		{
			this.currentWord = super.next();
			this.currentSuffix = "";
			return this.currentWord;
		}
		else
		{
			return this.currentWord + generateCurrentSuffix();
		}
	}

	private boolean finishedGeneratingForCurrentWord()
	{
		return StringUtilities.isMaximallyIncremented(this.currentSuffix, this.suffixCharacterSet) && (this.currentSuffix.length() == this.suffixLength);
	}
	
	private String generateCurrentSuffix() {
		this.currentSuffix = StringUtilities.incrementString(this.currentSuffix, this.suffixCharacterSet);
		return this.currentSuffix;
	}


}
