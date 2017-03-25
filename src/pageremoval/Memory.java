package pageremoval;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Memory {
	ArrayList<PageFrame> frames;
	Stack<PageFrame> freeFrames;
	HashMap<Page, PageFrame> assignments;
	protected int cacheHits;
	protected int cacheMisses;

	

	public Memory(int numFrames){
		frames = new ArrayList<PageFrame>();
		assignments = new HashMap<Page, PageFrame>();
		for (int i = 0; i < numFrames; i++) {
			PageFrame newFrame = new PageFrame();
			frames.add(newFrame);
			freeFrames.add(newFrame);
		}
	}
	
	public int getCacheHits() { return cacheHits; }
	
	public int getCacheMisses() { return cacheMisses;	}
	
}
