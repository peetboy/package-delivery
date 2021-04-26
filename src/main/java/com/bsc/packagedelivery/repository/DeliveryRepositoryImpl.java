package com.bsc.packagedelivery.repository;

import com.bsc.packagedelivery.beans.Delivery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {
	private final List<Delivery> deliveries = new ArrayList<>();

	@Override
	public void save(Delivery s) {
		this.deliveries.add(s);
	}

	@Override
	public List<Delivery> findAll() {
		return this.deliveries;
	}

	@Override
	public void saveAll(List<Delivery> deliveries) {
		this.deliveries.addAll(deliveries);
	}
}
