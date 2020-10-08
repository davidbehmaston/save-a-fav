package com.movie.SaveAFav.service;



import com.movie.SaveAFav.domains.Movie;
import com.movie.SaveAFav.repositories.MovieRepository;
import com.movie.SaveAFav.services.impl.MovieServiceImpl;
import com.movie.SaveAFav.services.interfaces.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MovieServiceImplIntegrationTest {

    @TestConfiguration
    static class MovieServiceImplTestContextConfiguration {

        @Bean
        public MovieService movieService() {
            return new MovieServiceImpl();
        }
    }

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @Before
    public void setUp() {
        Movie movie = new Movie();
        movie.setTitle("Blade Runner");
        movie.setYear(2013);
        movie.setDirector("David Attenborough");

        Mockito.when(movieRepository.findMovieByTitle(movie.getTitle()))
                .thenReturn(movie);
        Mockito.when(movieRepository.findMovieByYear(movie.getYear()))
                .thenReturn(movie);
        Mockito.when(movieRepository.findMovieByDirector(movie.getDirector()))
                .thenReturn(movie);
    }

    @Test
    public void whenValidTitle_thenMovieShouldBeFound() {
        String title = "Blade Runner";
        Movie found = movieService.getMovieByTitle(title);

        assertThat(found.getTitle())
                .isEqualTo(title);
    }

    @Test
    public void whenValidYear_thenMovieShouldBeFound() {
        int year = 2013;
        Movie found = movieService.getMovieByYear(year);

        assertThat(found.getYear())
                .isEqualTo(year);
    }

    @Test
    public void whenValidDirector_thenMovieShouldBeFound() {
        String director = "David Attenborough";
        Movie found = movieService.getMovieByDirector(director);

        assertThat(found.getDirector())
                .isEqualTo(director);
    }
}
