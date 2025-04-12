package com.fawry.movietask.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String value;

    @ManyToOne
    @JoinColumn(name = "movie_id") // FK column in rating table
    private MoiveEntity movie;
}
