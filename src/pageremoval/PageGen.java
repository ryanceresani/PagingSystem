package pageremoval;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class PageGen {
	//Constants representing the name of config file keys
	//Used for polling the config file for the simulation
	final static String NUM_OF_PAGES = "differentpages";
	final static String PAGE_FRAMES = "pageframes";
	final static String PAGE_REQUESTS = "pagerequests";
	final static String DISTRIBUTION = "distribution";
	final static String MULTIWEIGHT = "multiweight";
	final static String WEIGHT_BIAS = "weightbias";
	final static String CLUSTERED = "clustered";	

	public static ArrayDeque<Page> createRandomPageSequence(Properties props, ArrayList<Page> pages) {

		ArrayDeque<Page> pageRequest = new ArrayDeque<Page>();
		int pageRequests = (int) props.get(PAGE_REQUESTS);
		int numPages = (int) props.get(NUM_OF_PAGES);
		int randomNumber;


		if((int) props.get(DISTRIBUTION) == 1) {
			for (int i = 0; i < (int) props.get(PAGE_REQUESTS); i++) {
				randomNumber = ThreadLocalRandom.current().nextInt(0, numPages);
				pageRequest.offer(pages.get(randomNumber));
			}
			return pageRequest;
		}
		else{
			int multiWeight = (int) props.get(MULTIWEIGHT);
			int weightBias = (int) props.get(WEIGHT_BIAS);
			int std = Math.max(1, multiWeight-weightBias);
			
			for (int i = 0; i < pageRequests; i++) {
				do{
					randomNumber = (int) ThreadLocalRandom.current().nextGaussian() * std; 
				} while(randomNumber > 0 && randomNumber <= pageRequests);
				pageRequest.offer(pages.get(randomNumber));
			}
		}
		
		return pageRequest;
	}

	public static ArrayList<Page> genPages(Properties prop) {
		ArrayList<Page> pages = new ArrayList<Page>();
		int numOfPages = (int) prop.get(NUM_OF_PAGES);
		for (int i = 0; i < numOfPages; i++) {
			pages.add(new Page());
		}
		return pages;
	}
}
