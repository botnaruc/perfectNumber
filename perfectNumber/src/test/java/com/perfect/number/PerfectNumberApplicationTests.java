package com.perfect.number;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.perfect.number.service.PerfectNumber;

@SpringBootTest
class PerfectNumberApplicationTests {

	@Qualifier("sumOfDivisorsFormula")
	@Autowired
	private PerfectNumber sumOfDivisorsFormulaService;
	
	@Qualifier("primeNumberFormula")
	@Autowired
	private PerfectNumber primeNumberFormulaService;

	@Test
	public void contextLoads() throws Exception {
		assertThat(sumOfDivisorsFormulaService).isNotNull();
		
		assertThat(primeNumberFormulaService).isNotNull();
	}

	@Test
	public void whenVerifyValidParam_returnTrue() {
		assertThat(sumOfDivisorsFormulaService.isPerfect(6)).isTrue();
		
		assertThat(primeNumberFormulaService.isPerfect(6)).isTrue();
	}
	
	@Test
	public void whenVerifyInvalidParam_returnTrue() {
		assertThat(sumOfDivisorsFormulaService.isPerfect(7)).isFalse();
		
		assertThat(primeNumberFormulaService.isPerfect(7)).isFalse();
	}
	
	@Test
	public void whenRangeValidParam_returnArray() {
		assertThat(sumOfDivisorsFormulaService.findPerfectInRange(5, 10000)).isEqualTo(new long[] { 6,28,496,8128 });
		
		assertThat(primeNumberFormulaService.findPerfectInRange(5, 10000)).isEqualTo(new long[] { 6,28,496,8128 });
	}
	
	@Test
	public void whenRangeInvalidParam_returnEmptyArray() {
		assertThat(sumOfDivisorsFormulaService.findPerfectInRange(10, 5)).isEqualTo(new long[] { });
		
		assertThat(primeNumberFormulaService.findPerfectInRange(10, 5)).isEqualTo(new long[] { });
	}
}
