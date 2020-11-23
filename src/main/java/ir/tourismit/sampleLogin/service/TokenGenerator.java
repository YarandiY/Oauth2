package ir.tourismit.sampleLogin.service;



import ir.tourismit.sampleLogin.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;


public class TokenGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static TokenGenerator instance =new TokenGenerator();
    private static HashMap<String , UserDetails> userTokenMap;
    private static final int TOKEN_LENGTH = 6;

    private TokenGenerator() {
        userTokenMap = new HashMap<>();
    }

    public static TokenGenerator getInstance() {
        return instance;
    }

    public String getToken(UserDetails user){
        String token =generateNewToken();
        userTokenMap.put(token,user );
        return token;
    }
    public String generateNewToken() {
        byte[] randomBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
    public UserDetails getUser(String token){
        return userTokenMap.get(token);
    }
    public void deleteToken(String token){
        userTokenMap.remove(token);
    }
}
