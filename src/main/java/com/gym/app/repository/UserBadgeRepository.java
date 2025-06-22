package com.gym.app.repository;

import com.gym.app.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge,Long> {

    @Query("SELECT COUNT(ub) > 0 FROM UserBadge ub WHERE ub.loginId = :loginId AND ub.badgeId = :badgeId")
    boolean existsByUserLoginIdAndBadgeId(@Param("loginId") String loginId, @Param("badgeId") Long badgeId);
}
