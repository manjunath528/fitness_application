package com.gym.app.repository;

import com.gym.app.entity.UserPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Manjunath Reddy
 */

public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetails, Long> {
    @Query("SELECT userPersonalDetails from UserPersonalDetails userPersonalDetails where lower(userPersonalDetails.loginId)=lower(:loginId)")
    UserPersonalDetails findByLoginId(@Param("loginId") String loginId);

    @Query("SELECT userPersonalDetails FROM UserPersonalDetails userPersonalDetails WHERE userPersonalDetails.updatedTs >= :yDate AND userPersonalDetails.updatedTs < :tDate")
    List<UserPersonalDetails> userPersonalDetailsByDate(@Param("yDate") Timestamp yDate, @Param("tDate") Timestamp tDate);
}
