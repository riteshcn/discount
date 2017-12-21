package client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import discount.Brand;
import discount.Catagory;
import discount.Customer;
import discount.Inventory;
import discount.Store;

/**
 * this class is working as client client for Store class and responsible for
 * reading the file products.csv file and initializing the Store class. cutomers
 * are put seperate from store class and custoerms are used as input in Store
 * class
 * 
 * @author ritesh.nailwal
 *
 */
public class DiscountApp {
	private List<Customer> customers;
	private Store store;

	public static void main(String[] args) {
		DiscountApp discountApp = new DiscountApp("src/main/resources/products.csv", "src/main/resources/brands.csv");
		discountApp.loadCustomers("src/main/resources/customers.csv");
	}

	public DiscountApp(String fileName, String brandsfileName) {
		init(fileName, brandsfileName);
	}

	/**
	 * read the input csv file and initialize the brands, catagory
	 * 
	 * @param fileName
	 */

	private void init(String fileName, String brandFile) {
		store = new Store();
		Set<Brand> brands = initBrands(brandFile);
		Map<String, Catagory> catagories = initCatagories();
		Map<Integer, Inventory> inventoryAndCustomers = initInventory(fileName, catagories, brands);
		store.setInventories(inventoryAndCustomers);
		store.setBrands(brands);
		store.setCatagories(catagories);

	}

	public List<Customer> loadCustomers(String customerFile) {

		List<Customer> customers = new ArrayList<Customer>();
		Path path = Paths.get(customerFile);
		try {

			List<String> contents = Files.readAllLines(path);

			int customerSize = Integer.parseInt(contents.get(0));
			int i = 1;
			for (; i <= customerSize; i++) {
				String[] customerChoices = contents.get(i).split(",");
				if (customerChoices != null) {
					List<Inventory> inventories = Arrays.asList(customerChoices).stream()
							.map(inv -> store.getInventory(inv)).collect(Collectors.toList());

					customers.add(new Customer(inventories));
				}

			}

		} catch (IOException ex) {
			customers = null;
		}
		return customers;

	}

	public void calculatePrice() {
		for (Customer customer : customers) {
			double price = store.calculatePrice(customer);
			System.out.println(price);
		}
	}

	/**
	 * initializes the brands name
	 * 
	 * @return
	 */

	private Set<Brand> initBrands(String brandsFile) {
		Set<Brand> brands = null;
		Path path = Paths.get(brandsFile);
		try {

			List<String> contents = Files.readAllLines(path);
			brands = contents.stream().map(e -> {
				String[] brand = e.split(",");
				if (brand.length >= 2) {
					return new Brand(brand[0], Integer.parseInt(brand[1]));
				}
				return null;
			}).collect(Collectors.toSet());

		} catch (IOException ex) {
			brands = null;
		}
		return brands;
	}

	/**
	 * iitializes the catagories of tree structure
	 * 
	 * @return
	 */

	private Map<String, Catagory> initCatagories() {
		Map<String, Catagory> catagories = new HashMap<String, Catagory>();

		Catagory menRootCatagory = new Catagory();
		menRootCatagory.setCatagoryName("Men");
		menRootCatagory.setParentCatagory(null);
		menRootCatagory.setDiscount(-1);
		catagories.put(menRootCatagory.getCatagoryName(), menRootCatagory);

		Catagory menShirtCatagory = new Catagory();
		menShirtCatagory.setCatagoryName("Shirts");
		menShirtCatagory.setDiscount(-1);
		menShirtCatagory.setParentCatagory(menRootCatagory);
		menRootCatagory.getSubCatagories().add(menShirtCatagory);
		catagories.put(menShirtCatagory.getCatagoryName(), menShirtCatagory);

		Catagory menTrousersCatagory = new Catagory();
		menTrousersCatagory.setCatagoryName("Trousers");
		menTrousersCatagory.setDiscount(-1);
		menTrousersCatagory.setParentCatagory(menRootCatagory);
		menRootCatagory.getSubCatagories().add(menTrousersCatagory);
		catagories.put(menTrousersCatagory.getCatagoryName(), menTrousersCatagory);

		Catagory menCasualsCatagory = new Catagory();
		menCasualsCatagory.setCatagoryName("Casuals");
		menCasualsCatagory.setDiscount(30);
		menCasualsCatagory.setParentCatagory(menRootCatagory);
		menRootCatagory.getSubCatagories().add(menCasualsCatagory);
		catagories.put(menCasualsCatagory.getCatagoryName(), menCasualsCatagory);

		Catagory menJeansCatagory = new Catagory();
		menJeansCatagory.setCatagoryName("Jeans");
		menJeansCatagory.setDiscount(20);
		menJeansCatagory.setParentCatagory(menRootCatagory);
		menRootCatagory.getSubCatagories().add(menJeansCatagory);
		catagories.put(menJeansCatagory.getCatagoryName(), menJeansCatagory);
		/**
		 * women
		 */

		Catagory womenRootCatagory = new Catagory();
		womenRootCatagory.setCatagoryName("Women");
		womenRootCatagory.setParentCatagory(null);
		womenRootCatagory.setDiscount(50);
		catagories.put(womenRootCatagory.getCatagoryName(), womenRootCatagory);

		Catagory womenDress = new Catagory();
		womenDress.setCatagoryName("Dresses");
		womenDress.setDiscount(-1);
		womenDress.setParentCatagory(womenRootCatagory);
		womenRootCatagory.getSubCatagories().add(womenDress);
		catagories.put(womenDress.getCatagoryName(), womenDress);

		Catagory womenFootwear = new Catagory();
		womenFootwear.setCatagoryName("Footwear");
		womenFootwear.setDiscount(-1);
		womenFootwear.setParentCatagory(womenRootCatagory);
		womenRootCatagory.getSubCatagories().add(womenFootwear);
		catagories.put(womenFootwear.getCatagoryName(), womenFootwear);

		return catagories;
	}

	/**
	 * read the file and initialize the inventory and customer choices
	 * 
	 * @param fileName
	 * @param catagories
	 * @param brands
	 * @return
	 */

	private Map<Integer, Inventory> initInventory(String fileName, Map<String, Catagory> catagories,
			Set<Brand> brands) {

		Map<Integer, Inventory> loadedinventories = new HashMap<Integer, Inventory>();

		Path path = Paths.get(fileName);
		try {

			List<String> contents = Files.readAllLines(path);

			int inventorySize = Integer.parseInt(contents.get(0));
			int i = 1;
			for (; i <= inventorySize; i++) {
				String[] inventoryLine = contents.get(i).split(",");
				if (inventoryLine != null && inventoryLine.length == 4) {

					int id = Integer.parseInt(inventoryLine[0].trim());
					String brandName = inventoryLine[1].trim();

					Optional<Brand> matchingObject = brands.stream().filter(p -> p.getBrandName().equals(brandName))
							.findFirst();
					Brand brand = matchingObject.orElse(null);

					String catagoryName = inventoryLine[2];
					Catagory catagory = catagories.get(catagoryName);

					int price = Integer.parseInt(inventoryLine[3]);

					Inventory inventory = new Inventory(id, brand, catagory, price);

					loadedinventories.put(inventory.getId(), inventory);

				}

			}

		} catch (IOException ex) {
			return null;
		}

		return loadedinventories;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
