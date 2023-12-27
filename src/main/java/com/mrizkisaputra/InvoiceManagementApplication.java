package com.mrizkisaputra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:/database.properties")
})
public class InvoiceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceManagementApplication.class, args);
	}

}
