package com.fawry.movietask.repository;

import com.fawry.movietask.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoiveRepository extends JpaRepository<MovieEntity, Long> {
}
