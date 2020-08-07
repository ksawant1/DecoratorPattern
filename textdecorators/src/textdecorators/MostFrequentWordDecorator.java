package textdecorators;
import textdecorators.AbstractTextDecorator;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.String;

/**
 * class that accesses our list,finds the most frequent word occuring in the sentence and adds a decorator string before
 * and after the word
 *
 * @author Krupa Sawant
 */
public class MostFrequentWordDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;
	int count = 0, maxCount = 0, index = 0, counter = 0;
	String word = "";
	ArrayList<Integer> toloop = new ArrayList<Integer>();

	//constructor to set parameters like decorator and inputdetails instance
	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
		MyLogger.getInstance().writeMessage("constructor for most frequent decorator", MyLogger.DebugLevel.CONSTRUCTOR);

	}

	// method to find most frequent word
	public void processInputDetails() {
		MyLogger.getInstance().writeMessage("inside MOST FREQUENT decorator class", MyLogger.DebugLevel.MOSTFREQUENTWORDSDECORATOR);
		MyLogger.getInstance().writeMessage("gets the array list checks every word count", MyLogger.DebugLevel.MOSTFREQUENTWORDSDECORATOR);
		MyLogger.getInstance().writeMessage("compares with maximum word count", MyLogger.DebugLevel.MOSTFREQUENTWORDSDECORATOR);

		for (int i = 0; i < id.getResultant_sentence().size(); i++) {
			count = 1;
			//loops through the sentence list while comparing each words occurance by keeping a count
			for (int j = i + 1; j < id.getResultant_sentence().size(); j++) {
				if (id.getResultant_sentence().get(i).equalsIgnoreCase(id.getResultant_sentence().get(j))) {
					count++;

				}
			}
			// if we find a maximum count we know it's the most frequently occurring word
			if (count > maxCount) {
				maxCount = count;
				word = id.getResultant_sentence().get(i);
			}
		}
		// we store all the indices where out most frequently word is situated and store in a temporary list
		for (String s : id.getResultant_sentence()) {
			if (s.equalsIgnoreCase(word)) {
				toloop.add(index + counter);
				counter = counter + 2;
			}
			index = index + 1;
		}
		MyLogger.getInstance().writeMessage("adds decorators to most ocurring", MyLogger.DebugLevel.MOSTFREQUENTWORDSDECORATOR);
		// loooping through the temporary list we add sentence decorators
		for (int i : toloop) {
			id.getResultant_sentence().add(i, "MOST_FREQUENT_");
			id.getResultant_sentence().add(i + 2, "_MOST_FREQUENT");


		}
		MyLogger.getInstance().writeMessage("goes to next decorator", MyLogger.DebugLevel.MOSTFREQUENTWORDSDECORATOR);
		// we move to the next in sequence decorator
		if (null != atd) atd.processInputDetails();

	}

	@java.lang.Override
	public java.lang.String toString() {
		return "MostFrequentWordDecorator{" +
				"atd=" + atd +
				", id=" + id +
				", count=" + count +
				", maxCount=" + maxCount +
				", index=" + index +
				", counter=" + counter +
				", word='" + word + '\'' +
				", toloop=" + toloop +
				'}';
	}
}