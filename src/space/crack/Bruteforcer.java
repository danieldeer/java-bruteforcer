package space.crack;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import represetation.Base64Representation;
import represetation.ByteRepresentation;

/**
 * A class that provides bruteforcing capabilities.
 * @author User
 *
 */
public class Bruteforcer {

	private WordGenerator wordGenerator;
	private EncryptionRoutine encryptionRoutine;
	private String targetHash;
	private int startOffset = 0;
	private int stepSize = 1;
	
	//By default, represent byte arrays as Base64
	private final ByteRepresentation representation = new Base64Representation();
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash) {
		this.wordGenerator = new BruteforceWordGenerator(CharacterSets.ALL_LETTERS_ALL_NUMBERS);
		this.encryptionRoutine = new StringToSha256Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced using the specified characterSet.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash, final String characterSet) {
		this.wordGenerator = new BruteforceWordGenerator(characterSet);
		this.encryptionRoutine = new StringToSha256Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced using the specified wordlist.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash, final Path dictionaryFilePath) {
		this.wordGenerator = new DictionaryWordGenerator(dictionaryFilePath);
		this.encryptionRoutine = new StringToSha256Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Sets up the bruteforcer with the given hash to be bruteforced and the given {@WordGenerator}.
	 * @param hash
	 */
	public Bruteforcer(final String targetHash, WordGenerator wordGenerator) {
		this.wordGenerator = wordGenerator;
		this.encryptionRoutine = new StringToSha256Routine();
		this.targetHash = targetHash;
	}
	
	/**
	 * Bruteforces the given hash and returns the solution.
	 * @param hash
	 * @return result
	 */
	public String bruteforce()
	{
		generateWordsUntilOffsetIsReached();
		
		Logger.getGlobal().log(Level.INFO, "Attempting to find key for hash " + this.targetHash);
		boolean solutionFound = false;
		StringBuilder guess = new StringBuilder();
		StringBuilder hash = new StringBuilder();
		StringBuilder targetHash = new StringBuilder(this.targetHash);
		while(!solutionFound)
		{
			try {
				guess.setLength(0);
				skipWords(this.stepSize-1);
				guess.append(wordGenerator.next());
			} catch (Exception e) {
				break;
			}
			hash.setLength(0);
			hash.append(
						this.representation.getAsString(encryptionRoutine.encrypt(guess.toString()))
					);
			if(hash.toString().equals(targetHash.toString()))
			{
				Logger.getGlobal().log(Level.INFO, "SUCCESS! Hash " + this.targetHash + " has key: " + guess);
				return guess.toString();
			}
		}
		Logger.getGlobal().log(Level.INFO, "Could not find key");
		return null;
	}

	private void skipWords(int numberOfWordsToSkip) {
		IntStream.range(0,numberOfWordsToSkip).forEach($ -> {
			try {
				wordGenerator.next();
			} catch (Exception e){
			}
		});
	}

	private void generateWordsUntilOffsetIsReached() {
		IntStream.range(0,this.startOffset).forEach($ -> {
			try {
				wordGenerator.next();
			} catch (Exception e){
			}
		});
	}

	/**
	 * Set step size of the bruteforcer.
	 * A step size of 1 will test every generated word.
	 * A step size of 2 will test every second generated word.
	 * etc.
	 * Step size is used for multithreading, so multiple threads test different words.
	 */
	public void setStep(int stepSize) {
		this.stepSize = stepSize;
	}
	
	/**
	 * Start the bruteforcer at the startOffset'th word.
	 * If startOffset is 3, bruteforcer will start at the third word.
	 * @param startOffset
	 */
	public void setOffset(int startOffset)
	{
		this.startOffset = startOffset;
	}
	
}
