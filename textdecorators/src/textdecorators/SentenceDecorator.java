package textdecorators;

import textdecorators.AbstractTextDecorator;
import textdecorators.util.InputDetails;

/**
 * a decorator class that accesses the sentence list and adds a decorator indicating start and end of a sentence
 *
 * @author Krupa Sawant
 */
public class SentenceDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;

	//constructor to set parameters like decorator and inputdetails instance
	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	// method that appends decorator to start and end of sentence
	public void processInputDetails() {
		id.getResultant_sentence().add(0, "BEGIN_SENTENCE__");
		id.getResultant_sentence().add(id.getResultant_sentence().size(), " __END_SENTENCE.");

	}

	@java.lang.Override
	public java.lang.String toString() {
		return "SentenceDecorator{" +
				"atd=" + atd +
				", id=" + id +
				'}';
	}
}