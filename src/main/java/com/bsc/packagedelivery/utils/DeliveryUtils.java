package com.bsc.packagedelivery.utils;

import com.bsc.packagedelivery.beans.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class DeliveryUtils {
	private static final Logger log = LoggerFactory.getLogger(DeliveryUtils.class);
	private static final String WEIGHT_REGEX = "^[0-9]{1}(\\d*)?(\\.\\d{1,3})?$";
	private static final String POSTAL_REGEX = "^[0-9]{5}?$";


	public static boolean isWeightValid(String weight) {
		try {
			Float.parseFloat(weight);
			return weight.matches(WEIGHT_REGEX);
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public static boolean isPostalValid(String postal) {
		try {
			return postal.matches(POSTAL_REGEX);
		} catch (PatternSyntaxException e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public static Map<String, Double> getGroupedByWeight(List<Delivery> deliveries) {
		return deliveries.stream().collect(Collectors.groupingBy(Delivery::getPostal, Collectors.summingDouble(Delivery::getWeight)));

	}



}
