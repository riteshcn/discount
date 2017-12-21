package discount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Store {
	private Map<Integer, Inventory> inventories = new HashMap<Integer, Inventory>();
	private Set<Brand> brands = new HashSet<Brand>();
	private Map<String, Catagory> catagories = new HashMap<String, Catagory>();
	
	
	public Inventory getInventory(String id) {
		return inventories.get(Integer.parseInt(id));
	}

	public Map<Integer, Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Map<Integer, Inventory> inventories) {
		this.inventories = inventories;
	}

	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Map<String, Catagory> getCatagories() {
		return catagories;
	}

	public void setCatagories(Map<String, Catagory> catagories) {
		this.catagories = catagories;
	}

	public Double calculatePrice(Customer customer) {
		double totalPrice = 0;
		List<Inventory> products = customer.getChoices();
		for (Inventory product : products) {
			double price = product.getPrice();

			int discountPercentage = getDiscountPercent(product);

			price = price - (price * discountPercentage / 100);
			totalPrice = totalPrice + price;

		}

		return totalPrice;
	}

	private int getDiscountPercent(Inventory product) {
		Catagory catagory = product.getCatagory();
		int perCatagoryDiscount = 0;
		while (catagory != null) {
			if (catagory.getDiscount() != -1 && catagory.getDiscount() > perCatagoryDiscount) {
				perCatagoryDiscount = catagory.getDiscount();
			}
			catagory = catagory.getParentCatagory();
		} 

		return perCatagoryDiscount > product.getBrand().getDiscount() ? perCatagoryDiscount
				: product.getBrand().getDiscount();

	}
}
