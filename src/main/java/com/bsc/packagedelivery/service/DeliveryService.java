package com.bsc.packagedelivery.service;

import com.bsc.packagedelivery.beans.Delivery;
import com.bsc.packagedelivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
	private final DeliveryRepository repository;

	@Autowired
	public DeliveryService(DeliveryRepository repository) {
		this.repository = repository;
	}

	public List<Delivery> findAll() {
		return repository.findAll();
	}

	public void saveAll(List<Delivery> deliveries) {
		repository.saveAll(deliveries);
	}

	public void save(Delivery delivery) {
		repository.save(delivery);
	}

}
