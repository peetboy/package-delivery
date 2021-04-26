package com.bsc.packagedelivery.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryUtilsTest {

	@Test
	void isWeightValid() {
		// false assertion
		assertTrue(DeliveryUtils.isWeightValid("4.5"));
		assertTrue(DeliveryUtils.isWeightValid("04.5"));
		assertTrue(DeliveryUtils.isWeightValid("04"));
		assertTrue(DeliveryUtils.isWeightValid("4.50"));
		assertTrue(DeliveryUtils.isWeightValid("4.05"));
		assertTrue(DeliveryUtils.isWeightValid("4.051"));

		// true assertion
		assertFalse(DeliveryUtils.isWeightValid("4."));
		assertFalse(DeliveryUtils.isWeightValid("4.1234"));

	}

	@Test
	void isPostalValid() {
		// true assertion
		assertTrue(DeliveryUtils.isPostalValid("00000"));
		assertTrue(DeliveryUtils.isPostalValid("12345"));
		assertTrue(DeliveryUtils.isPostalValid("55555"));

		// false assertion
		assertFalse(DeliveryUtils.isPostalValid("123"));
		assertFalse(DeliveryUtils.isPostalValid("123456"));
		assertFalse(DeliveryUtils.isPostalValid("12x45"));
		assertFalse(DeliveryUtils.isPostalValid("ABC"));
		assertFalse(DeliveryUtils.isPostalValid(""));
	}
}