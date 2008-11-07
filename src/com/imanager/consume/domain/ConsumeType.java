package com.imanager.consume.domain;

import com.imanager.common.Basement;

public class ConsumeType  extends Basement{
	
	private int consumeTypeId;
	
	private String consumeType;

	public int getConsumeTypeId() {
		return consumeTypeId;
	}

	public void setConsumeTypeId(int consumeTypeId) {
		this.consumeTypeId = consumeTypeId;
	}

	public String getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}

}
