package space.crack;

import java.nio.file.Path;

/**
 * A class that provides bruteforcing capabilities.
 * @author User
 *
 */
public class Bruteforcer {

	private WordGenerator wordGenerator;
	private EncryptionRoutine encryptionRoutine;
	private String targetHash;
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash) {
		this.wordGenerator = new BruteforceWordGenerator(CharacterSets.ALL_LETTERS_ALL_NUMBERS);
		this.encryptionRoutine = new StringToSha256ToBase64Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced using the specified characterSet.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash, final String characterSet) {
		this.wordGenerator = new BruteforceWordGenerator(characterSet);
		this.encryptionRoutine = new StringToSha256ToBase64Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced using the specified wordlist.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash, final Path dictionaryFilePath) {
		this.wordGenerator = new DictionaryWordGenerator(dictionaryFilePath);
		this.encryptionRoutine = new StringToSha256ToBase64Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced and the given {@WordGenerator}.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash, WordGenerator wordGenerator) {
		this.wordGenerator = wordGenerator;
		this.encryptionRoutine = new StringToSha256ToBase64Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Bruteforces the given hash and returns the solution.
	 * @param hash
	 * @return result
	 */
	public String bruteforce()
	{
		boolean solutionFound = false;
		while(!solutionFound)
		{
			String guess;
			try {
				guess = wordGenerator.next();
			} catch (Exception e) {
				break;
			}
			final String hash = encryptionRoutine.encrypt(guess);
			if(hash.equals(this.targetHash))
			{
				return guess;
			}
		}
		return null;
	}
	
	
}
