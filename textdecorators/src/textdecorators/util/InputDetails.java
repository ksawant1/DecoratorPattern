package textdecorators.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class InputDetails{
	FileProcessor fileP;
   int count=0;
	ArrayList<String> words = new ArrayList<String>();

	String inputf, outputf;
	  public InputDetails(String inputFilename, String outputFilename){
	  	this.inputf=inputFilename;
	  	this.outputf=outputFilename;


	  }
	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileP = fileProcessor;
		}

	  public void inputData() throws IOException{
	  	try {
		    String line = fileP.poll();
		    while (line != null) {
			    String string[] = line.toLowerCase().split(" ");
			    for(String s : string){
				    words.add(s);
			    }
			    line = fileP.poll();

		    }

	    }catch (IOException ex){
		    System.out.println("bad input");
	    }}

	  	public void updateinarray(String addon, String key){
		 int index= search(key);
		   // System.out.println(index);
		 words.add(index,addon);
		 words.add(index+2,addon);

}
       public int search(String key){
	  	count=0;
	       for (String s : words) {
		       if (s.equals(key)) {
			       return count;
		       }
		       count=count+1;
	       }
	       return 0;

       }
       public void updatewithindex(String addon, int index){
	       words.add(index,addon);
	       words.add(index+2,addon);


       }

      public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}


}