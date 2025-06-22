package com.gym.app.repository;

import com.gym.app.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserLoginDataRepository extends JpaRepository<LoginData,Long> {

    @Query("SELECT COUNT(loginData) FROM LoginData loginData WHERE loginData.loginId = :loginId")
    long countLogins(@Param("loginId") String loginId);
    @Query("SELECT l FROM LoginData l WHERE l.loginId = :loginId ORDER BY l.loginTimestamp DESC")
    List<LoginData> findAllByLoginIdOrderByLoginTimestampDesc(@Param("loginId") String loginId);

    @Query(value = """
    SELECT login_timestamp 
    FROM login_data 
    WHERE login_id = :loginId 
      AND login_timestamp >= CURRENT_DATE - INTERVAL '30 days' 
    ORDER BY login_timestamp DESC
""", nativeQuery = true)
    List<Timestamp> findRecentLoginTimestamps(@Param("loginId") String loginId);

}
