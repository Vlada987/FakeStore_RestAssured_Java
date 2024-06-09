package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.Routes;
import endpoints.Methods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Load;
import pojo.Load2;
import pojo.Load3;

public class TestsClass {

	Load body = new Load();
	Faker f = new Faker();

	@BeforeTest
	public void CreateBody() {

		body.setEmail(f.internet().emailAddress());
		body.setUsername(f.name().username());
		body.setPassword(f.internet().password());
		LinkedHashMap<String, String> forName = new LinkedHashMap<String, String>();
		forName.put("firstname", f.name().firstName());
		forName.put("lastname", f.name().lastName());
		body.setName(forName);
		LinkedHashMap<String, Object> forAdress = new LinkedHashMap<String, Object>();
		forAdress.put("city", f.address().city());
		forAdress.put("street", f.address().streetName());
		forAdress.put("number", 45);
		forAdress.put("zipcode", f.number().digits(6));
		HashMap<String, String> forGeo = new HashMap<String, String>();
		forGeo.put("lat", f.number().digits(5));
		forGeo.put("long", f.number().digits(5));
		forAdress.put("geolocation", forGeo);
		body.setAdress(forAdress);
		body.setPhone(f.phoneNumber().cellPhone());
	}

	@Test
	public void Test01_AddUser() {

		Response res = Methods.AddNewUser(body);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test
	public void Test02_Login() {

		String uName = "jimmie_k";
		String pwd = "klein*#%*";
		Load2 body2 = new Load2();
		body2.setUsername(uName);
		body2.setPassword(pwd);
		Response res = Methods.Login(body2);
		int statusCode = res.getStatusCode();
		String token = res.jsonPath().get("token");

		Assert.assertEquals(statusCode, 200);
		Assert.assertTrue(token.length() > 25);

	}

	@Test
	public void Test03_GetAllUsers() {

		Response res = Methods.getAllData(Routes.allUsers);
		res.then().log().all();
		int sizeOfJsonArray = Methods.getSizeOfArray(res, "id");
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(sizeOfJsonArray, 10);

	}

	@Test
	public void Test04_GetUserByID() {

		Response res = Methods.getUserByID(1);
		int statusCode = res.getStatusCode();
		String city = res.jsonPath().get("address.city").toString();
		String firstName = res.jsonPath().get("name.firstname").toString();

		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(city, "kilcoole");
		Assert.assertEquals(firstName, "john");
	}

	@Test
	public void Test05_GetLimitedUsersList() {

		int limit = 7;
		Response res = Methods.GetUsers_limit(limit);
		int sizeOfJsonArray = Methods.getSizeOfArray(res, "id");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(limit, sizeOfJsonArray);

	}

//######################################################
	@Test
	public void Test06_getAllCarts() {

		Response res = Methods.getAllData(Routes.getCarts);
		int statusCode = res.getStatusCode();

		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void Test07_CreateCart() {

		Date d = new Date();
		Load3 body = new Load3();
		body.setUserId(20);
		body.setDate(d.toLocaleString());
		List<LinkedHashMap> products = new ArrayList<>();
		LinkedHashMap<String, Integer> cart1 = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> cart2 = new LinkedHashMap<String, Integer>();
		cart1.put("productId", 5);
		cart1.put("quantity", 1);
		cart2.put("productId", 4);
		cart2.put("quantity", 2);
		products.add(cart1);
		products.add(cart2);
		body.setProducts(products);
		Response res = Methods.createCart(body);

		Assert.assertTrue(res.statusCode() == 200);

	}

}
