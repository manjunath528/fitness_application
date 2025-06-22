package com.gym.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_health_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserHealthDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_user_health_details")
    @SequenceGenerator(name = "id_sequence_user_health_details", sequenceName = "sequence_user_health_details", allocationSize = 1)
    @Column(name = "id")
    private Long Id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    @Column(name = "age")
    private Long age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private Long height;

    @Column(name = "current_weight")
    private Long currentWeight;

    @Column(name = "goal_weight")
    private Long goalWeight;

    @Column(name = "activity_level")
    private String activityLevel;

    @Column(name = "target_calories")
    private Long targetCalories;

    @Column(name = "created_ts")
    private Date createdTs;

    @Column(name = "updated_ts")
    private Date updatedTs;

    public Long getId() {
        return Id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setId(Long userHealthDetailsId) {
        this.Id = userHealthDetailsId;
    }


    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Long currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Long getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Long goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Long getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(Long targetCalories) {
        this.targetCalories = targetCalories;
    }

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Date getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Date updatedTs) {
        this.updatedTs = updatedTs;
    }


    @Override
    public String toString() {
        return "UserHealthDetails{" +
                "userHealthDetailsId=" + Id +
                ", loginId=" + loginId +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", currentWeight=" + currentWeight +
                ", goalWeight=" + goalWeight +
                ", activityLevel='" + activityLevel + '\'' +
                ", targetCalories=" + targetCalories +
                ", createdTs=" + createdTs +
                ", updatedTs=" + updatedTs +
                '}';
    }
}
