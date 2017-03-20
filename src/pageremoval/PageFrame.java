package pageremoval;

import java.util.concurrent.atomic.AtomicInteger;

public class PageFrame {
	Page currPage;
	private int id;
	static AtomicInteger nextId = new AtomicInteger();
	
	public PageFrame(){
		this.currPage = null;
		this.id = nextId.incrementAndGet();
	}
	
	public int getId() {
		return id;
	}

	public void setPage(Page p) {
		currPage = p;	
	}
	
}
