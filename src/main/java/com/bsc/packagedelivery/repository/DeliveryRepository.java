package com.bsc.packagedelivery.repository;

import com.bsc.packagedelivery.beans.Delivery;

import java.util.List;

public interface DeliveryRepository {
	void save(Delivery s);

	List<Delivery> findAll();

	void saveAll(List<Delivery> deliveries);
}
