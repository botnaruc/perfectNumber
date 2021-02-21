package com.perfect.number.service;

import java.util.stream.LongStream;

import org.springframework.stereotype.Service;

/**
 * 
 * Perfect number implementation based on Euclid formula: when 2p − 1 is prime then 2p−1(2p − 1) is perfect.
 * Example: 8128 is perfect because for p = 7, 2pow6(2pow7 − 1) = 64 × 127 = 8128.
 *
 */
@Service("primeNumberFormula")
public class PrimeNumberFormulaService implements PerfectNumber {

	public long[] findPerfectInRange(long start, long end) {
		return LongStream.rangeClosed(start, end).filter(this::isPerfect).toArray();
	}

	public boolean isPerfect(long perfectNumberToCheck) {
		return calculatePerfectNumber(perfectNumberToCheck) == perfectNumberToCheck;
	}

	public double calculatePerfectNumber(long perfectNumberToCheck) {
		double partialResult;
		double formulaResult = 0;
		double powerTo = 2.0;
		boolean isPrime = false;

		do {
			partialResult = (Math.pow(2.0, powerTo) - 1);

			isPrime = isPrime((long) partialResult);

			if (isPrime) {
				formulaResult = Math.pow(2.0, powerTo - 1) * partialResult;
			}

			powerTo++;
		} while (formulaResult != perfectNumberToCheck && formulaResult < perfectNumberToCheck);

		return formulaResult;
	}

	public boolean isPrime(long num) {
		return LongStream.rangeClosed(2, num / 2).noneMatch(i -> num % i == 0);
	}
}
