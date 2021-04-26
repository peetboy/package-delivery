package com.bsc.packagedelivery.shell;

import com.bsc.packagedelivery.beans.Delivery;
import com.bsc.packagedelivery.service.DeliveryService;
import com.bsc.packagedelivery.utils.DeliveryUtils;
import com.bsc.packagedelivery.utils.ShellHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.Size;
import java.util.List;

@ShellComponent
public class DeliveryCommands {
	private static final Logger log = LoggerFactory.getLogger(DeliveryCommands.class.getName());

	@Autowired
	private DeliveryService service;

	@Autowired
	private ShellHelper shellHelper;

	@ShellMethod(value = "Add new package. Command: \"add <weight> <postal code>\" (max 3 decimal number)", key = "add")
	public String addPackage(@Size(min = 1) String weight, @Size(min = 1, max = 5) String postal) {
		log.debug("Weight value [{}]", weight);
		if (!DeliveryUtils.isWeightValid(weight)) {
			throw new IllegalArgumentException("Invalid weight number provided.");
		}

		log.debug("Postal code value [{}]", postal);
		if (!DeliveryUtils.isPostalValid(postal)) {
			throw new IllegalArgumentException("Invalid postal number provided.");
		}
		Delivery delivery = new Delivery();
		delivery.setWeight(Float.parseFloat(weight));
		delivery.setPostal(postal);
		service.save(delivery);

		return "You have added new package: " + delivery.toString();
	}

	@ShellMethod(value = "List all packages.", key = "list")
	public String listPackages() {
		List<Delivery> deliveries = service.findAll();
		return shellHelper.formatDeliveries(deliveries);
	}

	@Scheduled(fixedDelay = 60000L)
	public void showDeliveries() {
		List<Delivery> deliveries = service.findAll();
		String output = shellHelper.formatDeliveries(deliveries);
		shellHelper.print(output);
		shellHelper.print("Press enter to return to shell...");
	}
}
