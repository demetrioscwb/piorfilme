package com.dgr.piorfilme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgr.piorfilme.service.result.ResultService;
import com.dgr.piorfilme.service.result.vo.PremiumIntervalVO;

@RestController
@RequestMapping("/api")
public class MovieController {
	
	@Autowired
	private ResultService service;
	
	@RequestMapping(value="/intervalopremios",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PremiumIntervalVO findAll() {
		return service.findWinners();
	}	
	

}
