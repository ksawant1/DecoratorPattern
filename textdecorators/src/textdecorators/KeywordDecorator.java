package textdecorators;

import textdecorators.AbstractTextDecorator;
import textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import textdecorators.util.MyLogger;
import textdecorators.exceptions.InputFileEmptyException;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.String;

/**
 * a decorator class that accesses our list,matches word from given keywords file occuring in the sentence
 * and adds a decorator string before and after the word
 *
 * @author Krupa Sawant
 */

public class KeywordDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;
	FileProcessor fileP;
	ArrayList<String> keywords = new ArrayList<String>();

	//constructor to set parameters like decorator and inputdetails instance and fileprocessor
	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn, FileProcessor fileProcessor) {
		atd = atdIn;
		id = idIn;
		this.fileP = fileProcessor;
		MyLogger.getInstance().writeMessage("constructor for keyword decorator", MyLogger.DebugLevel.CONSTRUCTOR);

	}

	// methods that performs the comparison
	public void processInputDetails() {
		MyLogger.getInstance().writeMessage("inside key word decorator class", MyLogger.DebugLevel.KEYWORDDECORATOR);
		//reads the provided keywords.txt and stores in temporary array
		try {
			MyLogger.getInstance().writeMessage("checks for exceptions", MyLogger.DebugLevel.KEYWORDDECORATOR);
			String line = fileP.poll();
			if (line == null)
				throw new InputFileEmptyException("");
			while (line != null) {
				String string[] = line.split(" ");

				for (String s : string) {
					keywords.add(s);
				}

				line = fileP.poll();
			}
			MyLogger.getInstance().writeMessage("loops through the sentence array and finds all words that match adding the decorator", MyLogger.DebugLevel.KEYWORDDECORATOR);
			// loops through the sentence array and finds all words that match adding the decorator
			// comparison is case-insensitive
			for (String k : keywords)
				for (String s : id.getResultant_sentence()) {


					if (s.equalsIgnoreCase(k)) {
						int index = id.updateinarray("KEYWORD", k);
						id.getResultant_sentence().add(index, "KEYWORD_");
						id.getResultant_sentence().add(index + 2, "_KEYWORD");
						break;
					}
				}
			MyLogger.getInstance().writeMessage("calls the next decorator in sequence", MyLogger.DebugLevel.KEYWORDDECORATOR);

			// calls the next decorator in sequence
			if (null != atd) {
				atd.processInputDetails();
			}

		} catch (IOException ex) {
			System.out.println("bad input");
		}catch (InputFileEmptyException e) {
			System.out.println("input file is empty");
			System.exit(0);

		}

	}

	@java.lang.Override
	public java.lang.String toString() {
		return "KeywordDecorator{" +
				"atd=" + atd +
				", id=" + id +
				", fileP=" + fileP +
				", keywords=" + keywords +
				'}';
	}
}