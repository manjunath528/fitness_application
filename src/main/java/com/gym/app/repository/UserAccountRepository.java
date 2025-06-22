package com.gym.app.repository;


import com.gym.app.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Manjunath Reddy
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("SELECT userAccount FROM UserAccount userAccount WHERE lower(userAccount.emailId)=lower(:emailId)")
    UserAccount findByEmailId(@Param("emailId") String emailId);

    @Query("SELECT userAccount FROM UserAccount userAccount WHERE lower(userAccount.loginId)=lower(:loginId)")
    UserAccount findByLoginId(@Param("loginId") String loginId);

    @Query("SELECT userAccount FROM UserAccount userAccount WHERE userAccount.loginId=:loginId " +
            "AND userAccount.password=:password")
    UserAccount userAuthorizationCheck(@Param("loginId") String loginId, @Param("password") String password);

    @Query("SELECT userAccount FROM UserAccount userAccount WHERE userAccount.updatedTs >= :yDate AND userAccount.updatedTs < :tDate")
    List<UserAccount> userAccountsByDate(@Param("yDate") Timestamp yDate, @Param("tDate") Timestamp tDate);
}