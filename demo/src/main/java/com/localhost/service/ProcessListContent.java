package com.localhost.service;

import java.util.List;

import com.localhost.model.ListContent;
import com.localhost.repository.DatabaseUtils;

public class ProcessListContent {
	public static ListContent findContentById(String id) {
		List<ListContent> list = DatabaseUtils.readListContent();
		for (ListContent content : list) {
			if (content.getId().equals(id)) {
				return content;
			}
		}
		return null;
	}
	public static ListContent findContentInPendingItemById(String id) {
		List<ListContent> list =  DatabaseUtils.readListContentPendingItem();
		for (ListContent content : list) {
			if(content.getId().equals(id)) {
				return content;
			}
		}
		return null;
	}
}
