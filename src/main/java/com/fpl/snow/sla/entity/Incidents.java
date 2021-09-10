package com.fpl.snow.sla.entity;

public class Incidents {
	
	private String inc_no;
	private String affected_user;
	private String Description;
	private String priority;
	private String support_group;
	private String assigned_to;
	private boolean hasBreached_Response;
	private boolean hasExemption_Response;
	private boolean hasBreached_final_Response;
	private boolean hasBreached_Resolution;
	private boolean hasExemption_Resolution;
	private boolean hasBreached_final_Resolution;
	
	public String getInc_no() {
		return inc_no;
	}
	public void setInc_no(String inc_no) {
		this.inc_no = inc_no;
	}
	public String getAffected_user() {
		return affected_user;
	}
	public void setAffected_user(String affected_user) {
		this.affected_user = affected_user;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSupport_group() {
		return support_group;
	}
	public void setSupport_group(String support_group) {
		this.support_group = support_group;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public boolean isHasBreached_Response() {
		return hasBreached_Response;
	}
	public void setHasBreached_Response(boolean hasBreached_Response) {
		this.hasBreached_Response = hasBreached_Response;
	}
	public boolean isHasExemption_Response() {
		return hasExemption_Response;
	}
	public void setHasExemption_Response(boolean hasExemption_Response) {
		this.hasExemption_Response = hasExemption_Response;
	}
	public boolean isHasBreached_Resolution() {
		return hasBreached_Resolution;
	}
	public void setHasBreached_Resolution(boolean hasBreached_Resolution) {
		this.hasBreached_Resolution = hasBreached_Resolution;
	}
	public boolean isHasExemption_Resolution() {
		return hasExemption_Resolution;
	}
	public void setHasExemption_Resolution(boolean hasExemption_Resolution) {
		this.hasExemption_Resolution = hasExemption_Resolution;
	}
	
	
	
	

}
