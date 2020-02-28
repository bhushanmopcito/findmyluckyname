package com.clarion.luckyname.controllers;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clarion.luckyname.domain.dto.UserRequestDto;
import com.clarion.luckyname.domain.dto.UserResponseDto;
import com.clarion.luckyname.service.FindMyLuckNameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FindMyLuckyNameControllerTest {

	@InjectMocks
	FindMyLuckNameService findMyLuckNameService;
	
	@Autowired
	TestRestTemplate restTemplate;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findSuggestions() throws Exception {
		UserRequestDto userRequestDto = prepareTestData();
		UserResponseDto responseDto = findMyLuckNameService.initializeSuggestions(userRequestDto);
		assertEquals("bAli,Abli,Albi", responseDto.getSuggestions());
		assertEquals(Character.valueOf('b'), responseDto.getSuggestedLetter());
		assertEquals(Integer.valueOf(6), responseDto.getDobLuckyNumber());
		assertEquals(Integer.valueOf(4), responseDto.getNameLuckyNumber());
	}
	
	@Test
	public void emptyDOB() throws JSONException, JsonProcessingException {
		UserRequestDto userRequestDto = prepareTestData();
		userRequestDto.setDateOfBirth("");
		String nameInJson = new ObjectMapper().writeValueAsString(userRequestDto);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(nameInJson, headers);

		// send json with POST
		ResponseEntity<String> response = restTemplate.postForEntity("/api/findmyluckyname", entity, String.class);
		System.out.println(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void emptyName() throws JSONException, JsonProcessingException {
		UserRequestDto userRequestDto = prepareTestData();
		userRequestDto.setName("");
		String nameInJson = new ObjectMapper().writeValueAsString(userRequestDto);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(nameInJson, headers);

		// send json with POST
		ResponseEntity<String> response = restTemplate.postForEntity("/api/findmyluckyname", entity, String.class);
		System.out.println(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	private UserRequestDto prepareTestData() {
		return new UserRequestDto("Ali", "1/11/2001");
	}

}
