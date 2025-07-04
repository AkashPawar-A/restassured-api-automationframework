package com.onsite.payloadbuilder;

import java.util.Arrays;
import java.util.List;

import com.onsite.pojo_request.TimeSheetRequest;

public class TimeSheetCreatePayload {
	
	public static TimeSheetRequest buildtimeSheetCreatePayload() {
		
	TimeSheetRequest timesheetObj = new TimeSheetRequest();
	
	timesheetObj.setCompany_id("75916659-9cbe-4ca7-812e-181a29229772");
	timesheetObj.setDuration(14640.0);
	timesheetObj.setEnd_time("2025-07-04T13:03:31.377Z");
	timesheetObj.setNotes("testing frist");
	timesheetObj.setParty_company_user_id("8f1e9ece-d44c-4ca2-ae3f-986267a8e567");
	timesheetObj.setProject_id("e3266525-81ad-47e5-9796-d7ffea6568ff");
	timesheetObj.setStart_time("2025-07-04T08:59:31.377Z");
	timesheetObj.setTimesheet_date("2025-07-04T18:30:00.001Z");
	
	String[] photosArray = {};
	List<String> photosList = Arrays.asList(photosArray);
	timesheetObj.setPhoto(photosList);
	
	return timesheetObj;
	
	}
}
