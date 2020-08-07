package textdecorators;

import textdecorators.AbstractTextDecorator;
import textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import textdecorators.exceptions.InputFileEmptyException;
import textdecorators.util.MyLogger;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.String;

/**
 * class that accesses our list,matches word from given spellcheck file occuring in the sentence and adds a decorator
 * string before and after the word
 *
 * @author Krupa Sawant
 */

public class SpellCheckDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;
	FileProcessor fileP;
	ArrayList<String> misspelledwords = new ArrayList<String>();

	//constructor to set parameters like decorator and inputdetails instance and fileprocessor
	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn, FileProcessor fileprocessor) {
		atd = atdIn;
		id = idIn;
		this.fileP = fileprocessor;
		MyLogger.getInstance().writeMessage("constructor for spell check decorator", MyLogger.DebugLevel.CONSTRUCTOR);

	}

	//to find word and add decorator
	public void processInputDetails() {
		MyLogger.getInstance().writeMessage("inside spellcheck decorator class", MyLogger.DebugLevel.SPELLCHECKDECORATOR);
		//reads the provided spell check file.txt and stores in temporary array
		MyLogger.getInstance().writeMessage("reads the provided spell check file.txt and stores in temporary array", MyLogger.DebugLevel.SPELLCHECKDECORATOR);
		try {
			String line = fileP.poll();
			if (line == null)
				throw new InputFileEmptyException("");
			while (line != null) {
				String string[] = line.split(" ");
				for (String s : string) {
					misspelledwords.add(s);
				}
				line = fileP.poll();
			}
			MyLogger.getInstance().writeMessage("finds all words that match and add decorators to arraylist", MyLogger.DebugLevel.SPELLCHECKDECORATOR);

			// loops through the sentence array and finds all words that match adding the decorator
			// comparison is case-insensitive
			for (String k : misspelledwords) {
				for (String s : id.getResultant_sentence()) {
					if (s.equalsIgnoreCase(k)) {
						int index = id.updateinarray("SPELLCHECK_", k);
						id.getResultant_sentence().add(index, "SPELLCHECK_");
						id.getResultant_sentence().add(index + 2, "_SPELLCHECK");
						break;
					}
				}
			}
			MyLogger.getInstance().writeMessage("calls next decorator", MyLogger.DebugLevel.SPELLCHECKDECORATOR);
			//
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
		return "SpellCheckDecorator{" +
				"atd=" + atd +
				", id=" + id +
				", fileP=" + fileP +
				", misspelledwords=" + misspelledwords +
				'}';
	}
}