package io.pivotal.microservices.accounts;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.pivotal.microservices.productcatalog.ProductsConfiguration;

/**
 * Imitates the {@link ProductsServer}, but without using any of the discovery
 * client code. Allows the test to use the same configuration as the
 * <code>ProductsServer</code> would.
 * 
 * @author manikanta.s
 *
 */
@SpringBootApplication
@Import(ProductsConfiguration.class)
class ProductsMain {
	public static void main(String[] args) {
		// Tell server to look for product-catalog-server.properties or
		// product-catalog-server.yml
		System.setProperty("spring.config.name", "product-catalog-server");
		SpringApplication.run(ProductsMain.class, args);
	}
}

/**
 * Spring Integration/System test - by using @SpringApplicationConfiguration
 * instead of @ContextConfiguration, it picks up the same configuration that
 * Spring Boot would use.
 * 
 * @author manikanta.s
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductsMain.class)
public class ProductsControllerIntegrationTests extends ProductsControllerTests {

}
