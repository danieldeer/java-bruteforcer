package space.crack;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Welcome to SPACE CRACK");
		
		long startTime = System.currentTimeMillis();
		
		
		

		final String dictionaryFilePathString = "10millionenpw.txt";
		final Path dictionaryFilePath = new File(dictionaryFilePathString).toPath();
		
		WordGenerator wordGen = new BruteforceWordGenerator(CharacterSets.ALL_LETTERS_ALL_NUMBERS+"$!&#");
		
		
		EncryptionRoutine encryptionRoutine = new StringToSha256ToBase64Routine();
		final String targetHash = encryptionRoutine.encrypt("AA$M");
		
		new Bruteforcer(targetHash, wordGen).bruteforce();
		
		
		
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		
		System.out.println("The execution took about " + String.format("%d sec, %d ms", 
			    TimeUnit.MILLISECONDS.toSeconds(duration), 
			    duration - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration)))
			);
	}

}
