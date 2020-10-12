package com.movie.SaveAFav.bootstrap;


import com.movie.SaveAFav.domains.ApplicationUser;
import com.movie.SaveAFav.domains.Movie;
import com.movie.SaveAFav.repositories.ApplicationUserRepository;
import com.movie.SaveAFav.services.impl.ApplicationUserDetailsService;
import com.movie.SaveAFav.services.interfaces.MovieService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    final MovieService movieService;



    public SeedData(MovieService movieService) {
        this.movieService = movieService;
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


//only seed if empty
        if (movieService.findAllMovies().size() == 0){

            Movie movie = new Movie();
            movie.setTitle("Avatar");
            movie.setDirector("James Cameron");
            movie.setYear(2009);
            movieService.saveMovie(movie);

            Movie movie2 = new Movie();
            movie2.setTitle("The Grand Budapest Hotel");
            movie2.setDirector("Wes Anderson");
            movie2.setYear(2014);
            movieService.saveMovie(movie2);

            Movie movie3 = new Movie();
            movie3.setTitle("Rebecca");
            movie3.setDirector("Alfred Hitchcock");
            movie3.setYear(1940);
            movieService.saveMovie(movie3);

        }
    }

//    public void generateTestMovieData(int numberOfMovies) {
//        IntStream.range(1, numberOfMovies)
//                .forEach(i -> {
//                    Movie movie = new Movie();
//                    movie.setTitle("Movie" + i);
//                    movie.setDirector(i % 2 == 0 ? "Mr. Man" : "Ms. Lady");
//                    movie.setYear(i % 2 == 0 ? (i * 330) : (i * 350));
//                    movieService.saveMovie(movie);
//                });
//    }

}
