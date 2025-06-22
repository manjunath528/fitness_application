package com.gym.app.repository;

import com.gym.app.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
