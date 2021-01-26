package space.crack;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {
	
	private static long performanceMeasurementStartTime;
	
	public static void main(String[] args) {
		System.out.println("Welcome to SPACE CRACK");

		final String dictionaryFilePathString = "10millionenpw.txt";
		final Path dictionaryFilePath = new File(dictionaryFilePathString).toPath();
		
		
		
		//this is the String "hey" encrypted by SHA-256 represented as a hexadecimal
		final String targetHash = "fa690b82061edfd2852629aeba8a8977b57e40fcb77d1a7a28b26cba62591204"; 
		final String characterSet = CharacterSets.ALL_LETTERS_ALL_NUMBERS+"$!&#";
		final int numberOfThreads = 3;
		
		
		ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
		
		startDurationMeasurement();
		// create numberOfThreads new Threads to perform the bruteforce
		IntStream.range(0,numberOfThreads).forEach(i-> {
			
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					Bruteforcer bruteforcer = new Bruteforcer(targetHash, new BruteforceWordGenerator(characterSet));
					bruteforcer.setOffset(i);
					bruteforcer.setStep(numberOfThreads);
					bruteforcer.bruteforce();
					endDurationMeasurement();
					System.exit(0);
				}
			});
		});

		
		
	}
	
	
	private static void startDurationMeasurement()
	{
		performanceMeasurementStartTime = System.currentTimeMillis();
	}
	private static void endDurationMeasurement()
	{
		long endTime = System.currentTimeMillis();
		long duration = (endTime - performanceMeasurementStartTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The execution took about " + String.format("%d sec, %d ms", 
			    TimeUnit.MILLISECONDS.toSeconds(duration), 
			    duration - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration)))
			);
	}

}
