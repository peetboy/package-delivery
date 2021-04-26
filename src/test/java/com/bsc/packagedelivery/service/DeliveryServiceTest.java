package com.bsc.packagedelivery.service;

import com.bsc.packagedelivery.beans.Delivery;
import com.bsc.packagedelivery.repository.DeliveryRepository;
import com.bsc.packagedelivery.repository.DeliveryRepositoryImpl;
import com.bsc.packagedelivery.utils.DeliveryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class DeliveryServiceTest {

	DeliveryService service;

	@BeforeEach
	void setUp() {
		DeliveryRepository repository = new DeliveryRepositoryImpl();
		service = new DeliveryService(repository);
	}

	@Test
	void testEmptyRepo() {
		assertThat(service.findAll()).isEmpty();
	}

	@Test
	void testSaveDelivery() {
		Delivery d = new Delivery();
		d.setWeight(3.5F);
		d.setPostal("12345");
		service.save(d);

		assertThat(service.findAll().size()).isEqualTo(1);
	}

	@Test
	void testSaveWeightAndPostal() {
		Delivery d = new Delivery();
		d.setWeight(3.5f);
		d.setPostal("12345");
		service.save(d);

		assertThat(service.findAll().get(0).getPostal()).isEqualTo("12345");
		assertThat(service.findAll().get(0).getWeight()).isEqualTo(3.5f);
	}

	@Test
	void testSaveMultipleDeliveries() {
		Delivery d1 = new Delivery(1, "11111");
		Delivery d2 = new Delivery(2, "22222");
		Delivery d3 = new Delivery(3, "33333");
		service.saveAll(List.of(d1,d2,d3));

		assertThat(service.findAll().size()).isEqualTo(3);

		Map<String, Double> groupedByWeight = DeliveryUtils.getGroupedByWeight(service.findAll());
		assertThat(groupedByWeight.size()).isEqualTo(3);
	}

	@Test
	void testWeightOfMultipleDeliveries() {
		Delivery d1 = new Delivery(1.6f, "11111");
		Delivery d2 = new Delivery(2.4f, "11111");
		Delivery d3 = new Delivery(1.4f, "22222");
		Delivery d4 = new Delivery(2.6f, "22222");
		service.saveAll(List.of(d1,d2,d3,d4));

		Map<String, Double> groupedByWeight = DeliveryUtils.getGroupedByWeight(service.findAll());

		groupedByWeight.entrySet().forEach(stringDoubleEntry -> {
			assertThat(stringDoubleEntry.getValue().floatValue()).isEqualTo(4f);
		});

	}
}