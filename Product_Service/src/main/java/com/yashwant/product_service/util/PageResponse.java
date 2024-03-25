package com.yashwant.product_service.util;

import java.util.List;

import lombok.Data;

@Data
public class PageResponse<T> {
	
	private List<T>content;
	private int pageNumber;
	private int pageSize;
	private long totalElememts;
	private int totalPages;
	private boolean lastPage;
	

}
