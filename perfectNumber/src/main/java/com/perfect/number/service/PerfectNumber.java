package com.perfect.number.service;

public interface PerfectNumber {
	boolean isPerfect(long perfectNumberToCheck);
	long[] findPerfectInRange(long start, long end);
}
