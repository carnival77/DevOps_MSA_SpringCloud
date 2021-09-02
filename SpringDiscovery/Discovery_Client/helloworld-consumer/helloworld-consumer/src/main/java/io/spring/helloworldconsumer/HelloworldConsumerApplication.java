package io.spring.helloworldconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@SpringBootApplication
@EnableDiscoveryClient
public class HelloworldConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloworldConsumerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

@RestController
@RateLimiter(name = "helloServiceCircuitBreaker")
@CircuitBreaker(name = "helloServiceCircuitBreaker", fallbackMethod = "fallback")
class HelloworldClientController {
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/hello")
	public String getMessage() {
		return restTemplate.getForObject("http://HELLOWORLD/hello", String.class);
	}
	
	public String fallback(Exception ex) {
		return "fallback";
	}
}