package textdecorators.driver;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import  textdecorators.util.InputDetails;
import  textdecorators.AbstractTextDecorator;
import  textdecorators.KeywordDecorator;
import  textdecorators.MostFrequentWordDecorator;
import  textdecorators.SentenceDecorator;
import  textdecorators.SpellCheckDecorator;
import textdecorators.util.FileProcessor;

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 4;

	/**
	 * Handles few exceptions and mainly creates instances for visitors, ADTs and results
	 * @exception IOException On invalid input.
	 */
	public static void main(String[] args) throws FileNotFoundException,IOException {

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
			//storing input files
			String inputFilename = args[0];
			String outputFilename = args[3];
			FileProcessor fpinput = new FileProcessor(args[0]);
			FileProcessor fpmisspelled= new FileProcessor(args[1]);
			FileProcessor fpkeyword= new FileProcessor(args[2]);
			InputDetails inputD = new InputDetails(inputFilename, outputFilename);
			inputD.setFileProcessor(fpinput);
			inputD.inputData();

			AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);

			AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD,fpmisspelled);
			AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD,fpkeyword);

			AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

			mostFreqWordDecorator.processInputDetails();

		}catch(FileNotFoundException ex){
			System.out.println("file not found");
		}catch( IOException ex){
			System.out.println("input wrong");

		}

	}

	@Override
	public String toString() {
		return "Driver{}";
	}
}
