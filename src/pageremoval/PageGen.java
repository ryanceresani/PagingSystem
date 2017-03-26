package pageremoval;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import config.ConfigParser;
import pageremoval.PageRemovalTester.CV;

public class PageGen {
	//Constants representing the name of config file keys
	//Used for polling the config file for the simulation
	static String NUM_OF_PAGES = CV.NUM_OF_PAGES.getProperty();
	static String PAGE_FRAMES = CV.PAGE_FRAMES.getProperty();
	static String PAGE_REQUESTS = CV.PAGE_REQUESTS.getProperty();
	static String DISTRIBUTION = CV.DISTRIBUTION.getProperty();
	static String MULTIWEIGHT = CV.MULTIWEIGHT.getProperty();
	static String WEIGHT_BIAS = CV.WEIGHT_BIAS.getProperty();	

	public static ArrayDeque<Page> createRandomPageSequence(ConfigParser props, ArrayList<Page> pages) {

		ArrayDeque<Page> pageRequest = new ArrayDeque<Page>();
		int pageRequests = props.get(PAGE_REQUESTS);
		int numPages =  props.get(NUM_OF_PAGES);
		int randomNumber;


		if(props.get(DISTRIBUTION) == 1) {
			for (int i = 0; i < props.get(PAGE_REQUESTS); i++) {
				randomNumber = ThreadLocalRandom.current().nextInt(0, numPages);
				pageRequest.offer(pages.get(randomNumber));
			}
			return pageRequest;
		}
		else{
			int multiWeight = props.get(MULTIWEIGHT);
			int weightBias =  props.get(WEIGHT_BIAS) * (numPages/10);
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
