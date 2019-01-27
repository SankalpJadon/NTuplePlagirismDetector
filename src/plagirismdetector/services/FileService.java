package plagirismdetector.services;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import plagirismdetector.models.Tuple;

public class FileService {
	
	// read the synonyms file, and create a hashmap of each word and it's list of related words
	public static HashMap<String,HashSet<String>> generateSynonymsMap(String synonymsFileName) throws IOException{
		HashMap<String, HashSet<String>> synonymsMap = new HashMap<String, HashSet<String>>();
		
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(synonymsFileName);
			br = new BufferedReader(fr);

			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				String[] words = currentLine.toLowerCase().split("\\s+");
				
				HashSet<String> wordSet = new HashSet<String>(Arrays.asList(words));
				
				for (String word : words) {
                    if (!synonymsMap.containsKey(word))
                        synonymsMap.put(word, wordSet);
                }
			}
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return synonymsMap;
		
	}
	
	// Read the file, if file doesn't exist, print out the statement, return the file content as string
	public static String getFileContent(String filePath) throws IOException {
		StringBuilder content = new StringBuilder();
		
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				content.append(currentLine);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
			return null;
		} finally {

			try {

				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		return content.toString();
	}
	
	// convert file content into list of tuples.
	// Assumption:- if the tuple size > count of words in file, return null
	public static List<Tuple> convertFileContentToTuples (String fileContent, int tupleSize) throws IOException {
				
        List<Tuple> tuples = new ArrayList<Tuple>();

        String[] words = extractWords(fileContent);

        if(words.length<tupleSize)
        	return null;

        for (int i = 0; i <= words.length - tupleSize; i++) {
            Tuple tuple = new Tuple(tupleSize);
            for (int j = i ; j < i + tupleSize; j++) {
                tuple.addWord(words[j]);
            }
            tuples.add(tuple);
        }

        return tuples;
    }
	
	// Read only the words in the file, ignore special characters
	public static String[] extractWords(String line) {
        ArrayList<String> words = new ArrayList<String>();

        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(line);

        while (m.find()) {
            String word = line.substring(m.start(), m.end());

            words.add(word);
        }

        String resultWords[] = new String[words.size()];

        words.toArray(resultWords);

        return resultWords;
    }
}
