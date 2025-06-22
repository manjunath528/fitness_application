package com.gym.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Table(name = "login_data")
public class LoginData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_login_data")
    @SequenceGenerator(name = "id_sequence_login_data", sequenceName = "sequence_login_data", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name = "login_timestamp")
    private Timestamp loginTimestamp;

    @Column(name = "logout_timestamp")
    private Timestamp logoutTimestamp;

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

    public Timestamp getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(Timestamp loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    public Timestamp getLogoutTimestamp() {
        return logoutTimestamp;
    }

    public void setLogoutTimestamp(Timestamp logoutTimestamp) {
        this.logoutTimestamp = logoutTimestamp;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", loginTimestamp=" + loginTimestamp +
                ", logoutTimestamp=" + logoutTimestamp +
                '}';
    }
}
