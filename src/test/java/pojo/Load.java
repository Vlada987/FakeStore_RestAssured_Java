package pojo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Load {

	String email;
	String username;
	String password;
	LinkedHashMap<String, String> name = new LinkedHashMap<String, String>();
	LinkedHashMap<String, Object> adress = new LinkedHashMap<String, Object>();
	String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LinkedHashMap<String, String> getName() {
		return name;
	}

	public void setName(LinkedHashMap<String, String> name) {
		this.name = name;
	}

	public LinkedHashMap<String, Object> getAdress() {
		return adress;
	}

	public void setAdress(LinkedHashMap<String, Object> adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
