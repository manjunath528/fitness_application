package com.gym.app.repository;



import com.gym.app.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT city FROM City city WHERE city.stateId=:stateId ORDER BY city.city ASC")
    List<City> cityDetailsByStateId(@Param("stateId") Long stateId);

    @Query("SELECT city FROM City city where city.stateId IN (SELECT id FROM State state where lower(state.state) = " +
            "(SELECT lower(state.state) FROM State state where state.id=:stateId)) ORDER BY city.city ASC")
    List<City> cityDetailsForStateNameByStateId(@Param("stateId") Long stateId);


}