package com.b22tran.utils;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtils {

	// create method to determine amount of pages to connect to
	public int getNumberOfOrderPages(JSONObject paginationJson){
		int numberOfPages = -1;
		
		double ordPerPage = Integer.valueOf(
										String.valueOf(paginationJson.get("per_page")));
		double totalOrd = Integer.valueOf(
										String.valueOf(paginationJson.get("total")));
		numberOfPages = (int) Math.ceil(totalOrd/ordPerPage);
		
		if(numberOfPages < 1){
			numberOfPages = -1;
		}
		
		return numberOfPages;
	}
	
}
