package br.com.luizmariodev.ems.temperature.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EmsTemperatureMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsTemperatureMonitoringApplication.class, args);
	}

}
