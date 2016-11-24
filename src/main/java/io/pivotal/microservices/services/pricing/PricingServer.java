package io.pivotal.microservices.services.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Pricing server. Works as a microservice, fetching pricing from the
 * product-catalog-service. Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author manikanta.s
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class PricingServer {

	/**
	 * URL uses the logical name of product-catalog-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String PRODUCTS_SERVICE_URL = "http://PRODUCT-CATALOG-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for pricing-server.properties or pricing-server.yml
		System.setProperty("spring.config.name", "pricing-server");
		SpringApplication.run(PricingServer.class, args);
	}

	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton" 
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The ProductService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public PricingService productService() {
		return new PricingService(PRODUCTS_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link PricingService} to use.
	 * 
	 * @return
	 */
	@Bean
	public PricingController pricingController() {
		return new PricingController(productService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
}
