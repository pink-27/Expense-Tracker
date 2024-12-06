package com.example.MiniSplitwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@SpringBootApplication
@ComponentScan(basePackages = "com.example.MiniSplitwise")
public class MiniSplitwiseApplication {
	private static final Logger logger = LogManager.getLogger(MiniSplitwiseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MiniSplitwiseApplication.class, args);
		logger.info("Running Splitwise");
	}

}
