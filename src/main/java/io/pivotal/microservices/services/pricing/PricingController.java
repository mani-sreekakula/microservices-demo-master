package io.pivotal.microservices.services.pricing;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Client controller, fetches Products info from the microservice via
 * {@link PricingService}.
 * 
 * @author manikanta.s
 */
@Controller
public class PricingController {

	@Autowired
	protected PricingService productService;

	protected Logger logger = Logger.getLogger(PricingController.class.getName());

	public PricingController(PricingService productService) {
		this.productService = productService;
	}

	@RequestMapping("/pricing")
	public String goHome() {
		return "index";
	}

	@RequestMapping(value = "/pricing/{productId}", produces = "application/json")
	@ResponseBody
	public BigDecimal findPriceOfProduct(@PathVariable("productId") Long productId) {

		logger.info("pricing-service findPriceOfProduct() invoked: " + productId);

		BigDecimal productPrice = productService.findPriceOfProduct(productId);
		logger.info("pricing-service findPriceOfProduct() found: " + productId);
		return productPrice;
	}
}
