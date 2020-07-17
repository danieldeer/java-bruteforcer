package space.crack;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DictionaryWordGenerator implements WordGenerator {

	private List<String> list;
	private int currentIndex;
	
	public DictionaryWordGenerator(Path dictionaryFilePath) {
		this.currentIndex = 0;
		
		try
		{
			list = Files.readAllLines(dictionaryFilePath, Charset.defaultCharset());
		} catch (IOException e) {

		}
		
	}
	
	@Override
	public String next() throws Exception {
		if(currentIndex < list.size()) {
			return list.get(currentIndex++);
		}
		else
		{
			throw new Exception("No more elements available");
		}
	}

}
