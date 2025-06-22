package com.gym.app.service.dto;
import java.io.Serializable;

public class PersonalInformation implements Serializable {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private Long countryId;
    private Long stateId;
    private Long cityId;

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

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }



    public Long getCountryId() {
        return countryId;
    }

    public void setCountry(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setState(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCity(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", countryId=" + countryId +
                ", stateId=" + stateId +
                ", cityId=" + cityId +
                '}';
    }
}