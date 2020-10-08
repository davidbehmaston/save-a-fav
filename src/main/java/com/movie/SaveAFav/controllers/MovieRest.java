package com.movie.SaveAFav.controllers;

import com.movie.SaveAFav.domains.Movie;
import com.movie.SaveAFav.services.interfaces.MovieService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/movie")
public class MovieRest {

    private Logger log = Logger.getLogger(MovieRest.class.getName());

    private final RestTemplate restTemplate;


    private final MovieService movieService;

    public MovieRest(MovieService movieService, RestTemplate restTemplate) {
        this.movieService = movieService;
        this.restTemplate = restTemplate;
    }

    //GET BY ID
    @GetMapping ("/{id}")
    public Movie getMovieById(@PathVariable int id){
        return movieService.getMovieById(id);
    }

    //GET ALL
    @GetMapping("/")
    public List<Movie> getAll(){
        return movieService.findAllMovies();
    }

    //SAVE
    @PostMapping("/")
    public Movie save(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    //DELETE BY ID
    @DeleteMapping ("/{id}")
    public Boolean delete(@PathVariable int id){
        boolean result = false;
        try{
            movieService.deleteMovie(id);
            result = true;
        }catch (Exception ex){
            log.info((Supplier<String>) ex);
        }
        return result;
    }

    //SEARCH OMDB API
    @GetMapping("/search/{title}")
    public String searchMovieTitles(@PathVariable String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        if(title.contains("\\s"))
            title.replace("\\s", "+");

        String omdbUrl = "http://www.omdbapi.com/?s="+ title + "&plot=full&apikey=a0b88989";

        return restTemplate.exchange(omdbUrl, HttpMethod.GET, entity, String.class).getBody();
    }

    //GET SPECIFIC TITLE ON OMDB API
    @GetMapping("/find/{id}")
    public String getMovieDetails(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String omdbUrl = "http://www.omdbapi.com/?i="+ id + "&plot=full&apikey=a0b88989";

        return restTemplate.exchange(omdbUrl, HttpMethod.GET, entity, String.class).getBody();
    }

}