package com.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.poc.entity.LoggerEntity;
import com.poc.repository.LoggerRepository;

@Service
public class LoggerServiceImplementation implements LoggerService{

	@Autowired
	LoggerRepository loggerRepository;
	
	@KafkaListener(topics="Product_Logger", groupId="productId")
	public void consumerTopics(String loggerData) {
		System.out.println("Log: "+loggerData);
		LoggerEntity entity = new LoggerEntity();
		entity.setLoggerData(loggerData);
		loggerRepository.save(entity);
	}
}
