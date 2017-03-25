package pageremoval;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Properties;

import config.ConfigParser;

public class PageRemovalTester {
	
	public static void main(String[] args) throws IOException {
		//Create Memory Stack
		/*
		 * INPUT 
		 * Get: 
		 * num of frames
		 * num of page requests
		 * distribution
		 * clustering
		 */
		ConfigParser cf = new ConfigParser();
		Properties prop = cf.getPropValues();
	
		ArrayList<Page> pages = PageGen.genPages(prop);
		Memory memory = new Memory(5);
		//int pageRequest[] = createRandomPageSequence(NUM_OF_PAGE_REQUESTS, Pd.TOTAL_RANDOM, CLUSTERED);
		ArrayDeque<Page> pageRequests = PageGen.createRandomPageSequence(prop, pages);
		
		System.out.println(pageRequests.toString());
	}
}
