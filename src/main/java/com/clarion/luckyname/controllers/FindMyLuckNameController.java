package com.clarion.luckyname.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clarion.luckyname.constants.Constants;
import com.clarion.luckyname.domain.dto.UserRequestDto;
import com.clarion.luckyname.domain.dto.UserResponseDto;
import com.clarion.luckyname.service.FindMyLuckNameService;

@RestController
public class FindMyLuckNameController {
	
	private static final Logger logger = LoggerFactory.getLogger(FindMyLuckNameController.class);
	private FindMyLuckNameService findMyLuckyNameService;
	
	@Autowired
	public FindMyLuckNameController(FindMyLuckNameService findMyLuckyNameService) {
		this.findMyLuckyNameService = findMyLuckyNameService;
	}
	
	@PostMapping(Constants.FIND_MY_LUCKY_NAME)
	public ResponseEntity<?> findMyLuckName(@Valid @RequestBody UserRequestDto request) {
		logger.info("___INIT___ findMyLuckName request={}", request);
		UserResponseDto responseDto = findMyLuckyNameService.initializeSuggestions(request);
		logger.info("___DONE___ findMyLuckName response={}", responseDto);
		return ResponseEntity.ok().body(responseDto);
	}
}
