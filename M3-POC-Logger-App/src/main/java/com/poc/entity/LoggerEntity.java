package com.poc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "product_logger")
public class LoggerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loggerId;
	
	
	private String loggerData;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date loggerDate;
	
	public LoggerEntity(Long loggerId, String loggerData, Date loggerDate) {
		super();
		this.loggerId = loggerId;
		this.loggerData = loggerData;
		this.loggerDate = loggerDate;
	}

	public Long getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(Long loggerId) {
		this.loggerId = loggerId;
	}

	public String getLoggerData() {
		return loggerData;
	}

	public void setLoggerData(String loggerData) {
		this.loggerData = loggerData;
	}

	public Date getLoggerDate() {
		return loggerDate;
	}

	public void setLoggerDate(Date loggerDate) {
		this.loggerDate = loggerDate;
	}

	public LoggerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
