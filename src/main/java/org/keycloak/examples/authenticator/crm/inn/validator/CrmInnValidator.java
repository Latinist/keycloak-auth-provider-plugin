package org.keycloak.examples.authenticator.crm.inn.validator;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CrmInnValidator {

	private static final String GET_URL = "http://localhost:9090/SpringMVCExample";

	public void token(/*@NotNull Event event, @NotNull HttpServletRequest request*/) throws Exception {

		HttpGet httpGet = new HttpGet(GET_URL);

		//httpGet.addHeader("User-Agent", USER_AGENT);
		//setAuthKeycloak(post);
		/*Collections.list(request.getHeaderNames()).stream()
				//.filter(h -> !h.toLowerCase().equals("content-length"))
				.filter(h -> h.toLowerCase().equals("content-type"))
				.forEach(h -> post.addHeader(h,request.getHeader(h)));
		post.setEntity(new StringEntity(event.getBody())); */

		CloseableHttpResponse httpResponse = request(httpGet, 10);

	}



	public CloseableHttpResponse request(@NotNull HttpRequestBase request, @NotNull long timeout/*, @NotNull Functions.RPEFun<HttpResponse,R> func*/) throws Exception {
		try (CloseableHttpClient client = HttpClients.createDefault();
		     CloseableHttpResponse response = CompletableFuture.supplyAsync(() -> {
			     try {
				     return client.execute(request);
			     }catch (IOException ex) {
				     throw new RuntimeException(ex);
			     }
		     }).get(timeout, TimeUnit.SECONDS)) {
			return response;
		}catch (ExecutionException ex) {
			throw (Exception)ex.getCause().getCause();
		}
	}




}
