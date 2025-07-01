package com.onsite.payloadbuilder;

import com.onsite.context.ProjectDetail;
import com.onsite.pojo_request.ProjectEditStatausRequest;

public class ProjectEditStatusPayload {
	
	public static ProjectEditStatausRequest projectEditStatusPayload() {
		
		ProjectEditStatausRequest editStatusPayload = new ProjectEditStatausRequest();
		
		editStatusPayload.setId(ProjectDetail.projectId);
		editStatusPayload.setStatus("Complete");
		
		return editStatusPayload;
	}
}
