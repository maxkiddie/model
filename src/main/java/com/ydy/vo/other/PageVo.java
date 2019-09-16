/**
 * 
 */
package com.ydy.vo.other;

import java.util.List;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 下午5:29:17
 */
public class PageVo<T> extends BaseVo {

	private List<T> list;
	private int total = 0;//
	private int totalPage = 1;
	private int size = 10;
	private int page = 1;

	public void setTotal(long total) {
		this.total = (int) total;
		if (this.total < 0)
			this.total = 0 - this.total;
		resize();
	}

	private void resize() {
		this.totalPage = (this.total % this.size) == 0 ? (this.total / this.size) : ((this.total / this.size) + 1);
		if (this.page < 1) {
			this.page = 1;
		} else if (this.page > this.totalPage) {
			this.page = this.totalPage;
		}
	}

	public PageVo(Integer page, Integer size) {
		if (page != null)
			this.page = Math.abs(page);
		if (size != null) {
			if (size <= 0) {
				this.size = 10;
			} else if (size >= 1000) {
				this.size = 1000;
			} else {
				this.size = size;
			}
		}
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

}
