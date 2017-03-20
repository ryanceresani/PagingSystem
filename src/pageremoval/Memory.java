package pageremoval;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Memory {
	ArrayList<PageFrame> frames;
	HashMap<Integer, PageFrame> freeFrames;
	int cacheHits;
	int cacheMisses;
	ArrayDeque<Page> firstIn;

	public Memory(int numFrames){
		frames = new ArrayList<PageFrame>();
		ArrayDeque<Page> firstIn = new ArrayDeque<Page>();
		for (int i = 0; i < numFrames; i++) {
			PageFrame newFrame = new PageFrame();
			frames.add(newFrame);
			freeFrames.put(newFrame.getId(), newFrame);
		}
	}

	public void pageRequest(Page p){
		if(p.isAssigned){
			cacheHits++;
		}
		else{
			cacheMisses++;
			if(freeFrames.isEmpty()){

			}
		}
	}
}
