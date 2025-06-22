package com.gym.app.repository;



import com.gym.app.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Manjunath Reddy
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

}