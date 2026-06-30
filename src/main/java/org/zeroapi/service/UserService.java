package org.zeroapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.zeroapi.dto.UserResponse;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final ObjectMapper objectMapper;

    @Cacheable(value = "users", key = "#id")
    @PreAuthorize("hasRole(ADMIN)")
    public UserResponse getUser(int id) throws Exception{

        StringBuilder uri = new StringBuilder("https://jsonplaceholder.typicode.com/users/");
        uri = uri.append(id);

        URI apiUri = new URI(uri.toString());

        HttpGet request = new HttpGet(apiUri);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request);
             InputStream inputStream = response.getEntity().getContent()){

            log.info("Request executed");
            return objectMapper.readValue(inputStream, UserResponse.class);

        }

    }

    public String getField(int id, String field) throws Exception {
        UserResponse user = getUser(id);

        log.info("fetched User succesfully. Details are \n {}", user);

        Field fieldName = UserResponse.class.getDeclaredField(field);
        fieldName.setAccessible(true);

        Object value = fieldName.get(user);

        return value != null ? value.toString() : null;

    }

}
