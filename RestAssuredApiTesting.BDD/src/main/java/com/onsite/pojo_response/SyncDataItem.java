package com.onsite.pojo_response;

import java.time.LocalDateTime;

import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface;

public class SyncDataItem {
	
	private String app_type;
	private LocalDateTime sync_time;
	private String sync_by;
	private String display_text;
	private Interface[] platform_data;

	
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public LocalDateTime getSync_time() {
		return sync_time;
	}
	public void setSync_time(LocalDateTime sync_time) {
		this.sync_time = sync_time;
	}
	public String getSync_by() {
		return sync_by;
	}
	public void setSync_by(String sync_by) {
		this.sync_by = sync_by;
	}
	public String getDisplay_text() {
		return display_text;
	}
	public void setDisplay_text(String display_text) {
		this.display_text = display_text;
	}
	public Interface[] getPlatform_data() {
		return platform_data;
	}
	public void setPlatform_data(Interface[] platform_data) {
		this.platform_data = platform_data;
	}
	
}
