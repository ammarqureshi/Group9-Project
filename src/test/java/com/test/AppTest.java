package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void testAdd() {
		assertEquals(8,App.Add(4,4));

	}

	@Test
	public void testMult(){
		assertEquals(12,App.Mul(3,4));
	}

}
