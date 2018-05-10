package xyz.blackmonster.resume.services;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import xyz.blackmonster.resume.ws.response.ChuckFactWS;

/**
 * Http service for calling other APIs.
 */
public class HttpServiceImpl implements HttpService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpServiceImpl.class);

	@Inject
	public HttpServiceImpl() {

	}

	@Override
	public Optional<Object> makeRequest(String method, String destination, Object payload, Class className) {
		HttpRequestFactory requestFactory
			= new NetHttpTransport().createRequestFactory();
		try {
			HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(destination));
			String response = request.execute().parseAsString();
			ObjectMapper mapper = new ObjectMapper();
			return Optional.of(mapper.readValue(response, ChuckFactWS.class));
		} catch (IOException e) {
			LOGGER.error("Could not make request:", e);
		}
		return Optional.empty();
	}
}
