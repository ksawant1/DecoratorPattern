package textdecorators;
import textdecorators.AbstractTextDecorator;
import  textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import java.util.ArrayList;
import java.io.IOException;

public class SpellCheckDecorator extends AbstractTextDecorator{
	private AbstractTextDecorator atd;
	private InputDetails id;
	FileProcessor fileP;
	ArrayList<String> misspelledwords = new ArrayList<String>();

	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn, FileProcessor fileprocessor) {
		atd = atdIn;
		id = idIn;
		this.fileP=fileprocessor;
	}
public void processInputDetails(){
	try {
		String line = fileP.poll();
		while (line != null) {
			String string[]=line.toLowerCase().split(" ");

			for(String s : string){
				misspelledwords.add(s);
			}

			line = fileP.poll();

		}
		for(String k: misspelledwords){
			for(String s : id.getWords()){
				if(s.equals(k)) {
					id.updateinarray("SPELLCHECK_",k);
					break;
				}
			}
		}
		System.out.println(id.getWords());

		if (null != atd) {
			atd.processInputDetails();
		}


	}catch (IOException ex){
		System.out.println("bad input");
	}


}

		}