package xyz.blackmonster.resume.security.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Password utility to generate hashed password and verify them.
 */
public class PasswordUtil {
	
	private static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 128;
	private static final int SALT_SIZE = 16;

	private static final Random random = new SecureRandom();

	/**
	 * Create hash for password + salt.
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	public static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
			return skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}

	/**
	 * Generates the hash with the password and the salt.
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	private static String generateSecurePassword(String password, byte[] salt) {
		String returnValue = null;
		
		byte[] securePassword = hash(password.toCharArray(), salt);
		byte[] securePasswordWithSalt = new byte[SALT_SIZE + securePassword.length];
		System.arraycopy(salt, 0, securePasswordWithSalt, 0, SALT_SIZE);
		System.arraycopy(securePassword, 0, securePasswordWithSalt, SALT_SIZE, securePassword.length);

		returnValue = Base64.getEncoder().encodeToString(securePasswordWithSalt);

		return returnValue;
	}

	/**
	 * Creates the salt and then uses the same salt to create the hash with the password and salt.
	 * 
	 * @param password
	 * @return
	 */
	public static String generateSecurePassword(String password) {
		String returnValue = null;

		byte[] salt = new byte[SALT_SIZE];
		random.nextBytes(salt);

		return generateSecurePassword(password, salt);
	}

	/**
	 * Verifies the given password by recreating the same hash value with the same salt value.
	 * 
	 * @param providedPassword
	 * @param securedPassword
	 * @return
	 */
	public static boolean verifyUserPassword(String providedPassword, String securedPassword) {
		boolean returnValue = false;

		// Generate New secure password with the same salt
		byte[] securePasswordWithSalt = Base64.getDecoder().decode(securedPassword);
		byte[] salt = new byte[SALT_SIZE];
		System.arraycopy(securePasswordWithSalt, 0, salt, 0, SALT_SIZE);
		String newSecurePassword = generateSecurePassword(providedPassword, salt);

		// Check if two passwords are equal
		returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

		return returnValue;
	}
}
