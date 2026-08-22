package com.example.streaming_service;

import com.example.streaming_service.client.StreamingClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(StreamingServiceApplication.class, args);
	}

}
