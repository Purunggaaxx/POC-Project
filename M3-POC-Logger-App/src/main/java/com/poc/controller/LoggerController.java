package com.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.entity.LoggerEntity;
import com.poc.repository.LoggerRepository;

@RestController
@RequestMapping("/api")
public class LoggerController {
	
	
	@Autowired
	LoggerRepository loggerRepository;
	
	
	@GetMapping("/welcome")
	protected String welcome() {
		return "welcome";
	}
	
	@GetMapping("/logger")
	public List<LoggerEntity> getAllEntities(){
		List<LoggerEntity> loggerList = loggerRepository.findAll();
		return loggerList;
	}

}
