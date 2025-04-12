package com.fawry.movietask.integration;

import com.fawry.movietask.dto.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jsonPlaceholder", url = "https://www.omdbapi.com")
public interface  OmdbClient {
    @GetMapping("/")
    MovieDto getPostById(@RequestParam("i") String movieId, @RequestParam("apikey") String token);
}
