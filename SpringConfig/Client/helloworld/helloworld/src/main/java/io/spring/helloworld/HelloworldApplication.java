package io.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
public class HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

}

//1.3. Create Spring Cloud Config Application
//@RestController
//class HelloWorldController{
//	
//	@Value("${message:default message}")
//	String message;
//	
//	@GetMapping("/hello")
//	public String helloworld() {
//		return message;
//	}
//}

//1.5.1. Change the message value
//@RestController
//@RefreshScope
//class HelloWorldController {
//
//	@Value("${message:default message}")
//	String message;
//
//	@GetMapping("/hello")
//	public String helloworld() {
//		return message;
//	}
//}


//1.5.2. Change the logging level
@RestController
@Slf4j
@RefreshScope
class HelloWorldController {
	@Value("${message:default message}")
	String message;
	@GetMapping("/hello")
	public String helloworld() {
		log.debug("DEBUG!!!!"); 
		return message;
	}
}