package com.dgr.piorfilme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.dgr.piorfilme.service.result.ResultService;

@Configuration
public class InitialDataConfiguration{
	
	@Autowired
	private ResultService resultService;

	@PostConstruct
    public void postConstruct() {

    	resultService.importResults();

    }
}
