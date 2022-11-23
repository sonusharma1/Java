package com.bvk.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.bvk.service.ServiceArith;

public class ServiceArithTest
{
	private ServiceArith serviceArith;
	
	@Before
	public void setUp() throws Exception {
		serviceArith = new ServiceArith();
	}

	@After
	public void tearDown() throws Exception {
		serviceArith = null;
	}

	@Test
	public final void testAdd() {
		assertEquals(40, serviceArith.add(20, 20));
	}

	@Test
	public final void testSubtract() {
		assertEquals(0, serviceArith.subtract(10, 10));
	}

	@Ignore
	public final void testMultiply() {
		assertEquals(100, serviceArith.multiply(10, 10));
	}

	@Test
	public final void testDividePositive() {
		assertEquals(1, serviceArith.divide(10, 10));
	}
	
	@Test(expected=ArithmeticException.class)
	public final void testDivideNegative() {
		assertEquals(1, serviceArith.divide(10, 0));
	}
}