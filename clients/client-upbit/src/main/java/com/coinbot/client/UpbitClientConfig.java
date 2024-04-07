package com.coinbot.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@EnableFeignClients
@Configuration
@RequiredArgsConstructor
class UpbitClientConfig {

    private final Environment env;
    private final ObjectMapper objectMapper;

    @Bean
    public UpbitTokenProvider tokenProvider() {
        String accessKey = env.getProperty("upbit.access.key");
        String secretKey = env.getProperty("upbit.secret.key");
        return new UpbitTokenProvider(accessKey, secretKey);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Map<String, String> params = getParamMap(requestTemplate);
            requestTemplate
                    .header(AUTHORIZATION, "Bearer " + tokenProvider().getToken(params))
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
                return objectMapper.readValue(sb.toString(), new TypeReference<ConcurrentHashMap<String, String>>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return new ConcurrentHashMap<>();
    }

}
