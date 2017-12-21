package discount;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private List<Inventory> choices = new ArrayList<Inventory>();

	public Customer(List<Inventory> choices) {
		this.choices = choices;
	}

	public List<Inventory> getChoices() {
		return choices;
	}

	public void setChoices(List<Inventory> choices) {
		this.choices = choices;
	}

}
