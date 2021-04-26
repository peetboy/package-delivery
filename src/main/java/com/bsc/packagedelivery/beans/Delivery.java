package com.bsc.packagedelivery.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

	private float weight;
	private String postal;

	@Override
	public String toString() {
		return String.format("Package weight [%s], postal code [%s]", weight, postal);
	}
}
