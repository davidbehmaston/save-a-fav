package com.movie.SaveAFav.services.interfaces;

import com.movie.SaveAFav.domains.Movie;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);

    Movie getMovieById(Integer id);

    List<Movie> findAllMovies();

    void deleteMovie(Integer id);

    Movie getMovieByTitle(String title);

    Movie getMovieByYear(int year);

    Movie getMovieByDirector(String director);


}
