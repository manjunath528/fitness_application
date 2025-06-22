package com.gym.app.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "user_badge")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBadge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_user_badge")
    @SequenceGenerator(name = "id_sequence_user_badge", sequenceName = "sequence_user_badge", allocationSize = 1)
    private Long id;

    @Column(name="login_id")
    private String loginId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Badge badge;

    @Column(name="badge_id",insertable = false, updatable = false)
    private int badgeId;

    @Column(name="awarded_ts")
    private Timestamp awardedTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public Timestamp getAwardedTs() {
        return awardedTs;
    }

    public void setAwardedTs(Timestamp awardedTs) {
        this.awardedTs = awardedTs;
    }

    @Override
    public String toString() {
        return "UserBadge{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", badge=" + badge +
                ", badgeId=" + badgeId +
                ", awardedTs=" + awardedTs +
                '}';
    }
}
