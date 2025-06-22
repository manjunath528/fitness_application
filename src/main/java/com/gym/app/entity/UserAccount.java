package com.gym.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Manjunath Reddy
 */
@Entity
@Table(name = "user_account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_user_account")
    @SequenceGenerator(name = "id_sequence_user_account", sequenceName = "sequence_user_account", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    @Column(name = "email_id", unique = true)
    private String emailId;


    @Column(name = "password")
    private String password;

    @Column(name= "membership_id")
    private Long membershipId;


    @Column(name = "personal_details_status")
    private String personal_details_status;

    @Column(name = "health_details_status")

    private String health_details_status;

    @Column(name = "created_ts")
    private Timestamp createdTs;

    @Column(name = "updated_ts")
    private Timestamp updatedTs;


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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membership_id) {
        this.membershipId = membership_id;
    }

    public String getPersonal_details_status() {
        return personal_details_status;
    }

    public void setPersonal_details_status(String status) {
        this.personal_details_status = status;
    }

    public String getHealth_details_status() {
        return health_details_status;
    }

    public void setHealth_details_status(String health_details_status) {
        this.health_details_status = health_details_status;
    }


    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public Timestamp getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Timestamp updatedTs) {
        this.updatedTs = updatedTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UserAccount)) return false;

        UserAccount that = (UserAccount) o;

        return new EqualsBuilder()
                .append(getLoginId(), that.getLoginId())
                .append(getEmailId(), that.getEmailId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getLoginId())
                .append(getEmailId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", membership_id=" + membershipId +
                ", personal_details_status='" + personal_details_status + '\'' +
                ", health_details_status='" + health_details_status + '\'' +
                ", createdTs=" + createdTs +
                ", updatedTs=" + updatedTs +
                '}';
    }
}