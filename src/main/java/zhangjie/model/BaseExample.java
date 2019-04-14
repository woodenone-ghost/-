package zhangjie.model;

public class BaseExample {
	private int startNumber;
	private int pageSize;
	private boolean isPagination = false;
	
	public int getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(int startNumber) {
		this.isPagination=true;
		this.startNumber = startNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.isPagination=true;
		this.pageSize = pageSize;
	}
	public boolean isPagination() {
		return isPagination;
	}
	public void setPagination(boolean isPagination) {
		this.isPagination = isPagination;
	}
	
	
}
