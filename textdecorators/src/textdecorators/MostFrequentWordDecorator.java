package textdecorators;
import textdecorators.AbstractTextDecorator;
import  textdecorators.util.InputDetails;
import java.util.Iterator;
import java.util.ArrayList;


public class MostFrequentWordDecorator extends AbstractTextDecorator{
	private AbstractTextDecorator atd;
	private InputDetails id;
	int count = 0, maxCount = 0,index=0,counter=0;
	String word="";
	ArrayList<Integer> loop= new ArrayList<Integer>();

	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}
public  void processInputDetails(){
	for(int i = 0; i < id.getWords().size(); i++){
		count = 1;

		for(int j = i+1; j < id.getWords().size(); j++){
			if(id.getWords().get(i).equals(id.getWords().get(j))){
				count++;
			}
		}

		if(count > maxCount){
			maxCount = count;
			word = id.getWords().get(i);
		}
	}

		for(String s : id.getWords()){
			if(s.equals(word)) {
				System.out.println(index);
				loop.add(index+counter);
				counter=counter+2;
			}
				index=index+1;
		}

		for(int i:loop){
			System.out.println(i);
			id.updatewithindex("MOST_FREQUENT_", i);

		}
	if (null != atd) atd.processInputDetails();

}
		}