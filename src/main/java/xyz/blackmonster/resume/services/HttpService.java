package xyz.blackmonster.resume.services;

import java.util.Optional;

public interface HttpService {

	Optional<Object> makeRequest(String method, String destination, Object payload, Class className);
}
