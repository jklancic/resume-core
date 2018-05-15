package xyz.blackmonster.resume.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import xyz.blackmonster.resume.config.app.RuntimeConfiguration;

public class JWTServiceImpl implements JWTService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTServiceImpl.class);

	private static final String ISSUER = "blackmonster";

	@Inject
	public JWTServiceImpl() {
	}

	@Override
	public String createToken(String userUuid, String username, String role) throws JWTCreationException {
		try {
			long expirationDate = System.currentTimeMillis() + (1000 * 60 * 30);
			Algorithm algorithm = Algorithm.HMAC256(RuntimeConfiguration.get().getJwtSecret());
			return JWT.create()
				.withIssuer(ISSUER)
				.withExpiresAt(new Date(expirationDate))
				.withSubject(userUuid)
				.withClaim("username", username)
				.withClaim("scope", role)
				.sign(algorithm);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UTF-8 encoding not supported: ", e);
			throw new JWTCreationException("UTF-8 encoding not supported", e);
		} catch (JWTCreationException e) {
			LOGGER.error("Invalid signing configuration or claims could not be converted: ", e);
			throw e;
		}
	}

	@Override
	public String retrievePayloadFromToken(String token) throws JWTVerificationException {
		try {
			Algorithm algorithm = Algorithm.HMAC256(RuntimeConfiguration.get().getJwtSecret());
			JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer(ISSUER)
				.build(); //Reusable verifier instance
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getPayload();
		} catch (UnsupportedEncodingException e){
			LOGGER.error("UTF-8 encoding not supported: ", e);
			throw new JWTVerificationException("UTF-8 encoding not supported.", e);
		} catch (JWTVerificationException e){
			LOGGER.error("Invalid signing configuration or claims could not be converted: ", e);
			throw new JWTVerificationException("Invalid signing configuration or claims could not be converted.", e);
		}
	}
}
