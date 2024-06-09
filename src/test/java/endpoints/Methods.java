package endpoints;

import static io.restassured.RestAssured.*;

import api.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Load;
import pojo.Load2;
import pojo.Load3;

public class Methods {

	public static Response AddNewUser(Load load) {

		Response res = given().contentType("application/json").accept(ContentType.JSON).body(load).post(Routes.addUser);
		return res;
	}

	public static Response Login(Load2 load) {

		Response res = given().contentType("application/json").accept(ContentType.JSON).body(load).post(Routes.login);
		return res;
	}

	public static Response getAllData(String url) {

		Response res = given().get(url);
		return res;
	}

	public static Response getUserByID(int id) {

		Response res = given().pathParam("id", id).get(Routes.getUser);
		return res;
	}

	public static Response GetUsers_limit(int limit) {

		Response res = given().queryParam("limit", limit).get(Routes.allUsers);
		return res;
	}

	public static Response createCart(Load3 body) {

		Response res = given().contentType("application/json").accept(ContentType.JSON).body(body).post(Routes.addCart);
		return res;
	}

	public static int getSizeOfArray(Response res, String jsonObj) {

		int size = 0;
		while (true) {
			if (res.jsonPath().get(jsonObj + "[" + size + "]") == (null)) {
				break;
			}
			size++;

		}
		return size;
	}

}
