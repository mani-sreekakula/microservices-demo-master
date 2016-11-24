package io.pivotal.microservices.accounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.pivotal.microservices.productcatalog.Product;
import io.pivotal.microservices.productcatalog.ProductRepository;
import io.pivotal.microservices.productcatalog.ProductsController;

public class ProductsControllerTests {

	@Autowired
	ProductsController productsController;

	protected static final String PRODUCT_NAME_1 = "GlobalMart rice";
	protected static final String PRODUCT_TYPE_1 = "GlobalMart food";
	protected static final BigDecimal PRODUCT_PRICE_1 = new BigDecimal(185);
	protected static final Product theProduct = new Product(PRODUCT_NAME_1, PRODUCT_TYPE_1, PRODUCT_PRICE_1);

	protected static class TestProductRepository implements ProductRepository {

		@SuppressWarnings("unchecked")
		@Override
		public <S extends Product> S save(S entity) {
			Product entityProduct = (Product) entity;
			if (theProduct.equals(entityProduct)) {
				return (S) theProduct;
			} else {
				return null;
			}
		}

		@Override
		public <S extends Product> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Product findOne(Long id) {
			if (id.equals(theProduct.getId()))
				return theProduct;
			else
				return null;
		}

		@Override
		public boolean exists(Long id) {
			if (id.equals(theProduct.getId()))
				return true;
			else
				return false;
		}

		@Override
		public Iterable<Product> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterable<Product> findAll(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void delete(Long id) {
			// TODO Auto-generated method stub
		}

		@Override
		public void delete(Product entity) {
			// TODO Auto-generated method stub

		}

		@Override
		public void delete(Iterable<? extends Product> entities) {
			// TODO Auto-generated method stub

		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub

		}

		@Override
		public List<Product> findByType(String productType) {
			if (productType.equals(PRODUCT_TYPE_1)) {
				List<Product> list = new ArrayList<Product>();
				list.add(theProduct);
				return list;
			} else {
				return null;
			}
		}

	}

	protected TestProductRepository testRepo = new TestProductRepository();

	@Before
	public void setup() {
		productsController = new ProductsController(testRepo);
	}

	@Test
	public void testAddProduct() {
		Product product = productsController.addProduct(PRODUCT_NAME_1, PRODUCT_TYPE_1, PRODUCT_PRICE_1);

		Assert.assertNotNull(product);
		Assert.assertEquals(PRODUCT_NAME_1, product.getName());
		Assert.assertEquals(PRODUCT_TYPE_1, product.getType());

	}

	@Test
	public void testFindByType() {
		List<Product> products = productsController.findByType(PRODUCT_TYPE_1);

		Assert.assertNotNull(products);
		Assert.assertEquals(1, products.size());

		Product product = products.get(0);
		Assert.assertEquals(PRODUCT_NAME_1, product.getName());
		Assert.assertEquals(PRODUCT_TYPE_1, product.getType());

	}

	@Test
	public void testDeleteProduct() {
		Boolean deleteStatus = productsController.deleteProduct(theProduct.getId());

		Assert.assertTrue(deleteStatus);
	}

	@Test
	public void testPriceOfProduct() {
		BigDecimal priceOfProduct = productsController.findPriceOfProduct(theProduct.getId());

		Assert.assertNotNull(priceOfProduct);
		Assert.assertEquals(PRODUCT_PRICE_1, priceOfProduct);

	}
}
