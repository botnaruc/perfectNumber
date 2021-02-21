package com.perfect.number;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PerfectNumberControllerTests {
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void whenVerifyValidParam_defaultFormula_returnTrue() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/verify/6").toString(), String.class);
		assertEquals("true", response.getBody());
	}
	
	@Test
	public void whenVerifyValidParam_sumOfDivisorsFormula_returnTrue() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/verify/6?formula=sum-of-divisors").toString(), String.class);
		assertEquals("true", response.getBody());
	}
	
	@Test
	public void whenVerifyValidParam_primeNumberFormula_returnTrue() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/verify/6?formula=prime-number").toString(), String.class);
		assertEquals("true", response.getBody());
	}
	
	@Test
	public void whenVerifyInvalidParam_defaultFormula_returnTrue() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/verify/7").toString(), String.class);
		assertEquals("false", response.getBody());
	}
	
	@Test
	public void whenVerifyValidParam_wrongFormula_returnStringMessage() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/verify/6?formula=prime-number-formula").toString(), String.class);	
		assertEquals("Formula prime-number-formula is not valid", response.getBody());
	}
	
	@Test
	public void whenRangeValidParam_defaultFormula_returnArray() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/range?start=5&end=10000").toString(), String.class);
		assertEquals("[6,28,496,8128]", response.getBody());
	}
	
	@Test
	public void whenRangeValidParam_sumOfDivisorsFormula_returnArray() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/range?start=5&end=10000&formula=sum-of-divisors").toString(), String.class);
		assertEquals("[6,28,496,8128]", response.getBody());
	}
	
	@Test
	public void whenRangeValidParam_primeNumberFormula_returnArray() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/range?start=5&end=10000&formula=prime-number").toString(), String.class);
		assertEquals("[6,28,496,8128]", response.getBody());
	}

	@Test
	public void whenRangeInvalidParam_defaultFormula_returnArray() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/range?start=100&end=10").toString(), String.class);
		assertEquals("Parameter start can not be higher than end", response.getBody());
	}
	
	@Test
	public void whenRangeValidParam_wrongFormula_returnStringMessage() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/api/perfect-numbers/range?start=5&end=10000&formula=prime-number-formula").toString(), String.class);
		assertEquals("Formula prime-number-formula is not valid", response.getBody());
	}
}
