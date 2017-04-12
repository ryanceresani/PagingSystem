package pageremoval;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author ceresanr
 * Memory class that is initialized as a LinkedHashMap
 * LinkedHashMap is a hashmap backed by a double-linked list
 * It is sorted based on the page removal algorithm
 * 
 */
public class Memory extends LinkedHashMap<Integer, Page> {

	private static final long serialVersionUID = 4516181972475108065L;
	
	private int numFrames;
	private long requestCount = 0;
	private long cacheHits = 0;
	List<Object[]> timeline;

	public Memory(int numFrames) {
		this(numFrames, false);
	}
	
	public Memory(int numFrames, boolean lru) {
		super(numFrames+1, 1.0f, lru);
		this.numFrames = numFrames;
		timeline = new ArrayList<Object[]>();
	}
	
	/**
	 * A page is requested from the cache/memory
	 * @param a Page object
	 * 
	 **/
	public void pageRequest(Page p){
		requestCount++;
		if(containsKey(p.getId())){
			cacheHits++;
		}
		put(p.getId(), p);
		timeline.add(this.values().toArray());
	}

	/* (non-Javadoc)
	 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
	 */
	protected boolean removeEldestEntry(Entry<Integer, Page> entry) {
		return (size() > this.numFrames);
	} 

	public long getCacheHits() { return cacheHits; } 
	
	public long getRequestCount() { return requestCount; }
	
	public double getSuccessRate() { return (double) cacheHits/requestCount; }

	public void printStatistics() {
		NumberFormat percent = NumberFormat.getPercentInstance();
		double successRate = (double) cacheHits/requestCount;
		System.out.println("Cache Hits: " + cacheHits);
		System.out.println("Cache Misses: " + (requestCount - cacheHits));
		System.out.println("Success Rate: " + percent.format(successRate) + "\n");
	}
}

