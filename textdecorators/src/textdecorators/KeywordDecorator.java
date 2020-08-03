package textdecorators;
import textdecorators.AbstractTextDecorator;
import  textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import java.util.ArrayList;
import java.io.IOException;

public class KeywordDecorator extends AbstractTextDecorator{
	private AbstractTextDecorator atd;
	private InputDetails id;
	FileProcessor fileP;
	ArrayList<String> keywords = new ArrayList<String>();

	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn, FileProcessor fileProcessor) {
		atd = atdIn;
		id = idIn;
		this.fileP=fileProcessor;
	}

public void processInputDetails(){
	try {
		String line = fileP.poll();
		while (line != null) {
			String string[]=line.toLowerCase().split(" ");;

			for(String s : string){
				keywords.add(s);
			}

			line = fileP.poll();
		}
		for(String k: keywords)
			for (String s : id.getWords()) {
				if (s.equals(k)) {
					id.updateinarray("KEYWORD", k);
					break;
				}
			}

		if (null != atd) {
			atd.processInputDetails();
		}

	}catch (IOException ex){
		System.out.println("bad input");
	}

		}

		}