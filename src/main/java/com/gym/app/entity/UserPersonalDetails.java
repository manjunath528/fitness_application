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
@Table(name = "user_personal_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPersonalDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_user_personal_details")
    @SequenceGenerator(name = "id_sequence_user_personal_details", sequenceName = "sequence_user_personal_details", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "city_id")
    private Long cityId;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

        if (!(o instanceof UserPersonalDetails)) return false;

        UserPersonalDetails that = (UserPersonalDetails) o;

        return new EqualsBuilder()
                .append(getLoginId(), that.getLoginId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getLoginId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "UserPersonalDetails{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", countryId=" + countryId +
                ", stateId=" + stateId +
                ", cityId=" + cityId +
                ", createdTs=" + createdTs +
                ", updatedTs=" + updatedTs +
                '}';
    }
}
