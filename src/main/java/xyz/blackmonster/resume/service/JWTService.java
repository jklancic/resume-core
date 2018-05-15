package xyz.blackmonster.resume.service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

public interface JWTService {

	String createToken(String userUuid, String username, String role) throws JWTCreationException;

	String retrievePayloadFromToken(String token) throws JWTVerificationException;
}
