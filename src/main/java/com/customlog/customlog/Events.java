package com.customlog.customlog;

import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class Events {
	
	public Events(CustomLog log) {
		// TODO Auto-generated constructor stub
		id=log.getId();
		type=log.getType();
		host=log.getHost();
		duration=log.getDuration();
		alert=log.isAlert();
	}

	private String id;
	
	private String type;
	private String host;
	
	private long duration;
	
	private boolean alert;
}
