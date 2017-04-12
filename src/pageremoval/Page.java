package pageremoval;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ceresanr
 * Framework page class that can be processed by a memory manager
 */
public class Page {
	int timesUsed;
	private int id;
	static AtomicInteger nextId = new AtomicInteger(0);

	public Page(){
		this.id = nextId.incrementAndGet();
	}

	public int getId() {
		return id;
	}

	
	public void printPage() {
		String format = new String("| %7s | %2s");
		System.out.format(format, "Page " + id, "");
	}
	
	public String toString(){
		return "Page " + id;
	}

	public static void printEmptyPage() {
		String format = new String("| %7s | %2s");
		System.out.format(format, "", "");
		
	}
}
