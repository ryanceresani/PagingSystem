package pageremoval;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
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

		ArrayList<Page> pages = PageGen.genPages(cf);
		//Memory memory = new Memory(cf.get(CV.PAGE_FRAMES.getProperty()));
		//int pageRequest[] = createRandomPageSequence(NUM_OF_PAGE_REQUESTS, Pd.TOTAL_RANDOM, CLUSTERED);
		ArrayDeque<Page> pageRequests = PageGen.createRandomPageSequence(cf, pages);

		Memory lru = new Memory(cf.get(CV.PAGE_FRAMES.getProperty()), true);
		Memory fifo = new Memory(cf.get(CV.PAGE_FRAMES.getProperty()));
		System.out.println(pageRequests.toString());

		
		while(!pageRequests.isEmpty()){
			Page nextPage = pageRequests.poll();
			lru.pageRequest(nextPage);
			fifo.pageRequest(nextPage);
			System.out.println("LRU: " + lru.toString());
			System.out.println("FIFO: " + fifo.toString());
		}

		System.out.println(fifo.getCacheHits());
		System.out.println(lru.getCacheHits());
		
		
	}

	public enum CV{
		NUM_OF_PAGES("differentpages"),
		PAGE_FRAMES("pageframes"),
		PAGE_REQUESTS("pagerequests"),
		DISTRIBUTION("distribution"),
		MULTIWEIGHT("multiweight"),
		WEIGHT_BIAS("weightbias");

		private String text;

		CV(String text) {
			this.text = text;
		}

		public String getProperty() {
			return this.text;
		}
	}
}
