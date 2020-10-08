package com.movie.SaveAFav.services.impl;

import com.movie.SaveAFav.domains.Movie;
import com.movie.SaveAFav.repositories.MovieRepository;
import com.movie.SaveAFav.services.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> findAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieRepository.findAll().forEach(movieList::add);
        return movieList;
    }

    @Override
    public Movie getMovieByYear(int year) {
        return movieRepository.findMovieByYear(year);
    }

    @Override
    public Movie getMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    @Override
    public Movie getMovieByDirector(String director) {
        return movieRepository.findMovieByDirector(director);
    }

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}
