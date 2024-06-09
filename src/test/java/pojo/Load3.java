package pojo;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class Load3 {

	int userId;
	String date;
	List<LinkedHashMap> products;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<LinkedHashMap> getProducts() {
		return products;
	}

	public void setProducts(List<LinkedHashMap> products) {
		this.products = products;
	}

}

