package pageremoval;

import java.util.concurrent.atomic.AtomicInteger;

public class Page {
	boolean isAssigned;
	int timesUsed;
	private int id;
	static AtomicInteger nextId = new AtomicInteger(0);

	public Page(){
		isAssigned = false;
		timesUsed = 0;
		this.id = nextId.incrementAndGet();
	}

	public int getId() {
		return id;
	}

	public void setAssigned(boolean a) {
		isAssigned = a;
	}
	@Override
	public String toString() {
		return "Page " + id;
	}
}
