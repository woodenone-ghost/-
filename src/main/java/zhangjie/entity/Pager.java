package zhangjie.entity;

import java.util.Collections;
import java.util.List;

public class Pager<T> {

	private int pageNum = 1;
	private int pageSize = 10;
	private long total = 0;
	private List<T> rows;

	public Pager(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public Pager(int pageNum, int pageSize, long total) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
	}

	public int getStartNum() {
		return (pageNum - 1) * pageSize;
	}

	public int getStartIdx() {
		return (pageNum - 1) * pageSize + 1;
	}

	public int getEndIdx() {
		return pageNum * pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@SuppressWarnings("unchecked")
	public List<T> getRows() {
		return (List<T>) (rows == null ? Collections.emptyList() : rows);
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
