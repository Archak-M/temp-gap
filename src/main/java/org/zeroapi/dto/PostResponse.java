package org.zeroapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
