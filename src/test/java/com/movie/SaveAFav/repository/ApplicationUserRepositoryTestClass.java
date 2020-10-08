package com.movie.SaveAFav.repository;


import com.movie.SaveAFav.domains.ApplicationUser;
import com.movie.SaveAFav.repositories.ApplicationUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationUserRepositoryTestClass {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Test
    public void findByUsername(){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername("jsmith");

        entityManager.persist(applicationUser);
        entityManager.flush();

        ApplicationUser foundUser = applicationUserRepository.findByUsername(applicationUser.getUsername());

        assertThat(foundUser.getUsername())
                .isEqualTo(applicationUser.getUsername());
    }

    @Test
    public void addUser(){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername("dbehm");
        applicationUser.setPassword("password");

        applicationUserRepository.save(applicationUser);

        ApplicationUser newUser = applicationUserRepository.getOne(applicationUser.getId());

        assertThat(newUser.getUsername().equals("dbehm"));
    }
}
