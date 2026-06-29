package org.zeroapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.zeroapi.dto.PostResponse;

@Service
@RequiredArgsConstructor
public class PostService {
    private final RestClient restClient;

    public PostResponse getPost(int id){

        return restClient.get().uri("https://jsonplaceholder.typicode.com/posts/{id}", id)
                .retrieve()
                .body(PostResponse.class);
    }
}
