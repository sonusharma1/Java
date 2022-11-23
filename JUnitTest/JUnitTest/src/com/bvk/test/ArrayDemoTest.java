package com.bvk.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.bvk.service.ArrayDemo;

public class ArrayDemoTest {

	// positive
	@Test
	void case1() {
		int[] arr = { -18, -12, -4, 0, 2, 3 };
		assertEquals(4, new ArrayDemo().search(arr, 2));
	}

	// positive
	@Test
	void case2() {
		int[] arr = { -18, -12, -4, 0, 2, 3 };
		assertEquals(1, new ArrayDemo().search(arr, -12));
	}

	// negative
	@Test
	void case3() {
		int[] arr = { -18, -12, -4, 0, 2, 3 };
		assertNotEquals(0, new ArrayDemo().search(arr, -12));
	}
}
