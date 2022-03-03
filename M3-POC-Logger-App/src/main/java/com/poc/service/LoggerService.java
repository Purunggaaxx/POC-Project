package com.poc.service;

import org.springframework.stereotype.Service;

@Service
public interface LoggerService {

	public void consumerTopics(String loggerData);
}
