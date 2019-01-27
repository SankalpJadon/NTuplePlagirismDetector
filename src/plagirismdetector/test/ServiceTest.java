package plagirismdetector.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import plagirismdetector.models.Tuple;
import plagirismdetector.services.FileService;
import plagirismdetector.services.PlagirismService;

class ServiceTest {

	@Test
	// match the file contents
	void getFileContentTest() throws IOException {
		String file1Output = FileService.getFileContent("src/files/file1.txt");
		assertEquals("went for a jog", file1Output);
		String file2Output = FileService.getFileContent("src/files/file2.txt");
		assertEquals("go for a run", file2Output);
		String synonymsFile = FileService.getFileContent("src/files/synonyms.txt");
		assertEquals("run sprint jog", synonymsFile);
	}

	@Test
	// if the size of tuple is greater than number of words, return null
	void convertFileContentToTuplesTest() throws IOException {
		List<Tuple> tuples = FileService.convertFileContentToTuples("hello how are you", 6);
		assertNull(tuples);
	}
	
	@Test
	// if the line has special characters, remove the special characters and return only words
	void extractWordsTest() {
		String[] content = FileService.extractWords("  , !* this is , plagirisim detector");
		String[] expectedOutput = new String[]{"this","is","plagirisim","detector"};
		for(int i = 0;i<expectedOutput.length;i++) {
			assertEquals(content[i],expectedOutput[i]);
		}
	}
	
	@Test
	// if the input model == null, return 0% similarity;
	void checkPlagirismTest() {
		PlagirismService service = new PlagirismService();
		assertEquals(0,service.checkPlagirism(null));
	}
	
	@Test
	void calculateSimilarityPercentTest() {
		PlagirismService service = new PlagirismService();
		HashSet<String> set = new HashSet();
		set.addAll(new ArrayList<String>(Arrays.asList("go","for","a","run")));
		HashMap<String,HashSet<String>> synonymsMap = new HashMap();
		List<Tuple> tuples1=null, tuples2 = null;
		assertEquals(0,service.calculateSimilarityPercent(synonymsMap, tuples1, tuples2));
	}
	
	
}
