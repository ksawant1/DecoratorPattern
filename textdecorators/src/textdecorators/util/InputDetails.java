package textdecorators.util;
import java.io.*;
import java.util.ArrayList;
import textdecorators.exceptions.InputFileEmptyException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * class which stores,updates and retrieves data structure that stores sentence from input file
 *
 * @author Krupa Sawant
 */
public class InputDetails {

	FileProcessor fileP;
	int count = 0;
	//stores our sentence from input file which goes through decorator class
	ArrayList<String> resultant_sentence = new ArrayList<String>();
	String inputf, outputf;
	private static Pattern pattern;
	private static Matcher matcher;

	//constructor to set input and output file
	public InputDetails(String inputFilename, String outputFilename) {
		this.inputf = inputFilename;
		this.outputf = outputFilename;
	}

	// method to set file processor in current class
	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileP = fileProcessor;
	}

	//getters and setters for data structure resultant_sentence
	public ArrayList<String> getResultant_sentence() {
		return resultant_sentence;
	}

	public void setResultant_sentence(ArrayList<String> resultant_sentence) {
		this.resultant_sentence = resultant_sentence;
	}

	// method that calls poll using fileprocessor and stores it in an array list call resultant_sentence
	public void storeSentence() throws IOException {

		try {

			String line = fileP.poll();
			if (line == null)
				throw new InputFileEmptyException("");

			while (line != null) {

				// removes period from sentence and splits it
				String string[] = line.replace(".", " ").split(" ");
				//iterates and adds in the list
				for (String s : string) {
					resultant_sentence.add(s);
				}
				line = fileP.poll();

			}

		} catch (IOException ex) {
			System.out.println("input invalid");
		} catch (InputFileEmptyException e) {
		System.out.println("input file is empty");
		System.exit(0);

	}}

	// mainly calls search() and returns element index if found in array list
	public int updateinarray(String addon, String key) {
		int index = search(key);
		return index;
	}

	// method to search in the array list
	public int search(String key) {
		count = 0;
		//the search algorithm is case-insensitive
		for (String s : resultant_sentence) {
			if (s.equalsIgnoreCase(key)) {
				return count;
			}
			count = count + 1;
		}
		return 0;
	}

	//method that converts array list resultant_sentence to string and writes it to output file
	public void writeToFile() throws IOException {
		//converts to string array
		String output[] = resultant_sentence.toArray(new String[resultant_sentence.size()]);

		//prints output to console
		for (String k : output) {
			System.out.print(k + " ");
		}

		//writes to output file
		try {
			FileWriter fileWriter = new FileWriter(outputf);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			for (String x : resultant_sentence) {
				printWriter.print(x + " ");
			}

			printWriter.close();
		} catch (IOException ex) {
			System.out.println("file not found");
		}
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "InputDetails{" +
				"fileP=" + fileP +
				", count=" + count +
				", resultant_sentence=" + resultant_sentence +
				", inputf='" + inputf + '\'' +
				", outputf='" + outputf + '\'' +
				'}';
	}
}