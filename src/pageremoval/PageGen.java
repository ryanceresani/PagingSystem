package pageremoval;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import pageremoval.PageRemovalTester.Pd;

public class PageGen {
	
	final static int SOLO_WEIGHT = 4;
	final static int FIRST_WEIGHT = 4;
	final static int SECOND_WEIGHT = 2;
	final static int THIRD_WEIGHT = 3;
	
	static Stack<Integer> multiWeights = new Stack<Integer>();
	
	public static ArrayDeque<Page> createRandomPageSequence(int numOfRequests, Pd pd, ArrayList<Page> pages) {
		multiWeights.push(THIRD_WEIGHT);
		multiWeights.push(SECOND_WEIGHT);
		multiWeights.push(FIRST_WEIGHT);
		
		ArrayDeque<Page> pageRequest = new ArrayDeque<Page>();
		int randomNumber;
		
		
		if (pd.equals(Pd.TOTAL_RANDOM)){
			for (int i = 0; i < numOfRequests; i++) {
				randomNumber = ThreadLocalRandom.current().nextInt(0, PageRemovalTester.NUM_OF_PAGES);
				pageRequest.offer(pages.get(randomNumber));
			}
			return pageRequest;
		}

		TreeMap<Integer, Page> weights = new TreeMap<Integer, Page>();
		int sumOfWeights = 0;
		
		if(pd.equals(Pd.SOLO_WEIGHTED)){
			weights.put(SOLO_WEIGHT, pages.get(0));
			sumOfWeights += SOLO_WEIGHT;
			for (Page p : pages.subList(1, pages.size())) {
				weights.put(sumOfWeights, p);
			}
		}		
		
		else if(pd.equals(Pd.MULTI_WEIGHTED)){
			for (Page p : pages) {
				if(!multiWeights.isEmpty()){
					sumOfWeights += multiWeights.pop();
					weights.put(sumOfWeights, p);
				}
				else{
					weights.put(sumOfWeights, p);
					sumOfWeights++;
				}
			}
		}
		
		for (int i = 0; i < numOfRequests; i++) {
			randomNumber = ThreadLocalRandom.current().nextInt(1, sumOfWeights);
			Page req = weights.ceilingEntry(randomNumber).getValue();
			pageRequest.offer(req);
		}
		
		return pageRequest;
	}

	public static ArrayList<Page> genPages(int numOfPages) {
		ArrayList<Page> pages = new ArrayList<Page>();
		for (int i = 0; i < numOfPages; i++) {
			pages.add(new Page());
		}
		return pages;
	}
}
