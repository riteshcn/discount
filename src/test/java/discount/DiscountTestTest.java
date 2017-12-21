package discount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import client.DiscountApp;

/**
 * Test files to be unit tested is store
 * @author ritesh.nailwal
 *
 */
public class DiscountTestTest {
	
	String inventoryFile="src/main/resources/products.csv";
	String brands="src/main/resources/brands.csv";
	String customersFile="src/main/resources/customers.csv";
	
	private List<Customer> customers;
	
	private DiscountApp app;
	private Store store;

	@Before
	public void setUp() throws Exception {
		app = new DiscountApp(inventoryFile, brands);
		customers = app.loadCustomers(customersFile);
		store = app.getStore();
	}

	@Test
	public void testCustomerChoices() {
		// first customer
		Customer first = customers.get(0);
		assertEquals(3860, store.calculatePrice(first), 1);

		Customer second = customers.get(1);
		assertEquals(2140, store.calculatePrice(second), 1);

	}
	
	@Test
	public void testInvalidCustomerChoices() {
		// first customer
		Customer first = customers.get(0);
		assertNotSame(3830, store.calculatePrice(first));

		Customer second = customers.get(1);
		assertNotSame(2170, store.calculatePrice(second));

	}

}
