package com.fawry.movietask.controller;

import com.fawry.movietask.service.MoveAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/movie")
public class MovieAdminController {
    @Autowired
    MoveAdminService moveAdminService;

    @PostMapping("/add")
    public Object addMovie(@RequestParam("i") String movieId , @RequestParam("apikey") String apikey) {
        return moveAdminService.addMovieToDatabase(movieId,apikey);
    }
}
