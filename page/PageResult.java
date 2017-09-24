package com._520it.wms.page;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageResult {
	private Integer currentPage;
	private Integer pageSize;
	private Integer prevPage;
	private Integer nextPage;
	private Integer endPage;
	private Integer totalCount;
	private List<?> data;
	public static final PageResult emptyResult = new PageResult(1, 5, 0, Collections.EMPTY_LIST);

	public PageResult(Integer currentPage, Integer pageSize, Integer totalCount, List<?> data) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.data = data;
		endPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		prevPage = currentPage - 1 >= 1 ? currentPage - 1 : 1;
		nextPage = currentPage + 1 <= endPage ? currentPage + 1 : endPage;
	}
}
