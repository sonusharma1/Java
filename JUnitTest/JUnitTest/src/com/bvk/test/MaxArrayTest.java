package com.bvk.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.bvk.service.MaxInArray;

public class MaxArrayTest {

	// positive
	@Test
	void case1() {
		int[] arr = { 12, 25, 41, 32, 1, 78, 45 };
		assertEquals(78, new MaxInArray().findMax2(arr));
	}

	// negative
	@Test
	void case2() {
		int[] arr = { 12, 25, 41, 32, 1, 78, 45 };
		assertEquals(7, new MaxInArray().findMax2(arr));
	}
}
