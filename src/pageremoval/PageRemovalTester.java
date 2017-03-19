package pageremoval;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class PageRemovalTester {

	final static int NUM_OF_PAGE_REQUESTS = 25;
	final static int NUM_OF_PAGES = 10;
	//If random request generator creates cluster of 
	final static boolean CLUSTERED = false;

	public static void main(String[] args) {
		//Create Memory Stack
		/*
		 * INPUT 
		 * Get: 
		 * num of frames
		 * num of page requests
		 * distribution
		 * clustering
		 */
		
		ArrayList<Page> pages = PageGen.genPages(NUM_OF_PAGES);
		Memory memory = new Memory(5);
		//int pageRequest[] = createRandomPageSequence(NUM_OF_PAGE_REQUESTS, Pd.TOTAL_RANDOM, CLUSTERED);
		ArrayDeque<Page> pageRequests = PageGen.createRandomPageSequence(NUM_OF_PAGE_REQUESTS, Pd.MULTI_WEIGHTED, pages);
	}

	//For generating different sets of page requests
	public enum Pd {
		//Every number has equal chance of appearing in sequence
		TOTAL_RANDOM,
		//A single job has a greater chance of occurring
		SOLO_WEIGHTED, 
		//2 or 3 jobs are requested more frequently
		MULTI_WEIGHTED
	}
}
