package com.imanager.contact.domain;

import com.imanager.common.Basement;

public class ContactType extends Basement{
	
	private int contactTypeId;
	
	private String contactType;

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public int getContactTypeId() {
		return contactTypeId;
	}

	public void setContactTypeId(int contactTypeId) {
		this.contactTypeId = contactTypeId;
	}

}
