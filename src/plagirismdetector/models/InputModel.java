package plagirismdetector.models;
public class InputModel {
	
	private String file1Path;
	private String file2Path;
    private String synonymFilePath;
    private int tupleSize;
    
	public String getFile1Path() {
		return file1Path;
	}
	public void setFile1Path(String file1Path) {
		this.file1Path = file1Path;
	}
	public String getFile2Path() {
		return file2Path;
	}
	public void setFile2Path(String file2Path) {
		this.file2Path = file2Path;
	}
	public String getSynonymFilePath() {
		return synonymFilePath;
	}
	public void setSynonymFilePath(String synonymFilePath) {
		this.synonymFilePath = synonymFilePath;
	}
	public int getTupleSize() {
		return tupleSize;
	}
	public void setTupleSize(int n) {
		this.tupleSize = n;
	}
    
    
}
