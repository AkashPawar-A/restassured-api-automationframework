package com.onsite.payloadbuilder;

import com.onsite.context.ProjectDetail;
import com.onsite.pojo_request.ProjectListRequest;

public class ProjectListPayload {
	
	public static ProjectListRequest projectListRequest(int count, int page) {
		
		ProjectListRequest projectListRequest = new ProjectListRequest();
		
		projectListRequest.setCount(count);
		projectListRequest.setPage(page);
		projectListRequest.setCompany_id(ProjectDetail.companyId);
		
		return projectListRequest;
	}
}
