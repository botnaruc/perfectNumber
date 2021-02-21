package com.perfect.number.controller;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perfect.number.service.PerfectNumber;

@Validated
@RestController
@RequestMapping("/perfect-numbers")
public class PerfectNumberController {
	
	@Qualifier("sumOfDivisorsFormula")
	@Autowired
	private PerfectNumber sumOfDivisorsFormulaService;
	
	@Qualifier("primeNumberFormula")
	@Autowired
	private PerfectNumber primeNumberFormulaService;
	
	@GetMapping("/verify/{number}")
	public boolean verify(@PathVariable(value = "number") @Min(5) @Max(Long.MAX_VALUE) long number,
			@RequestParam(name = "formula", defaultValue = "sum-of-divisors") String formula) {
		
		if(!(formula.equals("sum-of-divisors") || formula.equals("prime-number"))) {
			throw new ValidationException("Formula " + formula + " is not valid");
		}
		
		if (formula.equals("sum-of-divisors")) {
			return sumOfDivisorsFormulaService.isPerfect(number);
		} else if (formula.equals("prime-number")) {
			return primeNumberFormulaService.isPerfect(number);
		}

		return false;
	}
	
	@GetMapping("/range")
	public long[] findAllInRange(@RequestParam(name = "start") @Min(5) @Max(Long.MAX_VALUE) long start,
			@RequestParam(name = "end") @Min(5) @Max(Long.MAX_VALUE) long end,
			@RequestParam(name = "formula", defaultValue = "sum-of-divisors") String formula) {
		
		if(!(formula.equals("sum-of-divisors") || formula.equals("prime-number"))) {
			throw new ValidationException("Formula " + formula + " is not valid");
		}
		
		if(start > end) {
			throw new ValidationException("Parameter start can not be higher than end");
		}
		
		if(end - start > 10000) {
			throw new ValidationException("Range can not be higher than 10000");
		}
		
		if (formula.equals("sum-of-divisors")) {
			return sumOfDivisorsFormulaService.findPerfectInRange(start, end);
		} else if (formula.equals("prime-number")) {
			return primeNumberFormulaService.findPerfectInRange(start, end);
		}

		throw new ValidationException("Formula " + formula + " is not valid");
	}
}
