package com.example.itau.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostsDTO {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

}
