package space.crack;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Welcome to SPACE CRACK");
		
		long startTime = System.currentTimeMillis();
		
		
		
		final String targetHash = "D7vum7HGNby46z7kORKU5n1Go/JUhuGgon/WQVtTfLk=";
		final String dictionaryFilePathString = "10millionenpw.txt";
		final Path dictionaryFilePath = new File(dictionaryFilePathString).toPath();
		
		DictionaryWithSuffixNumbersWordGenerator wordGen = new DictionaryWithSuffixNumbersWordGenerator(dictionaryFilePath, 3);
		Bruteforcer bruteforcer = new Bruteforcer(targetHash, wordGen);
		
		
		System.out.println("Attempting the bruteforce of " + targetHash);
		System.out.println("SOLUTION FOUND: " + bruteforcer.bruteforce());
		
		
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		
		System.out.println("The execution took about " + String.format("%d sec, %d ms", 
			    TimeUnit.MILLISECONDS.toSeconds(duration), 
			    duration - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration)))
			);
	}

}
