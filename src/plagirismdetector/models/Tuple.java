package plagirismdetector.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Tuple {
	    private List<String> words;
	    private int size;
	    
	    public Tuple(int size) {
	    	this.size = size;
	    	this.words = new ArrayList<String>();
	    }
	    
		public List<String> getWords() {
			return words;
		}
		public void setWords(List<String> words) {
			this.words = words;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		
		// Match this tuple with the other tuple
		public boolean isMatch(Tuple tuple, HashMap<String, HashSet<String>> wordSynonyms) {
			List<String> tuple1 = tuple.getWords();
			List<String> tuple2 = this.getWords();
			
			if(tuple1.size()!=tuple2.size())
				return false;
			
			for(int index=0;index<tuple1.size();index++) {
				String tuple1Word = tuple1.get(index);
				String tuple2Word = tuple2.get(index);
				
				if(!tuple1Word.equals(tuple2Word)) {
					if(!wordSynonyms.containsKey(tuple1Word))
						return false;
					HashSet<String> synonyms = wordSynonyms.get(tuple1Word);
					if(!synonyms.contains(tuple2Word))
						return false;
				}
			}
			
			return true;
		}
		public void addWord(String word) {
			words.add(word);
		}
}
