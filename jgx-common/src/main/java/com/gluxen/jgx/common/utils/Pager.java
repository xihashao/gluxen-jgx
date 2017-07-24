package com.gluxen.jgx.common.utils;

/**
 * 分页器
 * 
 * @author lijunfeng
 * @date 2016年9月3日 modify history
 */
public class Pager {

	private int pageSize;
	private int pageIndex;

	public Pager(int pageSize, int pageIndex) {
		super();
		this.pageSize = pageSize;
		this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getBeginNum() {

		return (pageIndex - 1) * pageSize + 1;
	}

	public int getEndNum() {
		return (pageIndex) * pageSize;
	}

}
