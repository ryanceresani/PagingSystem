package pageremoval;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class FIFOMemory extends LinkedHashMap<Integer, Page> {
	
	private int numFrames;
	private long requestCount = 0;
	private long cacheHits = 0;
		
	public FIFOMemory(int numFrames) {
		super(numFrames+1);
		this.numFrames = numFrames;
	}

	public void pageRequest(Page p){
		requestCount++;
		if(containsKey(p.getId())){
			cacheHits++;
		}
		put(p.getId(), p);
	}

	protected boolean removeEldestEntry(Entry<Integer, Page> entry) {
		return (size() > this.numFrames);
	} 
	
	public long getCacheHits() { return cacheHits; } 
}
