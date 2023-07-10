package com.example.itau.repositories;

import com.example.itau.dtos.PostsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "posts", url = "https://jsonplaceholder.typicode.com/")
public interface PostsRepository {

    @GetMapping(value = "/posts")
    List<PostsDTO> getAllPosts();
}

