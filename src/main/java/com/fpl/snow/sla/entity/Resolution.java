package com.fpl.snow.sla.entity;

public class Resolution {
	
	private String inc_no;
	private String affected_user;
	private String Description;
	private String priority;
	private String support_group;
	private String assigned_to;
	private boolean hasBreached;
	private boolean hasExemption;
	
	
	public Resolution()
	{
		
	}
	
	public Resolution(String inc_no, String affected_user, String description, String priority, String support_group,
			boolean hasBreached, boolean hasExemption) {
		super();
		this.inc_no = inc_no;
		this.affected_user = affected_user;
		Description = description;
		this.priority = priority;
		this.support_group = support_group;
		this.hasBreached = hasBreached;
		this.hasExemption = hasExemption;
	}
	
	
	public String getAssigned_to() {
		return assigned_to;
	}


	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}


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
	public boolean isHasBreached() {
		return hasBreached;
	}
	public void setHasBreached(boolean hasBreached) {
		this.hasBreached = hasBreached;
	}
	public boolean isHasExemption() {
		return hasExemption;
	}
	public void setHasExemption(boolean hasExemption) {
		this.hasExemption = hasExemption;
	}


		
	
	
	

}
