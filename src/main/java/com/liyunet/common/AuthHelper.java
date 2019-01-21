package com.liyunet.common;

import com.google.gson.JsonObject;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;
import org.apache.commons.lang3.StringUtils;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AuthHelper {

    private static final String AUDIENCE = "NotReallyImportant";

    private static final String ISSUER = "LiYuJiaZu";

    private static final String SIGNING_KEY = "$_123456LongAndHardToGuessValue.WithSpecialCharacters@^($%*$%";
    /**
     * Creates a json web token which is a digitally signed token that contains a payload (e.g. userId to identify 
     * the user). The signing key is secret. That ensures that the token is authentic and has not been modified.
     * Using a jwt eliminates the need to store authentication session information in a database.
     * @param userId
     * @param durationDays
     * @return
     */
    public static String createJsonWebToken(Integer userId,String reqFrom, TimeUnit timeUnit,Long duration)    {
        //signing algorithm
        HmacSHA256Signer signer;
        try {
            signer = new HmacSHA256Signer(ISSUER, reqFrom, SIGNING_KEY.getBytes());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        long time = System.currentTimeMillis();
        
        //Configure JSON token
        JsonToken token = new JsonToken(signer);
        token.setAudience(AUDIENCE);
        token.setIssuedAt(new org.joda.time.Instant(time));
        token.setExpiration(new org.joda.time.Instant(time + timeUnit.toMillis(duration)));
        
        
        //Configure request object, which provides information of the item
        JsonObject request = new JsonObject();
        request.addProperty("userId", userId);
        

        JsonObject payload = token.getPayloadAsJsonObject();
        payload.add("info", request);
        try {
            return token.serializeAndSign();
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifies a json web token's validity and extracts the user id and other information from it. 
     * @param token
     * @return
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public static TokenInfo verifyToken(String token)  
    {
        try {
            final Verifier hmacVerifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());

            VerifierProvider hmacLocator = new VerifierProvider() {
                @Override
                public List<Verifier> findVerifier(String id, String key){
                	ArrayList<Verifier> list = new ArrayList<Verifier>();
                    Collections.addAll(list, hmacVerifier);
                    return list;
                }
            };
            VerifierProviders locators = new VerifierProviders();
            locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);
            net.oauth.jsontoken.Checker checker = new net.oauth.jsontoken.Checker(){

                @Override
                public void check(JsonObject payload) throws SignatureException {
                    // don't throw - allow anything
                }

            };
            //Ignore Audience does not mean that the Signature is ignored
            JsonTokenParser parser = new JsonTokenParser(locators,
                    checker);
            JsonToken jt;
            try {
                jt = parser.verifyAndDeserialize(token);
            } catch (SignatureException e) {
                throw new RuntimeException(e);
            }
            JsonObject payload = jt.getPayloadAsJsonObject();
            TokenInfo t = new TokenInfo();
            String issuer = payload.getAsJsonPrimitive("iss").getAsString();
            String userIdString =  payload.getAsJsonObject("info").getAsJsonPrimitive("userId").getAsString();
            if (issuer.equals(ISSUER) && !StringUtils.isBlank(userIdString))
            {
                t.setUserId(Integer.parseInt(userIdString));
                t.setIssued(payload.getAsJsonPrimitive("iat").getAsLong() * 1000L);
                t.setExpires(payload.getAsJsonPrimitive("exp").getAsLong() * 1000L);
                return t;
            }
            else
            {
                return null;
            }
        } catch (InvalidKeyException e1) {
            throw new RuntimeException(e1);
        }
    }

    public static void main(String[] args) {
    	//过期的
		/*TokenInfo info = verifyToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJMaVl1SmlhWnUiLCJhdWQiOiJOb3RSZWFsbHlJbXBvcnRhbnQiLCJpYXQiOjE0NjI3Nzc4OTEsImV4cCI6MTQ2Mjc4MTQ5MSwiaW5mbyI6e319.Ol1Qv_MNAYP-UlvzTBO2IQl6n0_FMpyHG81ca371qOA");
		System.out.println(info);*/
		
    	
    	/*String token = createJsonWebToken(12, TimeUnit.HOURS, 72L,1L);
    	System.out.println(token);*/
    	
    	
    	
    	
    	
	}
}
