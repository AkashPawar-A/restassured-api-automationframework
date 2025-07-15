package com.onsite.payloadbuilder;

import com.onsite.context.TimeSheetDetails;
import com.onsite.pojo_request.TimeSheetEditRequest;

public class TimeSheetEditPayload {
	
	public static TimeSheetEditRequest builedtimeSheetEditRequest()
	{
		TimeSheetEditRequest editTimesheetObj = new TimeSheetEditRequest();
		
		editTimesheetObj.setBilling_activity_id(TimeSheetDetails.billing_activity_id);
		editTimesheetObj.setStart_time("2025-07-15T09:07:01.305Z");
		editTimesheetObj.setEnd_time("2025-07-15T13:08:01.305Z");
		editTimesheetObj.setDuration(241);
		editTimesheetObj.setId(TimeSheetDetails.timesheet_id);
		editTimesheetObj.setProject_id(TimeSheetDetails.project_id);
		editTimesheetObj.setNotes("test 1");
		editTimesheetObj.setParty_company_user_id(TimeSheetDetails.party_company_user_id);
		editTimesheetObj.setTimesheet_date("2025-07-14T18:30:00.001Z");
		editTimesheetObj.setUpdated("2025-07-15T08:08:13.185Z");
		
		String[] photosArray = {};
		editTimesheetObj.setPhotos(photosArray);
		
		return editTimesheetObj;
	}
}
