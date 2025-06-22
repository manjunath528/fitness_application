package com.gym.app.repository;



import com.gym.app.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT state FROM State state WHERE state.countryId=:countryId ORDER BY state.state ASC, state.id ASC")
    List<State> stateDetailsByCountry(@Param("countryId") Long countryId);
}
