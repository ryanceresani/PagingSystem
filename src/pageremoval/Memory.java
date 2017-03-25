package pageremoval;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Memory extends LinkedHashMap<Integer, Page> {

	private static final long serialVersionUID = 4516181972475108065L;
	
	private int numFrames;
	private long requestCount = 0;
	private long cacheHits = 0;

	public Memory(int numFrames) {
		this(numFrames, false);
	}
	
	public Memory(int numFrames, boolean lru) {
		super(numFrames+1, 1.0f, lru);
		this.numFrames = numFrames;
	}
	
	public void pageRequest(Page p){
		requestCount++;
		boolean hit = false;
		if(containsKey(p.getId())){
			cacheHits++;
			hit = true;
		}
		put(p.getId(), p);
		
	}

	protected boolean removeEldestEntry(Entry<Integer, Page> entry) {
		return (size() > this.numFrames);
	} 

	public long getCacheHits() { return cacheHits; } 
	
	public long getRequestCount() { return requestCount; }
	
	
	public void printFrames(){
		long showRequests = Math.min(requestCount, 10);
		StringBuilder requestFormat = new StringBuilder("| %5s | %2s");
		System.out.format("+-------+");
		System.out.format("|       |");
		System.out.format("+-------+");
	}
}

