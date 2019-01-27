package plagirismdetector.services;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import plagirismdetector.models.InputModel;
import plagirismdetector.models.Tuple;

public class PlagirismService {
	
	public double checkPlagirism(InputModel inputModel) {
		double similarityPercent = 0.0;
		try {
    		if(inputModel==null)
    			return 0;
    		
            HashMap<String, HashSet<String>> synonymsMap = FileService.generateSynonymsMap(inputModel.getSynonymFilePath());
            
            String file1Content = FileService.getFileContent(inputModel.getFile1Path());
            String file2Content = FileService.getFileContent(inputModel.getFile2Path());
            
            if(file1Content==null || file1Content==null) {
            	System.out.println("Invalid file");
            	return 0;
            }
            
            List<Tuple> tuples1 = FileService.convertFileContentToTuples(file1Content, inputModel.getTupleSize());
            List<Tuple> tuples2 = FileService.convertFileContentToTuples(file2Content, inputModel.getTupleSize());
            
            if(tuples1==null || tuples2==null) {
            	System.out.println("Tuple size cannot be greater than the number of words");
            	return 0;
            }
   
            similarityPercent  = calculateSimilarityPercent(synonymsMap, tuples1, tuples2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return similarityPercent;
	}
	
	// Calculate the similarity of tuples and return the percentage
	public double calculateSimilarityPercent(HashMap<String, HashSet<String>> synonymsMap, List<Tuple> tuples1, List<Tuple> tuples2) {
		double count = 0;

        if (tuples1==null || tuples2==null || tuples1.size() == 0)
            return count;

        for (Tuple tuple1 : tuples1) {
            for (Tuple tuple2 : tuples2) {
                if (tuple1.isMatch(tuple2, synonymsMap))
                    count++;
            }
        }

        return count / tuples1.size() *100;
	}
	
}
