package pageremoval;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import config.ConfigParser;

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

	public static ArrayDeque<Page> createRandomPageSequence(ConfigParser props, ArrayList<Page> pages) {

		ArrayDeque<Page> pageRequest = new ArrayDeque<Page>();
		int pageRequests = props.get(PAGE_REQUESTS);
		int numPages =  props.get(NUM_OF_PAGES);
		int randomNumber;


		if((int) props.get(DISTRIBUTION) == 1) {
			for (int i = 0; i < props.get(PAGE_REQUESTS); i++) {
				randomNumber = ThreadLocalRandom.current().nextInt(0, numPages);
				pageRequest.offer(pages.get(randomNumber));
			}
			return pageRequest;
		}
		else{
			int multiWeight = props.get(MULTIWEIGHT);
			int weightBias =  props.get(WEIGHT_BIAS);
			int std = (numPages - weightBias) + multiWeight;
			
			for (int i = 0; i < pageRequests; i++) {
				do{
					randomNumber = (int) Math.round((ThreadLocalRandom.current().nextGaussian() * std)); 
				} while(randomNumber < 0 || randomNumber > numPages-1);
				pageRequest.offer(pages.get(randomNumber));       
			}
		}
		
		return pageRequest;
	}

	public static ArrayList<Page> genPages(ConfigParser prop) {
		ArrayList<Page> pages = new ArrayList<Page>();
		int numOfPages = prop.get(NUM_OF_PAGES);
		for (int i = 0; i < numOfPages; i++) {
			pages.add(new Page());
		}
		return pages;
	}
}
