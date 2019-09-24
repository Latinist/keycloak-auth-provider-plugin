package org.keycloak.examples.authenticator.crm.inn.validator;

import org.apache.http.client.methods.HttpPost;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Arrays;

public class HttpRequest extends HttpPost {
	private final String method;

	public HttpRequest(String method, String url) {
		super(url);
		this.method = method;
	}

	@Override
	public String getMethod() {
		return method;
	}

	public JsonObject toJson() {
		JsonObjectBuilder h = Json.createObjectBuilder();


		Arrays.asList(getAllHeaders()).forEach(hh -> h.add(hh.getName(), hh.getValue()));

		return Json.createObjectBuilder()
				.add("URL",getURI().toString())
				.add("METHOD",getMethod())
				.add("headers",h.build()).build();
	}
}