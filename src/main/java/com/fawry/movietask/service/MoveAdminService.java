package com.fawry.movietask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fawry.movietask.dto.MovieDto;
import com.fawry.movietask.entity.MoiveEntity;
import com.fawry.movietask.integration.OmdbClient;
import com.fawry.movietask.repository.MoiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MoveAdminService {
    @Autowired
    private OmdbClient omdbClient;
    @Autowired
    private ObjectMapper objectMapper ;
    @Autowired
    private MoiveRepository moiveRepository;
    public ResponseEntity<?> addMovieToDatabase(String MovieId,String TokenId){
       MovieDto movieDto = omdbClient.getPostById(MovieId,TokenId);
       MoiveEntity moiveEntity = objectMapper.convertValue(movieDto, MoiveEntity.class);
       return ResponseEntity.ok().body(moiveRepository.save(moiveEntity));
  }
}
