package com.coinbot.client.upbit;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
class UpbitTokenProvider {

    private final String accessKey;
    private final String secretKey;

    public String getToken(Map<String, String> params) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .claims(getClaims(params))
                .signWith(key)
                .compact();
    }

    private Map<String, Object> getClaims(Map<String, String> params) {
        Map<String, Object> claims = new ConcurrentHashMap<>();
        claims.put("access_key", accessKey);
        claims.put("nonce", UUID.randomUUID().toString());

        if (!params.isEmpty()) {
            claims.put("query_hash", generateQueryHash(params));
            claims.put("query_hash_alg", "SHA512");
        }

        return claims;
    }

    private String generateQueryHash(Map<String, String> params) {
        try {
            ArrayList<String> queryElements = new ArrayList<>();
            for (String key : params.keySet()) {
                queryElements.add(key + "=" + params.get(key));
            }
            String queryString = String.join("&", queryElements.toArray(new String[0]));
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes(StandardCharsets.UTF_8));
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
