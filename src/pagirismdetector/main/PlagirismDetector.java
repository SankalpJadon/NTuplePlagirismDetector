package pagirismdetector.main;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plagirismdetector.models.InputModel;
import plagirismdetector.services.PlagirismService;
import plagirismdetector.utils.Util;

public class PlagirismDetector {
	
	private final static int DEFAULT_TUPLE_SIZE = 3;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlagirismService service = new PlagirismService();
		
		List<String> filePaths = new ArrayList<String>();
		scanner = new Scanner(System.in);
		System.out.println("Enter synonym file path: ");
		filePaths.add(scanner.nextLine());
		System.out.println("Enter file1 path: ");
		filePaths.add(scanner.nextLine());
		System.out.println("Enter file2 path: ");
		filePaths.add(scanner.nextLine());
		System.out.println("Enter the tuple size: ");
		filePaths.add(scanner.nextLine());
		
		InputModel inputModel = validateInput(filePaths);
		
		if(inputModel!=null) {
			double similarityPercent = service.checkPlagirism(inputModel);
			System.out.println("Similarity percent is "+similarityPercent+"%");
		}
		else {
			System.out.println("Input validation failed");
		}
	}
	
	// Checks weather the tuple size input is number or not
	public static InputModel validateInput(List<String> args) {

		InputModel inputModel = new InputModel();

		inputModel.setSynonymFilePath(args.get(0));
		inputModel.setFile1Path(args.get(1));
		inputModel.setFile2Path(args.get(2));

        if (Util.isNumber(args.get(3)))
            inputModel.setTupleSize(Integer.parseInt(args.get(3)));
        else {
            inputModel.setTupleSize(DEFAULT_TUPLE_SIZE);
   			System.out.println("Invalid tuple size, default value chosen is 3 ");    
        }
        
        return inputModel;
		
	}

}
