package com.customlog.customlog;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="CUSTOMLOG")
@NoArgsConstructor
public class CustomLog {
	
	@Id
	private String id;
	
	private String type;
	private String host;
	
	@Transient
	private long started;
	
	@Transient
	private long finished;
	
	private long duration;
	
	private boolean alert;

	public CustomLog(String id, String type, String host) {
		super();
		this.id = id;
		this.type = type;
		this.host = host;
	}
	
	
	
	
	

}
