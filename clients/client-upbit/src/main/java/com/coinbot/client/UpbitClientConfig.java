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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@EnableFeignClients
@Configuration
@RequiredArgsConstructor
class UpbitClientConfig {

    private final String ENV_UPBIT_ACCESS_KEY = "upbit.access.key";
    private final String ENV_UPBIT_SECRET_KEY = "upbit.secret.key";
    private final Environment env;
    private final ObjectMapper objectMapper;

    @Bean
    public UpbitTokenProvider tokenProvider() {
        String accessKey = env.getProperty(ENV_UPBIT_ACCESS_KEY);
        String secretKey = env.getProperty(ENV_UPBIT_SECRET_KEY);
        return new UpbitTokenProvider(accessKey, secretKey);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (existBody(requestTemplate)) {

            }
            Map<String, Object> params = getParamMap(requestTemplate);
            requestTemplate
                    .header(AUTHORIZATION, "Bearer " + tokenProvider().getToken(params))
                    .header(CONTENT_TYPE, "application/json");
        };
    }

    private boolean existBody(RequestTemplate requestTemplate) {
        return Objects.nonNull(requestTemplate.body());
    }

    private Map<String, Object> getParamMap(RequestTemplate requestTemplate) {
        byte[] body = requestTemplate.body();
        if (body != null) {
            String jsonBody = new String(body, StandardCharsets.UTF_8);
            return jsonStringToMap(jsonBody);
        }
        return new ConcurrentHashMap<>();
    }

    /**
     * String -> Map 변환
     */
    private Map<String, Object> jsonStringToMap(String json) {
        if (nonNull(json) && !json.trim().isEmpty()) {
            try {
                String substring = json.substring(1, json.length() - 1);
                String[] split = substring.split(",");
                StringBuilder sb = new StringBuilder("{");
                for (String property : split) {
                    if (!property.contains(":null")) {
                        sb.append(property).append(",");
                    }
                }
                int lastIndex = sb.length() - 1;
                sb.deleteCharAt(lastIndex);
                sb.append("}");
                return objectMapper.readValue(sb.toString(), new TypeReference<ConcurrentHashMap<String, Object>>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return new ConcurrentHashMap<>();
    }

}
