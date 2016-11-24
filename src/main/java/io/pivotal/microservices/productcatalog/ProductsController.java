package io.pivotal.microservices.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.microservices.exceptions.ProductAlreadyExistsException;
import io.pivotal.microservices.exceptions.ProductNotFoundException;

/**
 * A RESTFul controller for accessing product information.
 * 
 * @author manikanta.s
 */
@RestController
public class ProductsController {

	protected Logger logger = Logger.getLogger(ProductsController.class.getName());
	protected ProductRepository productRepository;

	/**
	 * Create an instance plugging in the respository of Products.
	 * 
	 * @param productRepository
	 *            A Product repository implementation.
	 */
	@Autowired
	public ProductsController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Adds a product with the specified product name, type and price.
	 * 
	 * @param productName
	 * @param productType
	 * @param price
	 * @return The status
	 * @throws ProductAlreadyExistsException
	 *             If the productName is recognised.
	 */
	@RequestMapping(value = "/products/add", produces = "application/json")
	@ResponseBody
	public Product addProduct(@QueryParam("productName") String productName,
			@QueryParam("productType") String productType, @QueryParam("price") BigDecimal price) {
		logger.info("product-catalog-service addProduct() invoked: ");
		Product product = new Product(productName, productType, price);
		product = productRepository.save(product);
		logger.info("product-catalog-service addProduct() created: " + product);

		if (product == null)
			throw new ProductNotFoundException(productType);
		else {
			return product;
		}
	}

	/**
	 * Fetch a list of products with the specified product type.
	 * 
	 * @param productType
	 * @return The account if found.
	 * @throws ProductNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping(value = "/products/type/{productType}", produces = "application/json")
	@ResponseBody
	public List<Product> findByType(@PathVariable("productType") String productType) {

		logger.info("product-catalog-service findByType() invoked: " + productType);
		List<Product> products = productRepository.findByType(productType);
		logger.info("product-catalog-service findByType() found: " + products);

		if (products == null || products.size() == 0)
			throw new ProductNotFoundException(productType);
		else {
			return products;
		}
	}

	/**
	 * Delete a product from catalog with given product id
	 * 
	 * @param productId
	 * @return The status.
	 * @throws ProductNotFoundException
	 *             If the id is not recognised.
	 */
	@RequestMapping(value = "/products/delete/{productId}", produces = "application/json")
	@ResponseBody
	public boolean deleteProduct(@PathVariable("productId") Long productId) {

		logger.info("product-catalog-service deleteProduct() invoked: " + productId);
		boolean status = productRepository.exists(productId);
		logger.info("product-catalog-service deleteProduct() found: " + status);

		if (status) {
			productRepository.delete(productId);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Fetch the price of specified product(Used by pricing-service)
	 * 
	 * @param productId
	 * @return The price of product
	 * @throws ProductNotFoundException
	 *             If the id is not recognised.
	 */
	@RequestMapping(value ="/products/pricing/{productId}", produces = "application/json")
	@ResponseBody
	public BigDecimal findPriceOfProduct(@PathVariable("productId") Long productId) {

		logger.info("product-catalog-service findPriceOfProduct() invoked: " + productId);
		Product product = productRepository.findOne(productId);
		logger.info("product-catalog-service findPriceOfProduct() found: " + product);

		if (product == null)
			throw new ProductNotFoundException(productId.toString());
		else {
			return product.getPrice();
		}
	}
}
