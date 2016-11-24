package io.pivotal.microservices.productcatalog;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Product data implemented using Spring Data JPA.
 * 
 * @author manikanta.s
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
	/**
	 * Find a list of products with the specified product type.
	 *
	 * @param productType
	 * @return The product if found, null otherwise.
	 */
	public List<Product> findByType(String productType);
	
	/**
	 * Add a product with the specified product name and type.
	 *
	 * @param productName
	 * @param productType
	 * @param price
	 * @return The status.
	 */
//	public boolean addProduct(String productName,String productType,BigDecimal price);
	
}
