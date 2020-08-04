package textdecorators.driver;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

import textdecorators.util.InputDetails;
import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.util.FileProcessor;
import textdecorators.exceptions.InputFileNotExistsException;
import textdecorators.exceptions.InputFileMatchException;

/**
 * Driver is a utility that contains main method
 * @author Krupa Sawant
 */


public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 4;

	/**
	 * Handles few exceptions and mainly creates instances for decorators and inputdetails class
	 * @exception IOException On invalid input.
	 * @exception InputFileNotExistsException On not finding input file where mentioned.
	 * @exception InputFileMatchException when both input files have same name and path
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 4) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}")) || (args[2].equals("${keywords}")) || (args[3].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		try {
			//storing input file
			String inputFilename = args[0];
			File inputFile = new File(args[0]);

			//storing outputfile
			String outputFilename = args[3];

			//defining file processors for input,misspelled and keywords
			FileProcessor fpinput = new FileProcessor(args[0]);
			FileProcessor fpmisspelled = new FileProcessor(args[1]);
			File misspelledFile = new File(args[1]);

			FileProcessor fpkeyword = new FileProcessor(args[2]);
			File keywordFile = new File(args[2]);

			// if input file does not exist in the given path
			if (!inputFile.exists() ) {
				throw new InputFileNotExistsException("");
			}

			// if input1 file name and path are matching to input file2
			if ((inputFile.equals(misspelledFile)) || (inputFile.equals(keywordFile)) || (keywordFile.equals(misspelledFile))) {
				throw new InputFileMatchException("");
			}


			// creating an instance of InputDetails, passing the files
			InputDetails inputD = new InputDetails(inputFilename, outputFilename);
			// setting file processor in inputdetails class
			inputD.setFileProcessor(fpinput);
			//calling the method to store input data into structure
			inputD.storeSentence();

			// creating instances of decorators, pass instances to sequential decorators along
			AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
			// extra parameter to pass misspelled and keyword file
			AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD, fpmisspelled);
			AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD, fpkeyword);
			AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

			//calling first decorator which initiates process
			mostFreqWordDecorator.processInputDetails();

			//writing to file
			inputD.writeToFile();

		} catch (FileNotFoundException ex) {
			System.out.println("file not found");
		} catch (IOException ex) {
			System.out.println("input wrong");
		} catch (InputFileNotExistsException e) {
			System.out.println("input file doesn't exist");
			System.exit(0);
		} catch (InputFileMatchException e) {
			System.out.println("input files have same name and path");
			System.exit(0);
		}
	}

	@Override
	public String toString() {
		return "Driver{}";
	}
}
