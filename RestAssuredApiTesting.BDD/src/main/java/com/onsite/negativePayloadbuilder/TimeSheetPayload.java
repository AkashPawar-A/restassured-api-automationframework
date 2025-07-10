package com.onsite.negativePayloadbuilder;

import java.util.Arrays;
import java.util.List;

import com.onsite.pojo_request.TimeSheetCreateRequest;

public class TimeSheetPayload {
	
	public static TimeSheetCreateRequest buildtimeSheetCreatePayload() {
		
		TimeSheetCreateRequest timeSheetObj = new TimeSheetCreateRequest();
		
		timeSheetObj.setCompany_id("75916659-9cbe-4ca7-812e-181a29229772");
		timeSheetObj.setDuration(3600);
		timeSheetObj.setEnd_time("2025-07-06T08:32:39.077Z");
		timeSheetObj.setNotes("testing frist");
		timeSheetObj.setParty_company_user_id("8f1e9ece-d44c-4ca2-ae3f-986267a8e568");
		timeSheetObj.setProject_id("e3266525-81ad-47e5-9796-d7ffea6568fg");
		timeSheetObj.setStart_time("2025-07-06T07:32:39.077Z");
		timeSheetObj.setTimesheet_date("2025-07-05T18:30:00.001Z");
		
		String[] photosArray = {};
		List<String> photosList = Arrays.asList(photosArray);
		timeSheetObj.setPhoto(photosList);
		
		return timeSheetObj;
		
	}
}
