package com.movie.SaveAFav.repositories;

import com.movie.SaveAFav.domains.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public Movie findMovieByTitle(String title);
    public Movie findMovieByYear(int year);
    public Movie findMovieByDirector(String director);
}