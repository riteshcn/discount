package discount;

import java.util.HashSet;
import java.util.Set;

public final class Catagory {

	private String catagoryName;
	private Catagory parentCatagory;
	private Set<Catagory> subCatagories = new HashSet<Catagory>();
	private Integer discount;
	
	public Catagory(String catagoryName){
		
	}

	public Catagory() {}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public Set<Catagory> getSubCatagories() {
		return subCatagories;
	}

	public void setSubCatagories(Set<Catagory> subCatagories) {
		this.subCatagories = subCatagories;
	}

	public Catagory getParentCatagory() {
		return parentCatagory;
	}

	public void setParentCatagory(Catagory parentCatagory) {
		this.parentCatagory = parentCatagory;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	
	
}
