package pageremoval;

import java.util.HashSet;

public class Memory {
	HashSet<PageFrame> frames;
	int cacheHits;
	int cacheMisses;
	Page firstIn;
	
	public Memory(){
		
	}
	public Memory(int numFrames){
		frames = new HashSet<PageFrame>(numFrames);
	
	}
	
	
}
