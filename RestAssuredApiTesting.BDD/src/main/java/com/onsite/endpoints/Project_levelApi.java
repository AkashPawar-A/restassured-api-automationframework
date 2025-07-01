package com.onsite.endpoints;

public class Project_levelApi {

	public static final String CREATE_PROJECT = "/add/project";
	public static final String GET_PROJECT = "/detail/project/{projectId}";  // using path param
	public static final String DELETE_PROJECT = "/delete/project/{projectId}";
	public static final String UPDATE_PROJECT = "/edit/project";
	public static final String UPDATE_STATUS = "/edit/project/status";
	public static final String PROJECT_LIST = "/list/dashboard/project";
}
