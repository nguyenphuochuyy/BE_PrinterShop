package utils;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {
	 private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	// thời gian sống của token
	private static final long EXPIRE_TIME = 864000000; // 10 days

	// phương thức tạo token
	public static String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}


}
