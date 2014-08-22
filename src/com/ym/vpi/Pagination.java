package com.ym.vpi;

import java.util.List;

public class Pagination {
	private int countPage;
	private int from;
	private int nowPage;
	private List<? extends Object> rows;
	private int size;
	private int total;

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public List<? extends Object> getRows() {
		return rows;
	}

	public void setRows(List<? extends Object> rows) {
		this.rows = rows;
	}

	public int getSize() {
		return this.rows.size();
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
