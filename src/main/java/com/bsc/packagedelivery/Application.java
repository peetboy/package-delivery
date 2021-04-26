package com.bsc.packagedelivery;

import com.bsc.packagedelivery.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private final DeliveryService service;

	@Autowired
	public Application(DeliveryService service) {
		this.service = service;
	}

	public static void main(String... args) {
		log.info("Starting application...");

//		String[] disabledCommands = {"--spring.shell.command.quit.enabled=false"};
//		String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);

		SpringApplication.run(Application.class, args).close();
	}



	@Bean
	CommandLineRunner runner(){
		return args -> {
			System.out.println("CommandLineRunner running in the main class...");
		};
	}
}
