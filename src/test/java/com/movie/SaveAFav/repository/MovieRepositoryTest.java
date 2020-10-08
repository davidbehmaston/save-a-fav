package com.movie.SaveAFav.repository;

import com.movie.SaveAFav.domains.Movie;
import com.movie.SaveAFav.repositories.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void whenFindByName_thenReturnMovie(){
        //given
        Movie movie = new Movie();
        movie.setTitle("Avatar");

        entityManager.persist(movie);
        entityManager.flush();

        //when
        Movie foundMovie = movieRepository.findMovieByTitle(movie.getTitle());

        //then
        assertThat(foundMovie.getTitle())
                .isEqualTo(movie.getTitle());
    }

    @Test
    public void addMovie(){
        Movie movie = new Movie();
        movie.setTitle("NEW MOVIE");

        movieRepository.save(movie);

        assertThat(movie.getTitle().equals("NEW MOVIE"));
    }
}
