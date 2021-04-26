package com.bsc.packagedelivery.utils;

import com.bsc.packagedelivery.beans.Delivery;
import org.jline.terminal.Terminal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ShellHelper {
	private final Terminal terminal;

	public ShellHelper(Terminal terminal) {
		this.terminal = terminal;
	}

	public void print(String message) {
		terminal.writer().println(message);
		terminal.flush();
	}

	public String formatDeliveries(List<Delivery> deliveries) {
		String line = "***".repeat(15);
		StringBuilder sb = new StringBuilder();

		if (deliveries.isEmpty()) {
			sb.append(line)
					.append("\nYou have an empty delivery storage.\n")
					.append(line);
		} else {
			Map<String, Double> grouped = DeliveryUtils.getGroupedByWeight(deliveries);

			sb.append(line);
			sb.append("\nPOSTAL CODE\t\tWEIGHT SUM\n");
			grouped.forEach((postal, weightSum) -> {
				sb.append(postal + "\t\t\t").append(String.format(java.util.Locale.US,"%.3f", weightSum.floatValue())).append("\n");
			});
			sb.append(line);

		}
		return sb.toString();
	}

}
