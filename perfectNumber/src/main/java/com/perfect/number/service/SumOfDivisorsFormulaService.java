package com.perfect.number.service;

import java.util.stream.LongStream;

import org.springframework.stereotype.Service;

/**
 * 
 * Perfect number implementation based on sum of its positive divisors, excluding the number itself. 
 * Example: 6 is perfect because has divisors 1, 2 and 3, and 1 + 2 + 3 = 6.
 *
 */
@Service("sumOfDivisorsFormula")
public class SumOfDivisorsFormulaService implements PerfectNumber {
	
	public long[] findDivisors(long n) {
		return LongStream.range(1, n).filter(x -> n % x == 0).toArray();
	}
	
	public boolean isPerfect(long n) {
		return LongStream.of(findDivisors(n)).sum() == n;
	}
	
	public long[] findPerfectInRange(long start, long end) {
		return LongStream.rangeClosed(start, end).filter(this::isPerfect).toArray();
	}
}
