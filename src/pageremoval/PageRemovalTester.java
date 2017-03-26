package pageremoval;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import config.ConfigParser;

public class PageRemovalTester {

	public static void main(String[] args) throws IOException {

		ConfigParser cf = new ConfigParser();

		int testRuns = cf.get(CV.TEST_RUNS.getProperty());
		double totalLRUSuccess = 0;
		double totalFIFOSuccess = 0;
		int currentRun=1;

		Memory lru = new Memory(cf.get(CV.PAGE_FRAMES.getProperty()), true);
		Memory fifo = new Memory(cf.get(CV.PAGE_FRAMES.getProperty()));
		ArrayList<Page> pages = PageGen.genPages(cf);

		while(currentRun <= testRuns){
			ArrayDeque<Page> pageRequests = PageGen.createRandomPageSequence(cf, pages);
			lru = new Memory(cf.get(CV.PAGE_FRAMES.getProperty()), true);
			fifo = new Memory(cf.get(CV.PAGE_FRAMES.getProperty()));
			
			System.out.println("------RUN NUMBER " + currentRun + "----------");
			
			System.out.print("Requests:  ");
			for (Page p : pageRequests) {
				p.printPage();
			}
			System.out.println();
			System.out.println();


			//Send all the requests to the different memory types
			while(!pageRequests.isEmpty()){
				Page nextPage = pageRequests.poll();
				lru.pageRequest(nextPage);
				fifo.pageRequest(nextPage);
			}

			//Print the graphic outputs
			for (int i = 0; i < lru.size(); i++) {
				System.out.print("Frame " + (i+1) + ":   ");
				for (int j = 0; j < lru.timeline.size(); j++) {
					try{
						((Page) lru.timeline.get(j)[i]).printPage();
					}
					catch(Exception e){
						Page.printEmptyPage();
					}
				}
				System.out.println();
			}		

			System.out.println();
			System.out.println("++FIFO Stats");
			fifo.printStatistics();
			System.out.println("++LRU Stats");
			lru.printStatistics();
			
			totalLRUSuccess += lru.getSuccessRate();
			totalFIFOSuccess += fifo.getSuccessRate();
			currentRun++;
		}
		NumberFormat percent = NumberFormat.getPercentInstance();
		System.out.println("++AGGREGATE AVERAGES STATS");
		System.out.println("Test Runs: " + testRuns);
		System.out.println("Requests per Run: " + cf.get(CV.PAGE_REQUESTS.getProperty()));
		System.out.println("FIFO Success Rate: " + percent.format(totalFIFOSuccess/testRuns));
		System.out.println("LRU Success Rate: " + percent.format(totalLRUSuccess/testRuns));
		
	}

	public enum CV{
		TEST_RUNS("testruns"),
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
