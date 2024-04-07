package com.coinbot.client.upbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;


public class UpbitFeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor(UpbitTokenProvider provider) {
        return requestTemplate -> {
            Map<String, String> params = getParamMap(requestTemplate);
            requestTemplate
                    .header(AUTHORIZATION, "Bearer " + provider.getToken(params))
                    .header(CONTENT_TYPE, "application/json");
        };
    }

    private Map<String, String> getParamMap(RequestTemplate requestTemplate) {
        byte[] body = requestTemplate.body();
        if (body != null) {
            String jsonBody = new String(body, StandardCharsets.UTF_8);
            return jsonStringToMap(jsonBody);
        }

        String url = requestTemplate.url();
        if (url.contains("?")) {
            String queryString = url.substring(url.indexOf("?") + 1);
            String[] split = queryString.split("&");
            Map<String, String> map = new ConcurrentHashMap<>();
            for (String str : split) {
                int idx = str.indexOf("=");
                String key = str.substring(0, idx);
                String value = str.substring(idx + 1);
                map.put(key, value);
            }
            return map;
        }

        return new ConcurrentHashMap<>();
    }

    /**
     * String -> Map 변환
     */
    private Map<String, String> jsonStringToMap(String json) {
        if (nonNull(json) && !json.trim().isEmpty()) {
            try {
                String substring = json.substring(1, json.length() - 1);
                String[] split = substring.split(",");
                StringBuilder sb = new StringBuilder("{");
                for (String property : split) {
                    if (!property.contains("null")) {
                        sb.append(property).append(",");
                    }
                }
                int lastIndex = sb.length() - 1;
                sb.deleteCharAt(lastIndex);
                sb.append("}");
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(sb.toString(), new TypeReference<ConcurrentHashMap<String, String>>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return new ConcurrentHashMap<>();
    }
}
